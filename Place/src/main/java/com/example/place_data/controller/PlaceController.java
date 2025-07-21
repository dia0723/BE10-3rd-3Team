package com.example.place_data.controller;

import com.example.place_data.controller.client.AccountClient;
import com.example.place_data.controller.request.PlaceSearchRequest;
import com.example.place_data.controller.request.RegisterPlaceWithAuthorizationRequest;
import com.example.place_data.controller.request.UpdatePlaceWithAuthorizationRequest;
import com.example.place_data.controller.response.IdAccountResponse;
import com.example.place_data.controller.response.RegisterPlaceWithAuthorizationResponse;
import com.example.place_data.controller.response.SearchPlaceResponse;
import com.example.place_data.controller.response.UpdatePlaceResponse;
import com.example.place_data.entity.Place;
import com.example.place_data.repository.PlaceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/place")
public class PlaceController {

    private final PlaceRepository placeRepository;
    private final AccountClient accountClient;

    public PlaceController(PlaceRepository placeRepository, AccountClient accountClient) {
        this.placeRepository = placeRepository;
        this.accountClient = accountClient;
    }

    // 여행지 등록 API 구현
    @PostMapping("/register")
    public RegisterPlaceWithAuthorizationResponse register(
            @RequestHeader("Authorization") String token,
            @RequestBody RegisterPlaceWithAuthorizationRequest request) {
        log.info("Received request to register a new place");

        // userToken 획득
        String pureToken = extractToken(token);

        // FeignClient를 통해 account 서비스에 accountId 요청
        IdAccountResponse response = accountClient.getAccountId("Bearer " + pureToken);
        Long accountId = response.getAccountId();
        log.info("accountId = {}", accountId);

        Place requestedPlace = request.toRegister(accountId);
        Place registeredPlace = placeRepository.save(requestedPlace);
        String message = "여행지가 성공적으로 등록되었습니다.";
        return RegisterPlaceWithAuthorizationResponse.from(registeredPlace, message);
    }

    private String extractToken(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return token;
    }

    // 여행지 목록 조회 API 구현
    @GetMapping("/list")
    public List<SearchPlaceResponse> listPlaces() {
        log.info("Received request to list all places");
        List<Place> places = placeRepository.findAll();

        return places.stream()
                .map(SearchPlaceResponse::from)
                .collect(Collectors.toList());
    }


    // 특정 여행지 상세 조회 API 구현 (1)
    @GetMapping("/{place_id}")
    public SearchPlaceResponse getPlaceById(@PathVariable Long place_id) {
        log.info("Received request to get a place by id: {}", place_id);

        Place place = placeRepository.findById(place_id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "장소를 찾지 못했습니다."));

        return SearchPlaceResponse.from(place);
    }

    // 특정 여행지 상세 조회 API 구현 (2)
    @PostMapping("/search")
    public List<SearchPlaceResponse> searchPlaces(@RequestBody PlaceSearchRequest request) {
        String title = request.getTitle();
        String category = request.getCategory();
        String location = request.getLocation();

        List<Place> result;

        if (title != null && category != null && location != null) {
            result = placeRepository.findByTitleContainingAndCategoryAndLocation(title, category, location);
        } else if (title != null && category != null) {
            result = placeRepository.findByTitleContainingAndCategory(title, category);
        } else if (title != null && location != null) {
            result = placeRepository.findByTitleContainingAndLocation(title, location);
        } else if (title != null) {
            result = placeRepository.findByTitleContaining(title);
        } else if (category != null && location != null) {
            result = placeRepository.findByCategoryAndLocation(category, location);
        } else if (category != null) {
            result = placeRepository.findByCategory(category);
        } else if (location != null) {
            result = placeRepository.findByLocation(location);
        } else {
            result = placeRepository.findAll();
        }

        return SearchPlaceResponse.from(result);
    }

    // 여행지 수정 API 구현 (인증받은 사용자만 여행지 수정 가능)
    @PostMapping("/update")
    public UpdatePlaceResponse updatePlace(
            @RequestHeader("Authorization") String token,
            @RequestBody UpdatePlaceWithAuthorizationRequest request) {

        log.info("Received request to update a place");
        log.info("Authorization header: {}", token);

        String pureToken = extractToken(token);
        log.info("Extracted token: {}", pureToken);

        IdAccountResponse response = accountClient.getAccountId("Bearer " + pureToken);
        if (response == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "인증 토큰이 유효하지 않습니다.");
        }

        Long accountId = response.getAccountId();
        log.info("accountId = {}", accountId);

        Long place_id = request.getPlace_id();
        if (place_id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "place_id가 null입니다.");
        }

        Optional<Place> maybePlace = placeRepository.findById(place_id);
        if (maybePlace.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "수정할 여행지를 찾을 수 없습니다.");
        }

        Place foundPlace = maybePlace.get();
        if (foundPlace.getAccountId() == null || !foundPlace.getAccountId().equals(accountId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "여행지를 수정할 권한이 없습니다.");
        }

        log.info("현재 로그인한 사용자 accountId = {}", accountId);
        log.info("수정 대상 여행지의 accountId = {}", foundPlace.getAccountId());

        foundPlace.setTitle(request.getTitle());
        foundPlace.setContent(request.getContent());
        foundPlace.setCategory(request.getCategory());
        foundPlace.setLocation(request.getLocation());
        foundPlace.setAddress(request.getAddress());

        Place updatedPlace = placeRepository.save(foundPlace);
        log.info("Updated place: {}", updatedPlace);

        return UpdatePlaceResponse.from(updatedPlace);
    }


    // 여행지 삭제 API 구현 (인증받은 사용자만 여행지 삭제 가능)
    @DeleteMapping("/delete/{place_id}")
    public ResponseEntity<?> deletePlace(
            @RequestHeader("Authorization") String token,
            @PathVariable Long place_id
    ) {
        log.info("Received request to delete a place");
        String pureToken = extractToken(token);
        IdAccountResponse response = accountClient.getAccountId("Bearer " + pureToken);
        Long accountId = response.getAccountId();

        Place foundPlace = placeRepository.findById(place_id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "장소를 찾을 수 없습니다."));

        if (!foundPlace.getAccountId().equals(accountId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "여행지를 삭제할 권한이 없습니다.");
        }

        placeRepository.delete(foundPlace);
        return ResponseEntity.ok().body("여행지를 성공적으로 삭제했습니다.");
    }

}

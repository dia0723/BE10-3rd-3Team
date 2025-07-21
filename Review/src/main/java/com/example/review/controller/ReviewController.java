package com.example.review.controller;

import com.example.review.client.AccountClient;
import com.example.review.client.PlaceClient;
import com.example.review.controller.request.RegisterReviewRequest;
import com.example.review.controller.request.UpdateReviewRequest;
import com.example.review.controller.response.IdAccountResponse;
import com.example.review.controller.response.RegisterReviewResponse;
import com.example.review.controller.response.SearchPlaceResponse;
import com.example.review.controller.response.UpdateReviewResponse;
import com.example.review.entity.Review;
import com.example.review.repository.ReviewRepository;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    AccountClient accountClient;
    @Autowired
    PlaceClient placeClient;

    @PostMapping("/register")
    public RegisterReviewResponse register(
            @RequestHeader("Authorization") String token,
            @RequestBody RegisterReviewRequest request
    ) {
        log.info("Registering new review -> {}", request);

        String pureToken = extractToken(token);
        IdAccountResponse response = accountClient.getAccountId("Bearer " + pureToken);
        Long accountId = response.getAccountId();

        // placeId 존재 여부 확인
        try {
            placeClient.getPlaceById(request.getPlaceId()); // 예외가 나면 404 → catch
        } catch (FeignException.NotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 placeId에 해당하는 장소가 존재하지 않습니다.");
        }

        // 리뷰 등록
        Review review = request.toReview(accountId);
        Review saved = reviewRepository.save(review);
        return RegisterReviewResponse.from(saved);
    }

    private String extractToken(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return token;


    }

    @GetMapping("/read/{reviewId}")
    public ResponseEntity<RegisterReviewResponse> getReviewById(@PathVariable Long reviewId) {
        Optional<Review> optionalReview = reviewRepository.findById(reviewId);
        if (optionalReview.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Review review = optionalReview.get();
        return ResponseEntity.ok(RegisterReviewResponse.from(review));
    }


    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UpdateReviewResponse> updateReview(
            @RequestHeader("Authorization") String token,
            @RequestBody UpdateReviewRequest update) {
        log.info("Updating review -> {}", update);

        String pureToken = extractToken(token);
        IdAccountResponse response = accountClient.getAccountId("Bearer " + pureToken);
        Long accountId = response.getAccountId();

        Review review = reviewRepository.findById(update.getReviewId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "리뷰를 찾을 수 없습니다."));

        if (!review.getAccountId().equals(accountId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "리뷰를 수정할 권한이 없습니다.");
        }

        review.setReviewTitle(update.getReviewTitle());
        review.setReviewContent(update.getReviewContent());

        Review updated = reviewRepository.save(review);

        return ResponseEntity.ok(UpdateReviewResponse.from(updated));
    }


    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity<?> deleteReview(
            @RequestHeader("Authorization") String token,
            @PathVariable Long reviewId) {
        log.info("Received request to delete review with id: {}", reviewId);

        String pureToken = extractToken(token);
        IdAccountResponse response = accountClient.getAccountId("Bearer " + pureToken);
        if (response == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "유효하지 않은 인증 토큰입니다.");
        }
        Long accountId = response.getAccountId();

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "리뷰를 찾을 수 없습니다."));

        if (!review.getAccountId().equals(accountId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "리뷰를 삭제할 권한이 없습니다.");
        }

        reviewRepository.delete(review);
        return ResponseEntity.ok().body("리뷰가 성공적으로 삭제되었습니다.");
    }


}

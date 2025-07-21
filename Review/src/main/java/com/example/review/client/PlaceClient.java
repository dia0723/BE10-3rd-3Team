package com.example.review.client;


import com.example.review.controller.response.SearchPlaceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "place")
public interface PlaceClient {

    @GetMapping("/places/{placeId}")
    SearchPlaceResponse getPlaceById(@PathVariable("placeId") Long placeId);
}

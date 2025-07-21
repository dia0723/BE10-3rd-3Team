package com.example.review.controller.request;

import com.example.review.entity.Review;
import lombok.ToString;

@ToString
public class RegisterReviewRequest {

    private Long placeId;
    private String reviewTitle;
    private String reviewContent;

    public RegisterReviewRequest() { }

    public RegisterReviewRequest(Long placeId, String reviewTitle, String reviewContent) {
        this.placeId = placeId;
        this.reviewTitle = reviewTitle;
        this.reviewContent = reviewContent;
    }

    public Long getPlaceId() {
        return placeId;
    }
    public String getReviewTitle() {
        return reviewTitle;
    }
    public String getReviewContent() {
        return reviewContent;
    }

    public Review toReview(Long accountId) {
        return new Review(placeId, accountId, reviewTitle, reviewContent);
    }

}

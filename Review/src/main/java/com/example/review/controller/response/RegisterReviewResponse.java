package com.example.review.controller.response;

import com.example.review.entity.Review;

public class RegisterReviewResponse {

    private Long reviewId;
    private Long placeId;
    private Long accountId;
    private String reviewTitle;
    private String reviewContent;

    public RegisterReviewResponse(Long reviewId, Long placeId, Long accountId, String reviewTitle, String reviewContent) {
        this.reviewId = reviewId;
        this.placeId = placeId;
        this.accountId = accountId;
        this.reviewTitle = reviewTitle;
        this.reviewContent = reviewContent;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public String getReviewTitle() {
        return reviewTitle;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public static RegisterReviewResponse from(Review review) {
        return new RegisterReviewResponse(
                review.getReviewId(),
                review.getPlaceId(),
                review.getAccountId(),
                review.getReviewTitle(),
                review.getReviewContent()
        );
    }
}

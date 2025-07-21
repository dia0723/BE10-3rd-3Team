package com.example.review.controller.response;

import com.example.review.entity.Review;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateReviewResponse {

    private Long reviewId;
    private Long placeId;
    private Long accountId;
    private String reviewTitle;
    private String reviewContent;

    public UpdateReviewResponse() {

    }

    public UpdateReviewResponse(Long reviewId, Long placeId, Long accountId, String reviewTitle, String reviewContent) {
        this.reviewId = reviewId;
        this.placeId = placeId;
        this.accountId = accountId;
        this.reviewTitle = reviewTitle;
        this.reviewContent = reviewContent;
    }


    public Long getReviewId() { return reviewId; }
    public Long getPlaceId() { return placeId; }
    public Long getAccountId() { return accountId; }
    public String getReviewTitle() { return reviewTitle; }
    public String getReviewContent() { return reviewContent; }


    public static UpdateReviewResponse from(Review review) {
        return new UpdateReviewResponse(
                review.getReviewId(),
                review.getPlaceId(),
                review.getAccountId(),
                review.getReviewTitle(),
                review.getReviewContent()
        );
    }
}

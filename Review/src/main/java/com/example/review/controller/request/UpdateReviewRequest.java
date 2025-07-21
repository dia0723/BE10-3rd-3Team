package com.example.review.controller.request;

public class UpdateReviewRequest {

    private Long reviewId;
    private String reviewTitle;
    private String reviewContent;

    public UpdateReviewRequest() {}

    public UpdateReviewRequest(Long reviewId, String reviewTitle, String reviewContent) {
        this.reviewId = reviewId;
        this.reviewTitle = reviewTitle;
        this.reviewContent = reviewContent;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public String getReviewTitle() {
        return reviewTitle;
    }

    public String getReviewContent() {
        return reviewContent;
    }
}

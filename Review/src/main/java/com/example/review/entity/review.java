package com.example.review.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.ToString;

@Entity
@ToString

public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reviewId;
    private Long placeId;
    private Long accountId;
    private String reviewTitle;
    private String reviewContent;

    public Review(Long placeId, Long accountId, String reviewTitle, String reviewContent) {
        this.placeId = placeId;
        this.accountId = accountId;
        this.reviewTitle = reviewTitle;
        this.reviewContent = reviewContent;
    }

    public Review() { }

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

    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }
}

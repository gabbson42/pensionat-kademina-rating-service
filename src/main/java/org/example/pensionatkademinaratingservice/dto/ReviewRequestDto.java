package org.example.pensionatkademinaratingservice.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ReviewRequestDto {

    @NotNull(message = "Needs customer ID")
    private Long customerId;

    @NotNull(message = "Room ID is needed")
    private Long roomId;

    @Min(value = 1, message = "Rating least 1")
    @Max(value = 5, message = "Rating max 5")
    private int rating;

    private String comment;

    public ReviewRequestDto() {
    }

    public ReviewRequestDto(Long customerId, Long roomId, int rating, String comment) {
        this.customerId = customerId;
        this.roomId = roomId;
        this.rating = rating;
        this.comment = comment;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
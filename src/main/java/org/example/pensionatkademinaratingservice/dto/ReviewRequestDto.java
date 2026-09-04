package org.example.pensionatkademinaratingservice.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class ReviewRequestDto {

    private Long customerId;
    private Long roomId;

    @Min(1)
    @Max(5)
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

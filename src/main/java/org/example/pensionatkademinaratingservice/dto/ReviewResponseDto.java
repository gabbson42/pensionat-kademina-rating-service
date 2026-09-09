package org.example.pensionatkademinaratingservice.dto;

import java.time.LocalDate;

public class ReviewResponseDto {

    private Long customerId;
    private Long roomId;
    private int rating;
    private String comment;
    private LocalDate date;

    public ReviewResponseDto() {
    }

    public ReviewResponseDto(Long customerId,Long roomId, int rating, String comment, LocalDate date) {
        this.customerId = customerId;
        this.roomId = roomId;
        this.rating = rating;
        this.comment = comment;
        this.date = date;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}

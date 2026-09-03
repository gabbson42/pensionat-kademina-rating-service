package org.example.pensionatkademinaratingservice.dto;

import java.time.LocalDate;

public class ReviewResponseDto {

    private int customerId;
    private String customerName; // logik för att hämta customer name.
    private int roomId;
    private int rating;
    private String comment;
    private LocalDate date;

    public ReviewResponseDto() {
    }

    public ReviewResponseDto(int customerId, String customerName, int roomId, int rating, String comment, LocalDate date) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.roomId = roomId;
        this.rating = rating;
        this.comment = comment;
        this.date = date;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
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

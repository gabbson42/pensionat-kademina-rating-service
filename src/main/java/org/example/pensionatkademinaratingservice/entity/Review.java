package org.example.pensionatkademinaratingservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Review {

    @Id
    private Long id;
    private Long customerId;
    private Long roomId;
    private int rating;
    private String comment;
    private LocalDate date;


    public Review(){

    }

    public Review(Long id, Long customerId, Long roomId, int rating, String comment, LocalDate date) {
        this.id = id;
        this.customerId = customerId;
        this.roomId = roomId;
        this.rating = rating;
        this.comment = comment;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

package org.example.pensionatkademinaratingservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Review {

    @Id
    private Long id;
    private int customerId;
    private int roomId;
    private int rating;
    private String comment;
    private LocalDate date;


    public Review(){

    }

    public Review(Long id, int customerId, int roomId, int rating, String comment, LocalDate date) {
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

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

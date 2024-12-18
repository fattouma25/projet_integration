package com.example.note.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique ID for each rating

    private int rating; // Rating from 1 to 5
    private String feedback; // Optional comment from the user
    private LocalDateTime dateSubmitted; // The date when the rating was submitted

    private String driverId;

    // Constructors
    public Note() {
    }

    public Note(int rating, String feedback, String driverId) {
        this.rating = rating;
        this.feedback = feedback;
        this.dateSubmitted = LocalDateTime.now();
        this.driverId = driverId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public LocalDateTime getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(LocalDateTime dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }
}

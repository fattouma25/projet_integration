package com.example.note.service;

import com.example.note.model.Note;
import com.example.note.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public Note addRating(String driverId, int rating, String feedback) {
        // Create new Note object and set the provided values
        Note newNote = new Note();
        newNote.setDriverId(driverId); // Set the driver ID
        newNote.setRating(rating); // Set the rating value
        newNote.setFeedback(feedback); // Set the feedback (optional)
        newNote.setDateSubmitted(LocalDateTime.now()); // Set the submission date

        // Save the Note object to the database
        return noteRepository.save(newNote);
    }

    // Get all ratings for a specific driver
    public List<Note> getRatingsForDriver(String driverId) {
        return noteRepository.findByDriverId(driverId);
    }
}

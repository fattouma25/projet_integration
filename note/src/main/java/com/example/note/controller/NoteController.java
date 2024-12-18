package com.example.note.controller;

import com.example.note.model.Note;
import com.example.note.repository.NoteRepository;
import com.example.note.service.NoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/note")
@CrossOrigin(origins = "http://localhost:4200")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private NoteRepository noteRepository;

    @PostMapping("/addNote")
    public Note addRating(
            @RequestParam String driverId,
            @RequestParam int rating,
            @RequestParam(required = false) String feedback) {

        // Print the received values for debugging purposes
        System.out.println("Driver ID: " + driverId);
        System.out.println("Rating: " + rating);
        System.out.println("Feedback: " + feedback);

        // Pass these values to the note service to process the rating
        return noteService.addRating(driverId, rating, feedback);
    }

    @GetMapping("/getAllNotes")
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }
}

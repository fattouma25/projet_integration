package com.example.note.repository;

import com.example.note.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    // Find all notes for a specific driver by driverId
    List<Note> findByDriverId(String driverId);
}

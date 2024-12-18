package com.example.message.controller;

import com.example.message.model.Message;
import com.example.message.service.MessageService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
@CrossOrigin(origins = "http://localhost:4200")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/contactAdmin")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<String> sendMessage(@RequestBody Message message) {
        try {
            messageService.saveMessage(message);
            return ResponseEntity.ok("Message sent successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error occurred while sending the message.");
        }
    }

    @GetMapping("/getAllMessages")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Message>> getAllMessages() {
        try {
            List<Message> messages = messageService.getAllMessages();
            return ResponseEntity.ok(messages);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}

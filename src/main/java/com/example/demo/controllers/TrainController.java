package com.example.demo.controllers;

import com.example.demo.repositories.TrainRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import com.example.demo.models.Train;

import java.util.*;
@RestController
@RequestMapping("/trains")
public class TrainController {
    private final TrainRepository trainRepository;
    public TrainController(TrainRepository trainRepository) {
        this.trainRepository = trainRepository;
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @PostMapping
    public Train createTrain(@Valid @RequestBody Train train) {
        return trainRepository.save(train);
    }

    @GetMapping
    public List<Train> getAllTrains() {
        return trainRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Train> getTrainById(@PathVariable String id) {
        Optional<Train> trainOptional = trainRepository.findById(id);
        return trainOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTrain(@PathVariable String id) {
        trainRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

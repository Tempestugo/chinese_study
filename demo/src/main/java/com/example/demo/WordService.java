package com.example.demo;

import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class WordService {

    private final WordRepository repository;
    private final WordReviewService reviewService;

    public WordService(WordRepository repository, WordReviewService reviewService) {
        this.repository = repository;
        this.reviewService = reviewService;
    }

    public Word save(Word word) {
        word.setCreatedAt(LocalDate.now());
        Word saved = repository.save(word);
        reviewService.initReview(saved);
        return saved;
    }

    public List<Word> findAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
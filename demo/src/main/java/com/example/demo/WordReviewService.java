package com.example.demo;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;

@Service
public class WordReviewService {

    private final WordReviewRepository repository;

    public WordReviewService(WordReviewRepository repository) {
        this.repository = repository;
    }
    public WordReview review(Long wordId, int qualidade) {
        WordReview review = repository.findByWordId(wordId)
                .orElseThrow(() -> new RuntimeException("Review não encontrada"));

        if (qualidade == 5) {
            double ez = review.getEaseFactor()+0.1;
            review.setIntervalDays((int) (review.getIntervalDays()*ez));

        } else if (qualidade == 3) {
            double easeFactor = review.getEaseFactor() - 0.1;
            review.setEaseFactor((double) easeFactor);


        } else {
            review.setEaseFactor(2.5);
            review.setIntervalDays(1);

        }

        review.setNextReviewDate(LocalDate.now().plusDays(review.getIntervalDays()));
        return repository.save(review);
    }
    public WordReview initReview(Word word) {
        WordReview review = new WordReview();
        review.setWord(word);
        review.setIntervalDays(1);
        review.setEaseFactor(2.5);
        review.setNextReviewDate(LocalDate.now().plusDays(1));
        return repository.save(review);
    }

}
package com.example.demo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
public class WordReviewController {

    private final WordReviewService service;

    public WordReviewController(WordReviewService service) {
        this.service = service;
    }

    @PostMapping("/{wordId}/review")
    public WordReview review(@PathVariable Long wordId,
                             @RequestParam int qualidade) {
        return service.review(wordId, qualidade);
    }
}

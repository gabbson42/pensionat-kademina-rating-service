package org.example.pensionatkademinaratingservice.controller;

import jakarta.validation.Valid;
import org.example.pensionatkademinaratingservice.dto.ReviewRequestDto;
import org.example.pensionatkademinaratingservice.dto.ReviewResponseDto;
import org.example.pensionatkademinaratingservice.service.ReviewService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @PostMapping("/api/reviews")
    public ReviewResponseDto createReview (
            @Valid @RequestBody ReviewRequestDto reviewRequestDto){

        return reviewService.createReview(reviewRequestDto);
    }
    @GetMapping("/api/reviews")
    public List<ReviewResponseDto> getAllReviews(){
        return reviewService.getAllReviews();
    }
}

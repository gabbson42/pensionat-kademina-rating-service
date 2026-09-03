package org.example.pensionatkademinaratingservice.service;

import org.example.pensionatkademinaratingservice.dto.ReviewRequestDto;
import org.example.pensionatkademinaratingservice.dto.ReviewResponseDto;
import org.example.pensionatkademinaratingservice.entity.Review;
import org.example.pensionatkademinaratingservice.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;


    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }


    public ReviewResponseDto createReview(ReviewRequestDto reviewRequestDto){

        int customerId = reviewRequestDto.getCustomerId();
        int roomId = reviewRequestDto.getRoomId();
        int rating = reviewRequestDto.getRating();
        String comment = reviewRequestDto.getComment();
        LocalDate date = LocalDate.now();

        Review review = new Review(null,customerId,roomId,rating,comment,date);

        // customerName ska hämtas från en annan API.
        String reviewCustumerNmae = "Raul";




        return new ReviewResponseDto(review.getCustomerId(),reviewCustumerNmae,roomId, rating,comment,date );

    }
}

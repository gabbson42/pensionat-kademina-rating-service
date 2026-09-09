package org.example.pensionatkademinaratingservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.example.pensionatkademinaratingservice.dto.CheckResponseDto;
import org.example.pensionatkademinaratingservice.dto.ReviewRequestDto;
import org.example.pensionatkademinaratingservice.dto.ReviewResponseDto;
import org.example.pensionatkademinaratingservice.entity.Review;
import org.example.pensionatkademinaratingservice.repository.ReviewRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

import java.time.LocalDate;


@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final RestClient restClient;


    public ReviewService(
            ReviewRepository reviewRepository,
            @Value("${booking-service.url}") String bookingServiceUrl) {

        this.reviewRepository = reviewRepository;
        this.restClient = RestClient.builder()
                .baseUrl(bookingServiceUrl)
                .build();
    }


    public ReviewResponseDto createReview(ReviewRequestDto reviewRequestDto){

        Long customerId = reviewRequestDto.getCustomerId();
        Long roomId = reviewRequestDto.getRoomId();
        int rating = reviewRequestDto.getRating();
        String comment = reviewRequestDto.getComment();
        LocalDate date = LocalDate.now();

        CheckResponseDto checkResponseDto = restClient
                .get()
                .uri("/api/bookings/check?customerId={customerId}&roomId={roomId}",
                        customerId,roomId)
                .retrieve()
                .body(CheckResponseDto.class);

        if (checkResponseDto == null || !checkResponseDto.isBooked()){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "You have not booked this room"
            );

        }
        Review review = new Review(null,customerId,roomId,rating,comment,date);
        Review result = reviewRepository.save(review);


        return new ReviewResponseDto(result.getCustomerId(),
                result.getRoomId(),
                result.getRating(),
                result.getComment(),
                result.getDate() );

    }
    public List<ReviewResponseDto> getAllReviews() {
        List<ReviewResponseDto> reviews = reviewRepository.findAll()
                .stream()
                .map(review -> new ReviewResponseDto(
                        review.getCustomerId(),
                        review.getRoomId(),
                        review.getRating(),
                        review.getComment(),
                        review.getDate()
                ))

                .toList();

        return reviews;



    }
}

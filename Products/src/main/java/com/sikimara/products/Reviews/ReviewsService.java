package com.sikimara.products.Reviews;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewsService {
    private final ReviewsRepository reviewsRepository;

    public ReviewsService(ReviewsRepository reviewsRepository) {
        this.reviewsRepository = reviewsRepository;
    }
    public Reviews saveReview(Reviews review){
        return reviewsRepository.save(review);
    }
    public List<Reviews> getAllReviews(){
        return reviewsRepository.findAll();
    }
    public List<Reviews> getReviewsByProductId(int id){
        return reviewsRepository.findByProductId(id);
    }
    public Reviews getReviewById(int id){
        return reviewsRepository.findById(id).orElse(null);
    }
    public String deleteReviewById(int id){
        reviewsRepository.deleteById(id);
        return "Review Deleted";
    }
    public Reviews updateReview(Reviews review){
        Reviews existingReview = reviewsRepository.findById(review.getId()).orElse(null);
        existingReview.setComment(review.getComment());
        existingReview.setRating(review.getRating());
        return reviewsRepository.save(existingReview);
    }

}

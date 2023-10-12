package com.sikimara.products.Reviews;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products/reviews")
@AllArgsConstructor
public class ReviewsController {

    ReviewsService reviewsService;

    @GetMapping("/product/{id}")
    public List<Reviews> getReviewByProductId(@PathVariable int id){
        return reviewsService.getReviewsByProductId(id);
    }
    @GetMapping("/all")
    public List<Reviews> getAllReviews(){
        return reviewsService.getAllReviews();
    }
    @PostMapping("/create")
    public Reviews createReview(@RequestBody Reviews review){
        return reviewsService.saveReview(review);
    }
    @PutMapping("/update")
    public Reviews updateReview(@RequestBody Reviews review){
        return reviewsService.updateReview(review);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteReview(@PathVariable int id){
        return reviewsService.deleteReviewById(id);
    }
    @GetMapping("/get/{id}")
    public Reviews getReviewById(@PathVariable int id){
        return reviewsService.getReviewById(id);
    }


}

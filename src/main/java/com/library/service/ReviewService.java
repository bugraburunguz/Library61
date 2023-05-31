package com.library.service;

import com.library.persistance.jpa.entity.ReviewEntity;
import com.library.persistance.jpa.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public List<ReviewEntity> getAllReviews() {
        return reviewRepository.findAll();
    }

    public ReviewEntity addReview(ReviewEntity review) {
        return reviewRepository.save(review);
    }

    public ReviewEntity getReviewById(Long id) {
        Optional<ReviewEntity> review = reviewRepository.findById(id);
        return review.orElseThrow(() -> new EntityNotFoundException("Review with ID " + id + " not found"));
    }

    public ReviewEntity updateReview(Long id, ReviewEntity review) {
        return reviewRepository.findById(id)
                .map(existingReview -> {
                    existingReview.setUser(review.getUser());
                    existingReview.setBook(review.getBook());
                    existingReview.setRating(review.getRating());
                    existingReview.setComment(review.getComment());
                    return reviewRepository.save(review);
                })
                .orElseThrow(() -> new EntityNotFoundException("Review with ID " + id + " not found"));
    }

    public void deleteReview(Long id) {
        if (!reviewRepository.existsById(id)) {
            throw new EntityNotFoundException("Review with ID " + id + " not found");
        }
        reviewRepository.deleteById(id);
    }
}
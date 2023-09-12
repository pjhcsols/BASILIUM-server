package basilium.basiliumspring.service.review;

import basilium.basiliumspring.domain.review.Review;
import basilium.basiliumspring.repository.review.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }
 /*
    public void addReview(Review review) {
        reviewRepository.addReview(review);
    }

 */
    public void addReview(Review review, int rating) {
        review.setRating(rating); // 리뷰에 평점 설정
        reviewRepository.addReview(review); // 리뷰 추가
    }


    public void updateReview(Review review) {
        reviewRepository.updateReview(review);
    }

    public void deleteReview(Long reviewId) {
        reviewRepository.deleteReview(reviewId);
    }

    public Review getReviewById(Long reviewId) {
        return reviewRepository.getReviewById(reviewId);
    }

    public List<Review> getAllReviews() {
        return reviewRepository.getAllReviews();
    }

    public List<Review> getReviewsByProductId(Long productId) {
        return reviewRepository.getReviewsByProductId(productId);
    }

    public List<Review> getReviewsByUserId(Long userId) {
        return reviewRepository.getReviewsByUserId(userId);
    }

    //리뷰 평점 계산
    public double calculateAverageRating(Long productId) {
        List<Review> reviews = reviewRepository.getReviewsByProductId(productId);
        if (reviews.isEmpty()) {
            return 0.0; // 리뷰가 없을 경우 기본값 반환
        }

        double sum = 0;
        for (Review review : reviews) {
            sum += review.getRating();
        }

        return sum / reviews.size();
    }

}


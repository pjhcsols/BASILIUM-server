package basilium.basiliumspring.repository.review;

import basilium.basiliumspring.domain.review.Review;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MemoryReviewRepository implements ReviewRepository {

        private Map<Long, Review> reviewMap;

        public MemoryReviewRepository() {
            reviewMap = new HashMap<>();
        }

        @Override
        public void addReview(Review review) { //review_date도 들어가야됨
            reviewMap.put(review.getReviewId(), review);
        }

        @Override
        public Review getReviewById(Long reviewId) {
            return reviewMap.get(reviewId);
        }

        @Override
        public List<Review> getAllReviews() {
            return new ArrayList<>(reviewMap.values());
        }

        @Override
        public List<Review> getReviewsByProductId(Long productId) {
            List<Review> result = new ArrayList<>();
            for (Review review : reviewMap.values()) {
                if (review.getProductId().equals(productId)) {
                    result.add(review);
                }
            }
            return result;
        }
        /*
        @Override
        public List<Review> getReviewsByProductName(String productName) {
            List<Review> result = new ArrayList<>();
            for (Review review : reviewMap.values()) {
                // 제품 이름이 productName과 일치하는 경우를 찾습니다.
                if (review.getProductName().getName().equals(productName)) {
                    result.add(review);
                }
            }
            return result;
        }
*/
        @Override
        public List<Review> getReviewsByUserId(Long userId) {
            List<Review> result = new ArrayList<>();
            for (Review review : reviewMap.values()) {
                if (review.getUserId().equals(userId)) {
                    result.add(review);
                }
            }
            return result;
        }

        @Override
        public void updateReview(Review review) {
            if (reviewMap.containsKey(review.getReviewId())) {
                reviewMap.put(review.getReviewId(), review);
            } else {
                throw new IllegalArgumentException("해당 리뷰 ID를 가진 리뷰가 존재하지 않습니다.");
            }
        }

        @Override
        public void deleteReview(Long reviewId) {
            reviewMap.remove(reviewId);
        }
}



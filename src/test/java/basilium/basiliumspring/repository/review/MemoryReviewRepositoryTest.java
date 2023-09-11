package basilium.basiliumspring.repository.review;

import basilium.basiliumspring.domain.review.Review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/*
class MemoryReviewRepositoryTest {
    private MemoryReviewRepository reviewRepository;

    @BeforeEach
    void setUp() {
        reviewRepository = new MemoryReviewRepository();
    }

    @Test
    void addReview() {
        Review review = new Review(1L, 100L, 500L, "좋은 상품입니다.", "2023-08-03");

        // 리뷰 추가
        reviewRepository.addReview(review);

        // 리뷰를 추가한 후, 해당 리뷰 ID로 조회하여 추가된 리뷰가 존재하는지 확인
        Review retrievedReview = reviewRepository.getReviewById(1L);
        assertEquals(review, retrievedReview, "리뷰 추가 실패: 리뷰가 추가되지 않았습니다.");
    }

    @Test
    void getReviewById() {
        // 존재하는 리뷰 하나 추가
        Review review = new Review(1L, 100L, 500L, "좋은 상품입니다.", "2023-08-03");
        reviewRepository.addReview(review);

        // 리뷰 ID로 조회하여 존재하는 리뷰를 찾는지 확인
        Review retrievedReview = reviewRepository.getReviewById(1L);
        assertNotNull(retrievedReview, "리뷰 조회 실패: 리뷰를 찾을 수 없습니다.");
    }

    @Test
    void getAllReviews() {
        // 리뷰 여러 개 추가
        Review review1 = new Review(1L, 100L, 500L, "좋은 상품입니다.", "2023-08-03");
        Review review2 = new Review(2L, 101L, 500L, "매우 만족합니다.", "2023-08-04");
        Review review3 = new Review(3L, 102L, 501L, "그저 그래요.", "2023-08-05");

        reviewRepository.addReview(review1);
        reviewRepository.addReview(review2);
        reviewRepository.addReview(review3);

        // 모든 리뷰 조회
        List<Review> allReviews = reviewRepository.getAllReviews();

        // 리뷰 개수와 추가한 리뷰 개수가 일치하는지 확인
        assertEquals(3, allReviews.size(), "모든 리뷰 조회 실패: 리뷰 개수가 일치하지 않습니다.");
        assertTrue(allReviews.contains(review1), "모든 리뷰 조회 실패: 리뷰1이 포함되어 있지 않습니다.");
        assertTrue(allReviews.contains(review2), "모든 리뷰 조회 실패: 리뷰2가 포함되어 있지 않습니다.");
        assertTrue(allReviews.contains(review3), "모든 리뷰 조회 실패: 리뷰3이 포함되어 있지 않습니다.");
    }

    @Test
    void getReviewsByProductId() {
        // 특정 제품의 리뷰 목록 조회를 테스트하기 위해 해당 제품 리뷰 추가
        Review review1 = new Review(1L, 100L, 500L, "좋은 상품입니다.", "2023-08-03");
        Review review2 = new Review(2L, 101L, 500L, "매우 만족합니다.", "2023-08-04");
        Review review3 = new Review(3L, 102L, 501L, "그저 그래요.", "2023-08-05");

        reviewRepository.addReview(review1);
        reviewRepository.addReview(review2);
        reviewRepository.addReview(review3);

        // 특정 제품의 리뷰 목록 조회
        List<Review> productReviews = reviewRepository.getReviewsByProductId(500L);

        // 특정 제품 리뷰 목록에 추가한 리뷰가 모두 포함되어 있는지 확인
        assertEquals(2, productReviews.size(), "제품 리뷰 조회 실패: 리뷰 개수가 일치하지 않습니다.");
        assertTrue(productReviews.contains(review1), "제품 리뷰 조회 실패: 리뷰1이 포함되어 있지 않습니다.");
        assertTrue(productReviews.contains(review2), "제품 리뷰 조회 실패: 리뷰2가 포함되어 있지 않습니다.");
    }

    @Test
    void getReviewsByUserId() {
        // 특정 사용자의 리뷰 목록 조회를 테스트하기 위해 해당 사용자 리뷰 추가
        Review review1 = new Review(1L, 100L, 500L, "좋은 상품입니다.", "2023-08-03");
        Review review2 = new Review(2L, 100L, 501L, "그저 그래요.", "2023-08-04");
        Review review3 = new Review(3L, 101L, 500L, "매우 만족합니다.", "2023-08-05");

        reviewRepository.addReview(review1);
        reviewRepository.addReview(review2);
        reviewRepository.addReview(review3);

        // 특정 사용자의 리뷰 목록 조회
        List<Review> userReviews = reviewRepository.getReviewsByUserId(100L);

        // 특정 사용자 리뷰 목록에 추가한 리뷰가 모두 포함되어 있는지 확인
        assertEquals(2, userReviews.size(), "사용자 리뷰 조회 실패: 리뷰 개수가 일치하지 않습니다.");
        assertTrue(userReviews.contains(review1), "사용자 리뷰 조회 실패: 리뷰1이 포함되어 있지 않습니다.");
        assertTrue(userReviews.contains(review2), "사용자 리뷰 조회 실패: 리뷰2가 포함되어 있지 않습니다.");
    }

    @Test
    void updateReview() {
        // 리뷰 추가
        Review review = new Review(1L, 100L, 500L, "좋은 상품입니다.", "2023-08-03");
        reviewRepository.addReview(review);

        // 리뷰 수정
        review.setReview("매우 좋은 상품입니다.");
        reviewRepository.updateReview(review);

        // 수정된 리뷰를 조회하여 수정된 내용이 반영되었는지 확인
        Review updatedReview = reviewRepository.getReviewById(1L);
        assertEquals("매우 좋은 상품입니다.", updatedReview.getReview(), "리뷰 수정 실패: 리뷰 내용이 반영되지 않았습니다.");
    }

    @Test
    void deleteReview() {
        // 리뷰 추가
        Review review = new Review(1L, 100L, 500L, "좋은 상품입니다.", "2023-08-03");
        reviewRepository.addReview(review);

        // 리뷰 삭제
        reviewRepository.deleteReview(1L);

        // 삭제된 리뷰를 조회하여 리뷰가 존재하지 않는지 확인
        Review deletedReview = reviewRepository.getReviewById(1L);
        assertNull(deletedReview, "리뷰 삭제 실패: 리뷰가 삭제되지 않았습니다.");
    }
}
*/
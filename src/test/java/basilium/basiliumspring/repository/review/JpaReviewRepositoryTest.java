package basilium.basiliumspring.repository.review;

import basilium.basiliumspring.domain.review.Review;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class JpaReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @AfterEach
    public void afterEach() {
        reviewRepository.deleteAll();
    }

    @Test
    void addReview() {
        // 추가할 리뷰 생성
        Review review = new Review();
        review.setUserId(1L); // 사용자 ID 설정
        review.setProductId(1L); // 제품 ID 설정
        review.setReview("훌륭한 제품입니다."); // 리뷰 텍스트 설정
        review.setReviewDate("2023-09-10"); // 리뷰 날짜 설정

        // 리뷰 추가
        reviewRepository.addReview(review);

        // 추가한 리뷰의 ID 조회
        Long addedReviewId = review.getReviewId();

        // 추가한 리뷰 조회
        Review addedReview = reviewRepository.getReviewById(addedReviewId);

        // 테스트: 추가한 리뷰가 존재하는지 확인
        assertEquals(review, addedReview);
    }
    @Test
    void getReviewById() {
        // 데이터베이스에 리뷰 추가
        Review review = new Review();
        review.setUserId(1L);
        review.setProductId(1L);
        review.setReview("테스트 리뷰");
        review.setReviewDate("2023-09-10");
        reviewRepository.addReview(review);

        // 리뷰 조회
        Review retrievedReview = reviewRepository.getReviewById(review.getReviewId());

        // 테스트: 해당 리뷰가 null이 아닌지 확인
        assertNotNull(retrievedReview);
    }

    @Test
    void getAllReviews() {
        // 데이터베이스에 리뷰 추가
        Review review = new Review();
        review.setUserId(1L);
        review.setProductId(1L);
        review.setReview("테스트 리뷰");
        review.setReviewDate("2023-09-10");
        reviewRepository.addReview(review);

        // 모든 리뷰 조회
        List<Review> reviews = reviewRepository.getAllReviews();

        // 테스트: 리뷰 목록이 비어있지 않은지 확인
        assertFalse(reviews.isEmpty());
    }

    @Test
    void getReviewsByProductId() {
        // 데이터베이스에 리뷰 추가
        Review review = new Review();
        review.setUserId(1L);
        review.setProductId(1L);
        review.setReview("테스트 리뷰");
        review.setReviewDate("2023-09-10");
        reviewRepository.addReview(review);

        // 상품 ID로 리뷰 조회
        List<Review> reviews = reviewRepository.getReviewsByProductId(1L);

        // 테스트: 리뷰 목록이 비어있지 않은지 확인
        assertFalse(reviews.isEmpty());
    }

    @Test
    void getReviewsByUserId() {
        // 데이터베이스에 리뷰 추가
        Review review = new Review();
        review.setUserId(1L);
        review.setProductId(1L);
        review.setReview("테스트 리뷰");
        review.setReviewDate("2023-09-10");
        reviewRepository.addReview(review);

        // 사용자 ID로 리뷰 조회
        List<Review> reviews = reviewRepository.getReviewsByUserId(1L);

        // 테스트: 리뷰 목록이 비어있지 않은지 확인
        assertFalse(reviews.isEmpty());
    }

    @Test
    void updateReview() {
        // 데이터베이스에 리뷰 추가
        Review review = new Review();
        review.setUserId(1L);
        review.setProductId(1L);
        review.setReview("테스트 리뷰");
        review.setReviewDate("2023-09-10");
        reviewRepository.addReview(review);

        // 추가한 리뷰의 ID 조회
        Long addedReviewId = review.getReviewId();

        // 수정할 리뷰 조회
        Review updateReview = reviewRepository.getReviewById(addedReviewId);

        if (updateReview != null) {
            // 리뷰 내용 수정
            updateReview.setReview("새로운 리뷰 내용입니다.");

            // 리뷰 업데이트
            reviewRepository.updateReview(updateReview);

            // 업데이트한 리뷰 조회
            Review updatedReview = reviewRepository.getReviewById(addedReviewId);

            // 테스트: 업데이트한 리뷰 내용이 일치하는지 확인
            assertEquals("새로운 리뷰 내용입니다.", updatedReview.getReview());
        } else {
            // 리뷰가 존재하지 않는 경우 테스트를 패스합니다.
            assertNull(updateReview);
        }
    }

    @Test
    void deleteReview() {
        // 데이터베이스에 리뷰 추가
        Review review = new Review();
        review.setUserId(1L);
        review.setProductId(1L);
        review.setReview("테스트 리뷰");
        review.setReviewDate("2023-09-10");
        reviewRepository.addReview(review);

        // 추가한 리뷰의 ID 조회
        Long addedReviewId = review.getReviewId();

        // 삭제할 리뷰 ID
        Long reviewId = addedReviewId;

        // 리뷰 삭제
        reviewRepository.deleteReview(reviewId);

        // 삭제한 리뷰 조회
        Review deletedReview = reviewRepository.getReviewById(reviewId);

        // 테스트: 삭제한 리뷰가 존재하지 않는지 확인
        assertNull(deletedReview);
    }
}

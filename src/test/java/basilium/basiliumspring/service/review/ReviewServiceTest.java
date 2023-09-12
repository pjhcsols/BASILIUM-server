package basilium.basiliumspring.service.review;

import basilium.basiliumspring.domain.review.Review;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class ReviewServiceTest {

    @Autowired
    private ReviewService reviewService;
    // private Review testReview; // 테스트에서 사용할 리뷰 객체


    @Test
    void addReview() {
        // 새 리뷰 생성
        Review review = new Review();
        review.setUserId(1L);
        review.setProductId(1L);
        review.setReview("테스트 리뷰 내용");
        review.setReviewDate("2023-09-10"); // 리뷰 날짜 설정
        int rating = 5; // 평점 설정
        // review.setRating(5); // 평점 설정
        // 리뷰 추가
        reviewService.addReview(review, rating);

        // 리뷰 ID가 설정되었는지 확인
        assertNotNull(review.getReviewId());
    }

    @Test
    void updateReview() {
        Review review = new Review();
        review.setUserId(1L);
        review.setProductId(1L);
        review.setReview("테스트 리뷰 내용");
        review.setReviewDate("2023-09-10");
        int rating = 5; // 평점 설정
        reviewService.addReview(review, rating);
        //이까지 리뷰 설정

        // 기존 리뷰 가져오기 (리뷰 ID가 있는 가정 하에)
        Review existingReview = reviewService.getReviewById(1L);
        assertNotNull(existingReview);

        // 리뷰 내용 수정
        existingReview.setReview("수정된 리뷰 내용");

        // 리뷰 업데이트
        reviewService.updateReview(existingReview);

        // 업데이트된 리뷰 가져오기
        Review updatedReview = reviewService.getReviewById(existingReview.getReviewId());
        assertEquals("수정된 리뷰 내용", updatedReview.getReview());
    }

    @Test
    void deleteReview() {
        Review review = new Review();
        review.setUserId(1L);
        review.setProductId(1L);
        review.setReview("테스트 리뷰 내용");
        review.setReviewDate("2023-09-10");
        int rating = 5; // 평점 설정
        reviewService.addReview(review, rating);
        //이까지 리뷰 설정

        // 삭제할 리뷰 ID 지정
        Long reviewId = 1L;

        // 리뷰 삭제
        reviewService.deleteReview(reviewId);

        // 삭제 후 리뷰를 가져와서 확인 (null 이어야 함)
        Review deletedReview = reviewService.getReviewById(reviewId);
        assertNull(deletedReview);
    }

    @Test
    void getReviewById() {
        Review review = new Review();
        review.setUserId(1L);
        review.setProductId(1L);
        review.setReview("테스트 리뷰 내용");
        review.setReviewDate("2023-09-10");
        int rating = 5; // 평점 설정
        reviewService.addReview(review, rating);
        //이까지 리뷰 설정

        // 특정 리뷰 ID로 리뷰 가져오기
        review = reviewService.getReviewById(1L);

        // 리뷰가 null이 아닌지 확인
        assertNotNull(review);
    }

    @Test
    void getAllReviews() {
        Review review = new Review();
        review.setUserId(1L);
        review.setProductId(1L);
        review.setReview("테스트 리뷰 내용");
        review.setReviewDate("2023-09-10");
        int rating = 5; // 평점 설정
        reviewService.addReview(review, rating);
        //이까지 리뷰 설정

        // 모든 리뷰 가져오기
        List<Review> reviews = reviewService.getAllReviews();

        // 리뷰 목록이 null이 아니며, 비어 있지 않은지 확인
        assertNotNull(reviews);
        assertFalse(reviews.isEmpty());
    }

    @Test
    void getReviewsByProductId() {
        Review review = new Review();
        review.setUserId(1L);
        review.setProductId(1L);
        review.setReview("테스트 리뷰 내용");
        review.setReviewDate("2023-09-10");
        int rating = 5; // 평점 설정
        reviewService.addReview(review, rating);
        //이까지 리뷰 설정

        // 특정 제품 ID에 해당하는 리뷰 목록 가져오기
        List<Review> reviews = reviewService.getReviewsByProductId(1L);

        // 리뷰 목록이 null이 아니며, 비어 있지 않은지 확인
        assertNotNull(reviews);
        assertFalse(reviews.isEmpty());
    }

    @Test
    void getReviewsByUserId() {
        Review review = new Review();
        review.setUserId(1L);
        review.setProductId(1L);
        review.setReview("테스트 리뷰 내용");
        review.setReviewDate("2023-09-10");
        int rating = 5; // 평점 설정
        reviewService.addReview(review, rating);
        //이까지 리뷰 설정

        // 특정 사용자 ID에 해당하는 리뷰 목록 가져오기
        List<Review> reviews = reviewService.getReviewsByUserId(1L);

        // 리뷰 목록이 null이 아니며, 비어 있지 않은지 확인
        assertNotNull(reviews);
        assertFalse(reviews.isEmpty());
    }

    @Test
    void calculateAverageRating() {
        Review review = new Review();
        review.setUserId(1L);
        review.setProductId(1L);
        review.setReview("테스트 리뷰 내용");
        review.setReviewDate("2023-09-10");
        int rating = 5; // 평점 설정
        reviewService.addReview(review, rating);
        //이까지 리뷰 설정

        // 특정 제품의 평균 평점 계산
        double averageRating = reviewService.calculateAverageRating(1L);

        // 평균 평점이 0보다 크거나 같은지 확인
        assertTrue(averageRating >= 0);
    }
}
package basilium.basiliumspring.repository.review;

import basilium.basiliumspring.domain.review.Review;

import java.util.List;

public interface ReviewRepository { //CRUD
    // 리뷰 추가
    void addReview(Review review); //사용자

    // 리뷰 ID로 특정 리뷰 조회
    Review getReviewById(Long reviewId); //관리자

    // 모든 리뷰 조회
    List<Review> getAllReviews(); //관리자

    // 특정 제품의 리뷰 목록 조회
    List<Review> getReviewsByProductId(Long productId); //관리자

    // List<Review> getReviewsByProductName(String productName); //관리자 domain 에서 정의 안됨

    // 특정 사용자의 리뷰 목록 조회
    List<Review> getReviewsByUserId(Long userId); //관리자

    // 리뷰 수정
    void updateReview(Review review); //관리자

    // 리뷰 삭제
    void deleteReview(Long reviewId); //관리자
}
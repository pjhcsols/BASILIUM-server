package basilium.basiliumspring.repository.review;

import basilium.basiliumspring.domain.review.Review;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;


public class JpaReviewRepository implements ReviewRepository {

    private EntityManager em;

    public JpaReviewRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void addReview(Review review) {
        em.persist(review); // 리뷰를 데이터베이스에 추가
    }

    @Override
    public Review getReviewById(Long reviewId) {
        return em.find(Review.class, reviewId); // 특정 리뷰 ID로 리뷰 조회
    }

    @Override
    public List<Review> getAllReviews() {
        TypedQuery<Review> query = em.createQuery("SELECT r FROM Review r", Review.class);
        return query.getResultList(); // 모든 리뷰를 조회
    }

    @Override
    public List<Review> getReviewsByProductId(Long productId) {
        TypedQuery<Review> query = em.createQuery("SELECT r FROM Review r WHERE r.productId = :productId", Review.class);
        query.setParameter("productId", productId);
        return query.getResultList(); // 특정 상품 ID에 해당하는 리뷰 조회
    }

    @Override
    public List<Review> getReviewsByUserId(Long userId) {
        TypedQuery<Review> query = em.createQuery("SELECT r FROM Review r WHERE r.userId = :userId", Review.class);
        query.setParameter("userId", userId);
        return query.getResultList(); // 특정 사용자 ID에 해당하는 리뷰 조회
    }
    // 기존 객체 찾아서 꺼낸다음 그 객체 setter 사용해서 수정후 merge
    @Override
    public void updateReview(Review review) {
        // 주어진 리뷰의 ID를 사용하여 기존 리뷰를 찾아서 업데이트
        Review existingReview = em.find(Review.class, review.getReviewId());
        if (existingReview != null) {
            // 기존 리뷰 속성을 업데이트
            existingReview.setUserId(review.getUserId());
            existingReview.setProductId(review.getProductId());
            existingReview.setReview(review.getReview());
            existingReview.setReviewDate(review.getReviewDate());

            // 리뷰 업데이트
            em.merge(existingReview);
        }
    }

    @Override
    public void deleteReview(Long reviewId) {
        Review review = em.find(Review.class, reviewId);
        if (review != null) {
            em.remove(review); // 특정 리뷰 삭제
        }
    }
    @Override
    public void deleteAll() {
        em.createQuery("delete from Review").executeUpdate();
    }
}


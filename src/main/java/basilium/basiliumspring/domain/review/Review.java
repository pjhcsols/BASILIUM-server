package basilium.basiliumspring.domain.review;


import javax.persistence.*;


@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;
    private String userId;
    private Long productId;
    private String review;
    private String reviewDate;
    private int rating; // 리뷰 평점

    public Review(){

    }
/*
    public Review(Long reviewId, Long userId, Long productId, String review, String reviewDate) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.productId = productId;
        this.review = review;
        this.reviewDate = reviewDate;
    }
*/
    public int getRating() {return rating;}

    public void setRating(int rating) {this.rating = rating;}

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }
}

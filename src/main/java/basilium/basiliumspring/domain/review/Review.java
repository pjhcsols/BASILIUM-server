package basilium.basiliumspring.domain.review;

public class Review {
    private Long reviewId;
    private Long userId;
    private Long productId;
    private String review;
    private String reviewDate;

    public Review(Long reviewId, Long userId, Long productId, String review, String reviewDate) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.productId = productId;
        this.review = review;
        this.reviewDate = reviewDate;
    }


    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
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

package basilium.basiliumspring.domain.reviewcomment;

public class ReviewComment {
    Long commentId;
    Long reviewId;
    String reviewComment;
    String commentDate;

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public String getReviewComment() {
        return reviewComment;
    }

    public void setReviewComment(String reviewComment) {
        this.reviewComment = reviewComment;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    public ReviewComment(Long commentId, Long reviewId, String reviewComment, String commentDate){
        this.commentId = commentId;
        this.reviewId = reviewId;
        this.reviewComment = reviewComment;
        this.commentDate = commentDate;
    }




}

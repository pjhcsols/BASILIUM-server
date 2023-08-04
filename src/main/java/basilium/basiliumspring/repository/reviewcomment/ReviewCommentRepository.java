package basilium.basiliumspring.repository.reviewcomment;

import basilium.basiliumspring.domain.reviewcomment.ReviewComment;

import java.util.List;

public interface ReviewCommentRepository {
    // 댓글을 추가하는 메서드
    void addReviewComment(ReviewComment reviewComment);

    // 댓글을 수정하는 메서드
    void updateReviewComment(Long commentId, String updatedComment);

    // 댓글을 삭제하는 메서드
    void deleteReviewComment(Long commentId);

    // 리뷰에 대한 모든 댓글 목록을 가져오는 메서드
    List<ReviewComment> getAllReviewComments(Long reviewId);

    // 특정 댓글을 가져오는 메서드
    ReviewComment getReviewComment(Long commentId);
}

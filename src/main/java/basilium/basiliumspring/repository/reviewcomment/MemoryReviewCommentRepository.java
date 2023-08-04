package basilium.basiliumspring.repository.reviewcomment;

import basilium.basiliumspring.domain.reviewcomment.ReviewComment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryReviewCommentRepository implements ReviewCommentRepository {
    private Map<Long, ReviewComment> reviewCommentsMap;

    public MemoryReviewCommentRepository() {
        reviewCommentsMap = new HashMap<>();
    }

    @Override
    public void addReviewComment(ReviewComment reviewComment) {
        reviewCommentsMap.put(reviewComment.getCommentId(), reviewComment);
    }

    @Override
    public void updateReviewComment(Long commentId, String updatedComment) {
        ReviewComment reviewComment = reviewCommentsMap.get(commentId);
        if (reviewComment != null) {
            reviewComment.setReviewComment(updatedComment);
            reviewCommentsMap.put(commentId, reviewComment);
        }
    }

    @Override
    public void deleteReviewComment(Long commentId) {
        reviewCommentsMap.remove(commentId);
    }

    @Override
    public List<ReviewComment> getAllReviewComments(Long reviewId) {
        List<ReviewComment> reviewComments = new ArrayList<>();
        for (ReviewComment reviewComment : reviewCommentsMap.values()) {
            if (reviewComment.getReviewId().equals(reviewId)) {
                reviewComments.add(reviewComment);
            }
        }
        return reviewComments;
    }

    @Override
    public ReviewComment getReviewComment(Long commentId) {
        return reviewCommentsMap.get(commentId);
    }

}

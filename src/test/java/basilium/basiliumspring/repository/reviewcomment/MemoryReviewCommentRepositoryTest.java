package basilium.basiliumspring.repository.reviewcomment;

import basilium.basiliumspring.domain.reviewcomment.ReviewComment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class MemoryReviewCommentRepositoryTest {
    private MemoryReviewCommentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new MemoryReviewCommentRepository();
        // 테스트를 위해 미리 몇 개의 댓글 데이터를 추가해줄 수 있습니다.
        repository.addReviewComment(new ReviewComment(1L, 1L, "First comment", "2023-08-04"));
        repository.addReviewComment(new ReviewComment(2L, 1L, "Second comment", "2023-08-05"));
        repository.addReviewComment(new ReviewComment(3L, 2L, "Third comment", "2023-08-06"));
    }

    @Test
    void testAddReviewComment() {
        ReviewComment newComment = new ReviewComment(4L, 2L, "New comment", "2023-08-07");
        repository.addReviewComment(newComment);

        ReviewComment retrievedComment = repository.getReviewComment(4L);
        assertEquals(newComment, retrievedComment);
    }

    @Test
    void testUpdateReviewComment() {
        Long commentIdToUpdate = 1L;
        String updatedCommentText = "Updated comment";
        repository.updateReviewComment(commentIdToUpdate, updatedCommentText);

        ReviewComment updatedComment = repository.getReviewComment(commentIdToUpdate);
        assertEquals(updatedCommentText, updatedComment.getReviewComment());
    }

    @Test
    void testDeleteReviewComment() {
        Long commentIdToDelete = 3L;
        repository.deleteReviewComment(commentIdToDelete);

        ReviewComment deletedComment = repository.getReviewComment(commentIdToDelete);
        assertNull(deletedComment);
    }

    @Test
    void testGetAllReviewComments() {
        Long reviewId = 1L;
        List<ReviewComment> commentsForReview1 = repository.getAllReviewComments(reviewId);

        // 리뷰 ID가 1인 댓글들이 두 개가 있어야 합니다.
        assertEquals(2, commentsForReview1.size());
    }

    @Test
    void testGetReviewComment() {
        Long commentId = 2L;
        ReviewComment comment = repository.getReviewComment(commentId);

        // 댓글 ID가 2인 댓글이 존재해야 합니다.
        assertEquals(commentId, comment.getCommentId());
    }
}
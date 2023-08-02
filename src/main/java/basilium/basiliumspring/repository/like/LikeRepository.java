package basilium.basiliumspring.repository.like;

import basilium.basiliumspring.domain.like.Like;

import java.util.Optional;

public interface LikeRepository {
    public long countByProductId(Long productId);
    public long countByUserId(String UserId);
    public Like doLike(Like like);
    public Optional<Like> unDoLike(Like like);
    public void deleteAll();
}

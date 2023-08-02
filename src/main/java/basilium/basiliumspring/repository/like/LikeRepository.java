package basilium.basiliumspring.repository.like;

import basilium.basiliumspring.domain.like.Like;

public interface LikeRepository {
    public long countByProductId(Long productId);
    public long countByUserId(String UserId);
    public void doLike(Like like);
    public void unDoLike(Like like);
}

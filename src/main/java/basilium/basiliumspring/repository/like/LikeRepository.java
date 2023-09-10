package basilium.basiliumspring.repository.like;

import basilium.basiliumspring.domain.like.LikeDTO;

import java.util.List;
import java.util.Optional;

public interface LikeRepository {
    public long countByProductId(Long productId);
    public long countByUserId(String UserId);
    public LikeDTO doLike(LikeDTO likeDTO);
    public Optional<LikeDTO> unDoLike(LikeDTO likeDTO);
    public List<LikeDTO> getLikeListByUserId(String userId);
    public void deleteAll();
}

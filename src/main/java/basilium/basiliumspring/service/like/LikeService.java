package basilium.basiliumspring.service.like;

import basilium.basiliumspring.domain.like.LikeDTO;
import basilium.basiliumspring.repository.like.LikeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Slf4j
public class LikeService {
    private final LikeRepository likeRepository;

    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    public void likeProduct(LikeDTO likeDTO){
        likeRepository.doLike(likeDTO);
    }
    public Optional<LikeDTO> unLikeProduct(LikeDTO likeDTO){
        return likeRepository.unDoLike(likeDTO);
    }

    public List<LikeDTO> getUserLikeInfo(String userId){
        List<LikeDTO> ret = likeRepository.getLikeListByUserId(userId);
        log.info(ret.get(0).getUserId());
        return ret;
    }
}

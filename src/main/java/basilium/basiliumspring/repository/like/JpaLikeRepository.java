package basilium.basiliumspring.repository.like;

import basilium.basiliumspring.domain.like.LikeDTO;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

public class JpaLikeRepository implements LikeRepository{

    private EntityManager em;

    public JpaLikeRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public long countByProductId(Long productId) {
        return em.createQuery("select count(u) from LikeDTO u where u.productId = :productId", Long.class).setParameter("productId", productId).getSingleResult();
    }

    @Override
    public long countByUserId(String UserId) {
        return em.createQuery("select count(u) from LikeDTO u where u.userId = :userId", Long.class).setParameter("userId", UserId).getSingleResult();
    }

    @Override
    public LikeDTO doLike(LikeDTO likeDTO) {
        em.persist(likeDTO);
        return likeDTO;
    }

    @Override
    public Optional<LikeDTO> unDoLike(LikeDTO likeDTO) {
        try {
            LikeDTO ret = em.createQuery("select m from LikeDTO m where m.userId = :userId and m.productId = :productId", LikeDTO.class)
                    .setParameter("userId", likeDTO.getUserId())
                    .setParameter("productId", likeDTO.getProductId())
                    .getSingleResult();
            em.remove(ret);
            return Optional.of(ret);
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    @Override
    public void deleteAll() {
        em.createQuery("delete from LikeDTO").executeUpdate();
    }

    @Override
    public List<LikeDTO> getLikeListByUserId(String userId) {
        return em.createQuery("select m from LikeDTO m where m.userId = :userId", LikeDTO.class).setParameter("userId", userId)
                .getResultList();
    }
}

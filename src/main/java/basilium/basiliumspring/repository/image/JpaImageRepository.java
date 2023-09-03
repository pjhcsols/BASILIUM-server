package basilium.basiliumspring.repository.image;

import basilium.basiliumspring.domain.image.Image;
import basilium.basiliumspring.domain.product.Product;
import basilium.basiliumspring.domain.user.NormalUser;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JpaImageRepository implements ImageRepository{

    private EntityManager em;

    public JpaImageRepository(EntityManager em) {
        this.em = em;
    }
    @Override
    public void saveImage(Image image) {
        em.persist(image);
    }

    @Override
    public Optional<Image> findById(Long id) {
        Image result = em.find(Image.class, id);
        return Optional.ofNullable(result);
    }

    @Override
    public List<Image> findByProductId(Long productId) {
        return em.createQuery("select m from Image m where m.productId = :productId", Image.class).setParameter("productId", productId).getResultList();
    }

    @Override
    public void deleteImageById(Long id) {
        Image image = em.find(Image.class, id);
        if (image != null) {
            em.remove(image);
        }
    }

    @Override
    public void deleteImageByProductId(Long productId) {
        em.createQuery("DELETE FROM Image i WHERE i.productId = :productId")
                .setParameter("productId", productId)
                .executeUpdate();
    }
}

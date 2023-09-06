package basilium.basiliumspring.repository.product;

import basilium.basiliumspring.domain.product.Image;
import basilium.basiliumspring.domain.product.Product;
import basilium.basiliumspring.domain.user.SuperUser;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaImageRepository implements ImageRepository{

    private EntityManager em;
    public JpaImageRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void addImage(Image image) {
        em.persist(image);
    }

    @Override
    public void modifyImageById(Image image) {
        Image originalImage = em.find(Image.class, image.getImageId());

        if (originalImage != null) {
            originalImage.setImageId(image.getImageId());
            originalImage.setImagePath(image.getImagePath());
            originalImage.setProductId(image.getProductId());
            em.merge(originalImage); // 변경 내용 저장
        }
    }

    @Override
    public void deleteImageById(Long imageId) {
        Image image = em.find(Image.class, imageId);
        if (image != null){
            em.remove(image);
        }
    }

    @Override
    public void deleteImageByProductId(Long productId) {
        em.createQuery("delete  from Image i where i.productId = :productId").setParameter("productId", productId).executeUpdate();
    }

    @Override
    public Optional<Image> getImageById(Long imageId) {
        Image image = em.find(Image.class, imageId);
        return Optional.ofNullable(image);
    }

    @Override
    public List<Image> getImageByProductId(Long productId) {
        return em.createQuery("select i from Image i where i.productId = :productId", Image.class).setParameter("productId", productId)
                .getResultList();
    }

    @Override
    public Long countImagesByProductId(Long productId) {
        return em.createQuery("select count(i) from Image i where i.productId = :productId", Long.class).setParameter("productId", productId).getSingleResult();
    }


}

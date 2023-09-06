package basilium.basiliumspring.repository.product;

import basilium.basiliumspring.domain.product.Image;

import java.util.List;
import java.util.Optional;

public interface ImageRepository {
    void addImage(Image image);
    void modifyImageById(Image image);
    void deleteImageById(Long imageId);

    void deleteImageByProductId(Long productId);

    Long countImagesByProductId(Long productId);

    Optional<Image> getImageById(Long imageId);

    List<Image> getImageByProductId(Long productId);

}

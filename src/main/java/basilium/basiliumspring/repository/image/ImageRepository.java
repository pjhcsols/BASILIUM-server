package basilium.basiliumspring.repository.image;

import basilium.basiliumspring.domain.image.Image;
import basilium.basiliumspring.domain.like.Like;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ImageRepository {

    public void saveImage(Image image);
    public Optional<Image> findById(Long id);
    public List<Image> findByProductId(Long productId);
    public void deleteImageById(Long id);
    public void deleteImageByProductId(Long productId);

}

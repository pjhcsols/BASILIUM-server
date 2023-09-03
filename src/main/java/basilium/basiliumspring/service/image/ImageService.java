package basilium.basiliumspring.service.image;

import basilium.basiliumspring.domain.image.Image;
import basilium.basiliumspring.domain.product.Product;
import basilium.basiliumspring.repository.image.ImageRepository;
import basilium.basiliumspring.repository.product.ProductRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class ImageService {
    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public void registerImage(Image image) {
        imageRepository.saveImage(image);
    }

    public Image sendImageById(Long id) {
        return imageRepository.findById(id).get();
    }

    public List<Image> sendImageByProductId(Long productId) {
        return imageRepository.findByProductId(productId);
    }

    public void deleteImageById(Long id){
        Image result = imageRepository.findById(id).get();
        if(result != null)imageRepository.deleteImageById(id);
    }

    public void deleteImageByProductId(Long productId){
        List<Image>result = imageRepository.findByProductId(productId);
        if (result.size() != 0)imageRepository.deleteImageByProductId(productId);
    }
}

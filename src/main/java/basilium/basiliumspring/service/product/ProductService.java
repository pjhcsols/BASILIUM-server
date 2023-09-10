package basilium.basiliumspring.service.product;

import basilium.basiliumspring.domain.product.Image;
import basilium.basiliumspring.domain.product.Product;
import basilium.basiliumspring.repository.product.ImageRepository;
import basilium.basiliumspring.repository.product.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.buf.UriUtil;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.util.UriUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Slf4j
public class ProductService {

    private String UPLOAD_DIR = "C:/Users/kimmo/Desktop/img/";
    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;
    public ProductService(ProductRepository productRepository, ImageRepository imageRepository) {
        this.productRepository = productRepository;
        this.imageRepository = imageRepository;
    }

    public void addProduct(Product product) {
        productRepository.addProduct(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    public Product getProductById(Long productId) {
        return productRepository.getProductById(productId).get();
    }

    public void updateProduct(Product updatedProduct) {
        productRepository.updateProduct(updatedProduct);
    }

    public void deleteProductById(Long productId) {
        productRepository.deleteProductById(productId);
    }

    public void deleteProductByName(String productName) {
        productRepository.deleteProductByName(productName);
    }

    public ResponseEntity<String> savePhotoFiles(MultipartFile [] files, Long productId){
        List<String> uploadedFiles = new ArrayList<>();
        int cnt = 1;
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                try {
                    // 업로드 파일명 설정
                    String originalFileName = file.getOriginalFilename();
                    String newFileName = productId.toString() + "_" + Integer.toString(cnt++) + ".png";
                    File newFile = new File(UPLOAD_DIR + newFileName);

                    //Image 객체 생성
                    Image newImage = new Image();
                    newImage.setProductId(productId);
                    newImage.setImagePath(UPLOAD_DIR + newFileName);
                    imageRepository.addImage(newImage);

                    // 파일 저장
                    file.transferTo(newFile);

                    uploadedFiles.add(newFileName);
                } catch (IOException e) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Image upload failed");
                }
            }
        }

        if (!uploadedFiles.isEmpty()) {
            // 업로드 된 파일 목록을 로그에 기록
            for (String fileName : uploadedFiles) {
                log.info("Uploaded file: " + fileName);
            }

            return ResponseEntity.status(HttpStatus.CREATED).body("Images uploaded and saved successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No images selected.");
        }
    }

    public Resource loadFiles(Long productId, Long num) {
        List<Image> imageArr = imageRepository.getImageByProductId(productId);

        for (Image image : imageArr) {
            int idx1 = image.getImagePath().indexOf("_");
            int idx2 = image.getImagePath().indexOf(".");
            String imageNum = image.getImagePath().substring(idx1 + 1, idx2);
            if (Integer.parseInt(imageNum) == num) {
                try {
                    // 파일 경로가 올바르게 지정되어 있어야 합니다.
                    Path filePath = Paths.get(image.getImagePath());
                    return new UrlResource(filePath.toUri());
                } catch (MalformedURLException e) {
                    throw new RuntimeException("Malformed URL: " + image.getImagePath(), e);
                }
            }
        }

        return null;
    }

    public Long countPhotos(Long productId){
        return imageRepository.countImagesByProductId(productId);
    }
}

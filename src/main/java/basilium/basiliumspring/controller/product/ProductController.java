package basilium.basiliumspring.controller.product;

import basilium.basiliumspring.domain.product.Product;
import basilium.basiliumspring.service.product.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/products")
public class ProductController {
    //상품전체목록 보기
    //상품 상세보기
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //post 요청 /products/add
    @PostMapping("/upload")
    public ResponseEntity<String> uploadProduct(@ModelAttribute Product product, @RequestParam("files") MultipartFile[] files) {
        productService.addProduct(product);

        ResponseEntity<String>ret = productService.savePhotoFiles(files, product.getProductId());

        if (ret.getStatusCode() == HttpStatus.BAD_REQUEST){
            return ret;
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(product.getProductId().toString());
    }
    @GetMapping("countPhotos/{productId}")
    public ResponseEntity<Long> countPhotosByProductId(@PathVariable("productId")Long productId){
        Long result = productService.countPhotos(productId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @GetMapping("/downloadProductPhotos/{productId}")
    public ResponseEntity<byte[]> downloadProductPhotos(@PathVariable("productId") Long productId, @RequestParam("num") Long num){
        Resource imageResource = productService.loadFiles(productId, num);
        try {
            InputStream inputStream = imageResource.getInputStream();
            byte[] imageBytes = StreamUtils.copyToByteArray(inputStream);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG); // 이미지 타입에 맞게 설정

            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        Product product = productService.getProductById(productId);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{productId}")
    public ResponseEntity<String> updateProduct(@PathVariable Long productId, @RequestBody Product updatedProduct) {
        updatedProduct.setProductId(productId);
        productService.updateProduct(updatedProduct);
        return ResponseEntity.ok("Product updated successfully");
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long productId) {
        productService.deleteProductById(productId);
        return ResponseEntity.ok("Product deleted successfully");
    }

    @DeleteMapping("/name/{productName}")
    public ResponseEntity<String> deleteProductByName(@PathVariable String productName) {
        productService.deleteProductByName(productName);
        return ResponseEntity.ok("Products with name " + productName + " deleted successfully");
    }


}

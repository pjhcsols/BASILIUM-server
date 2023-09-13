package basilium.basiliumspring.controller.product;

import basilium.basiliumspring.domain.product.Product;
import basilium.basiliumspring.service.product.ProductService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
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
    private final ObjectMapper objectMapper;

    @Autowired
    public ProductController(ProductService productService, ObjectMapper objectMapper) {
        this.productService = productService;
        this.objectMapper = objectMapper;

    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadProduct(@RequestParam("product") String strProduct, @RequestPart("files") MultipartFile[] files) {
        try {
            log.info(strProduct);
            // JSON 문자열을 Product 객체로 변환
            JsonNode rootNode = objectMapper.readTree(strProduct);
            Product product = new Product();
            product.setProductName(rootNode.get("productName").asText());
            product.setProductDesc(rootNode.get("productDesc").asText());
            product.setProductCategoryId(rootNode.get("productCategoryId").asLong());
            product.setProductPrice(rootNode.get("productPrice").asLong());
            productService.addProduct(product);
            log.info("여기서 까지 OK");
            ResponseEntity<String> ret = productService.savePhotoFiles(files, product.getProductId());

            if (ret.getStatusCode() == HttpStatus.BAD_REQUEST) {
                return ret;
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(product.getProductId().toString());
        } catch (Exception e) {
            // JSON 파싱 오류 처리
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid JSON data");
        }
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

    @GetMapping("/productsInfo")
    public ResponseEntity<List<Product>> getProductsInfoByList(@RequestParam List<Long> values){
        List<Product> resultList = productService.getProductInfosByList(values);
        return ResponseEntity.ok(resultList);
    }

}

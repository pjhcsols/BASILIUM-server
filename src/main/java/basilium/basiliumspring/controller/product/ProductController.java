package basilium.basiliumspring.controller.product;

import basilium.basiliumspring.domain.product.Product;
import basilium.basiliumspring.service.product.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/products")
public class ProductController {
    //상품전체목록 보기
    //상품 상세보기
    private static final String UPLOAD_DIR = "C:/Users/kimmo/Desktop/img/";
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //post 요청 /products/add
    @PostMapping("/add")
    public ResponseEntity<String> registerProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product.getProductId().toString());
    }
    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("imageFile") MultipartFile imageFile) {
        log.info(imageFile.getName());
        log.info(imageFile.getOriginalFilename());
        if (!imageFile.isEmpty()) {
            try {
                // 업로드 파일명 설정
                String originalFileName = imageFile.getOriginalFilename();
                String newFileName = System.currentTimeMillis() + "_" + originalFileName;
                File newFile = new File(UPLOAD_DIR + newFileName);

                // 파일 저장
                imageFile.transferTo(newFile);

                return ResponseEntity.status(HttpStatus.CREATED).body("Image uploaded and saved successfully!");
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Image upload failed");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No image selected.");
        }
    }

    /*
    @PostMapping("/{categoryId}")
    public ResponseEntity<String> addProduct(@RequestBody Product product, @PathVariable Long categoryId) {
        productService.addProductAndMapToCategory(product, categoryId);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product added successfully");
    }
    */


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

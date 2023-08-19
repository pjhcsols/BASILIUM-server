package basilium.basiliumspring.controller.product;

import basilium.basiliumspring.domain.product.Product;
import basilium.basiliumspring.service.product.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //post 요청 /products/add
    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product added successfully");
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

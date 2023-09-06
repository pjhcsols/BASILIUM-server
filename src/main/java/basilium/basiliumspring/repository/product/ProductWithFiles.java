package basilium.basiliumspring.repository.product;

import basilium.basiliumspring.domain.product.Product;
import org.springframework.web.multipart.MultipartFile;

public class ProductWithFiles {
    Product product;
    MultipartFile [] files;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public MultipartFile[] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }
}

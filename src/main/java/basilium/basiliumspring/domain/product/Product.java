package basilium.basiliumspring.domain.product;

public class Product {
    private Long productId;
    private Long productCategoryId;
    private String productName;
    private Long productPrice;
    private String productDesc;
    private String productPhotoUrl;


    public Product(Long productId, Long productCategoryId, String productName, Long productPrice,
                   String productDesc, String productPhotoUrl) {
        this.productId = productId;
        this.productCategoryId = productCategoryId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDesc = productDesc;
        this.productPhotoUrl = productPhotoUrl;
    }

    // Getter and Setter methods for each field
    // (Omitted for brevity, but you should include them in your actual code)
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Long productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductPhotoUrl() {
        return productPhotoUrl;
    }

    public void setProductPhotoUrl(String productPhotoUrl) {
        this.productPhotoUrl = productPhotoUrl;
    }


}

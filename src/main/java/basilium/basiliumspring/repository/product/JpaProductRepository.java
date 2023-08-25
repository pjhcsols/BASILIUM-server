package basilium.basiliumspring.repository.product;

import basilium.basiliumspring.domain.product.Product;
import basilium.basiliumspring.domain.user.NormalUser;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public class JpaProductRepository implements ProductRepository{

    private EntityManager em;

    public JpaProductRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void addProduct(Product product) {
        em.persist(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return em.createQuery("select m from Product m", Product.class)
                .getResultList();
    }

    @Override
    public Optional<Product> getProductById(Long productId) {
        Product result = em.find(Product.class, productId);
        return Optional.ofNullable(result);
    }

    @Override
    public Optional<Product> getProductByName(String productName) {
        Product result =  em.createQuery("SELECT p FROM Product p WHERE p.productName = :name", Product.class)
                .setParameter("name", productName)
                .getSingleResult();
        return Optional.ofNullable(result);
    }

    @Override
    public void updateProduct(Product updatedProduct) {

        Product existingProduct = em.find(Product.class, updatedProduct.getProductId());

        if (existingProduct != null) {
            existingProduct.setProductCategoryId(updatedProduct.getProductCategoryId());
            existingProduct.setProductDesc(updatedProduct.getProductDesc());
            existingProduct.setProductName(updatedProduct.getProductName());
            existingProduct.setProductPrice(updatedProduct.getProductPrice());
            existingProduct.setProductId(updatedProduct.getProductId());
            existingProduct.setProductPhotoUrl(updatedProduct.getProductPhotoUrl());
            em.merge(existingProduct); // 변경 내용 저장
        }
    }

    @Override
    public void deleteProductById(Long productId) {
        Product product = em.find(Product.class, productId);
        if (product != null) {
            em.remove(product);
        }
    }

    @Override
    public void deleteProductByName(String productName) {
        Product product = getProductByName(productName).get();
        if (product != null) {
            em.remove(product);
        }
    }

    @Override
    public void deleteAll() {
        em.createQuery("delete from Product").executeUpdate();
    }

}

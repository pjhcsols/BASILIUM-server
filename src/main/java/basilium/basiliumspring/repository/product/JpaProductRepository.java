package basilium.basiliumspring.repository.product;

import basilium.basiliumspring.domain.product.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public class JpaProductRepository implements ProductRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void addProduct(Product product) {
        entityManager.persist(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return entityManager.createQuery("SELECT p FROM Product p", Product.class)
                .getResultList();
    }

    @Override
    public Product getProductById(Long productId) {
        return entityManager.find(Product.class, productId);
    }

    @Override
    public Product getProductByName(String productName) {
        return entityManager.createQuery("SELECT p FROM Product p WHERE p.productName = :name", Product.class)
                .setParameter("name", productName)
                .getSingleResult();
    }

    @Override
    @Transactional
    public void updateProduct(Product updatedProduct) {
        entityManager.merge(updatedProduct);
    }

    @Override
    @Transactional
    public void deleteProductById(Long productId) {
        Product product = entityManager.find(Product.class, productId);
        if (product != null) {
            entityManager.remove(product);
        }
    }

    @Override
    @Transactional
    public void deleteProductByName(String productName) {
        Product product = getProductByName(productName);
        if (product != null) {
            entityManager.remove(product);
        }
    }
}

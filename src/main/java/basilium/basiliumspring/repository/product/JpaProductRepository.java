package basilium.basiliumspring.repository.product;

import basilium.basiliumspring.domain.product.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
public class JpaProductRepository implements ProductRepository{

    private EntityManager em;


    private final JpaProductRepositoryLegend jpaProductRepositoryLegend;

    public JpaProductRepository(EntityManager em, JpaProductRepositoryLegend jpaProductRepositoryLegend) {
        this.em = em;
        this.jpaProductRepositoryLegend = jpaProductRepositoryLegend;
    }



    private Long productId;
    private Long productCategoryId;
    private String productName;
    private Long productPrice;
    private String productDesc;




    /*********더미데이터 생성로직**************/
    public Product makeProduct(Long categoryId, String name, Long price, String desc){
        Product product = new Product();
        product.setProductCategoryId(categoryId);
        product.setProductName(name);
        product.setProductPrice(price);
        product.setProductDesc(desc);

        //product.setStoreImage(null);

        return product;
    }

    @PostConstruct
    public void init() {
        try {
            List<Product> products = new ArrayList<>();
            products.add(makeProduct(1L, "바실리움 후드티", 68000L, "좋은 맨투승준"));
            products.add(makeProduct(1L, "바실리움 백로고 맨투맨(블랙)", 58000L, "깔쌈한 맨투맨 등등"));
            products.add(makeProduct(1L, "바실리움 백로고 맨투맨(화이트)", 58000L, "깔쌈한 맨투맨 등등"));
            products.add(makeProduct(1L, "바실리움 백로고 맨투맨(그레이)", 58000L, "깔쌈한 맨투맨 등등"));
            products.add(makeProduct(2L, "바실리움 팬츠(블랙)", 58000L, "깔쌈한 맨투맨 등등"));
            products.add(makeProduct(2L, "바실리움 팬츠(화이트)", 58000L, "깔쌈한 맨투맨 등등"));
            products.add(makeProduct(2L, "바실리움 팬츠(그레이)", 58000L, "깔쌈한 맨투맨 등등"));
            products.add(makeProduct(1L, "바실리움 반팔(블랙)", 58000L, "깔쌈한 맨투맨 등등"));
            products.add(makeProduct(1L, "바실리움 반팔(화이트)", 58000L, "깔쌈한 맨투맨 등등"));

            for (Product product : products) {
                jpaProductRepositoryLegend.createProduct(product);
                log.info("Product inserted: {}", product.getProductName());
            }
        } catch (Exception e) {
            log.error("Error during initialization: " + e.getMessage());
        }
    }
    @Override
    public Product createProduct(Product product) {
        // 고유 ID 생성 및 설정
        //store.setStoreId(null); // ID는 자동 생성
        em.persist(product);
        return product;
    }

    /*********더미데이터 생성로직**************/





    @Override
    public void addProduct(Product product) {
        em.persist(product);
    }
/*
    @Override
    public List<Product> getAllProducts() {
        return em.createQuery("select m from Product m", Product.class)
                .getResultList();
    }
*/
    @Override
    public List<Product> getAllProducts() {
        String jpql = "SELECT s FROM Product s";
        TypedQuery<Product> query = em.createQuery(jpql,Product.class);
        return query.getResultList();
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

    @Override
    public List<Product> getProductInfos(List<Long> values) {
        return em.createQuery("select p from Product p where p.productId in :ids", Product.class).setParameter("ids", values).getResultList();
    }
}

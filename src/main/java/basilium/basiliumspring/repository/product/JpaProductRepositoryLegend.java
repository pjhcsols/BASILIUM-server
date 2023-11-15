package basilium.basiliumspring.repository.product;


import basilium.basiliumspring.domain.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
/**********더미 데이터 세팅 jpa 레전드 레포**********/


public class JpaProductRepositoryLegend {
    private final EntityManager entityManager;

    public JpaProductRepositoryLegend(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Transactional
    public Product createProduct(Product product) {
        // 고유 ID 생성 및 설정
        //store.setStoreId(null); // ID는 자동 생성
        entityManager.persist(product);
        return product;
    }
}
/**********더미 데이터 세팅 jpa 레전드 레포**********/
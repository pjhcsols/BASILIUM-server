package basilium.basiliumspring.repository.shoppingcart;

import basilium.basiliumspring.domain.product.Product;
import basilium.basiliumspring.domain.purchasetransaction.PurchaseTransaction;
import basilium.basiliumspring.domain.shopingcart.ShoppingCart;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class JpaShoppingCartRepository implements ShoppingCartRepository{

    private EntityManager em;

    public JpaShoppingCartRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void addProductToCart(ShoppingCart record) {
        em.persist(record);
    }

    @Override
    public Optional<ShoppingCart> getProductById(Long id) {
        ShoppingCart result = em.find(ShoppingCart.class, id);
        return Optional.ofNullable(result);
    }

    @Override
    public List<ShoppingCart> getAllProductsByUserId(String userId) {
        TypedQuery<ShoppingCart> result = em.createQuery("select m from ShoppingCart m where m.userId = :userId", ShoppingCart.class);
        result.setParameter("userId", userId);
        return result.getResultList();
    }

    @Override
    public void updateShoppingRecord(ShoppingCart record) {
        ShoppingCart existingShoppingCart = em.find(ShoppingCart.class, record.getOrderId());

        if (existingShoppingCart != null){
            existingShoppingCart.setOrderId(record.getOrderId());
            existingShoppingCart.setAmount(record.getAmount());
            existingShoppingCart.setProductId(record.getProductId());
            existingShoppingCart.setUserId(record.getUserId());
        }
        em.merge(existingShoppingCart);
    }

    @Override
    public void deleteShoppingRecord(ShoppingCart record) {
        ShoppingCart existingShoppingCart = em.find(ShoppingCart.class, record.getOrderId());
        if(existingShoppingCart != null){
            em.remove(existingShoppingCart);
        }
    }

    @Override
    public void deleteAllShoppingRecordByUserId(String userId) {
        em.createQuery("delete from ShoppingCart").executeUpdate();
    }
}

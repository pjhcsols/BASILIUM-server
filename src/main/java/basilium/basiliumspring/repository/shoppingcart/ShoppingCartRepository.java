package basilium.basiliumspring.repository.shoppingcart;

import basilium.basiliumspring.domain.shopingcart.ShoppingCart;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartRepository {
    void addProductToCart(ShoppingCart record);
    Optional<ShoppingCart> getProductById(Long id);
    List<ShoppingCart> getAllProductsByUserId(String userId);
    void updateShoppingRecord(ShoppingCart record);
    void deleteShoppingRecord(ShoppingCart record);//product id 로 지우기
    void deleteAllShoppingRecordByUserId(String userId);//product name 으로 지우기
}

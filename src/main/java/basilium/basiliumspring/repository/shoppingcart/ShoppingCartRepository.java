package basilium.basiliumspring.repository.shoppingcart;

import basilium.basiliumspring.domain.shopingcart.ShoppingCart;

import java.util.List;

public interface ShoppingCartRepository {
    void addProductToCart(ShoppingCart record);
    ShoppingCart getProductById(Long id);
    List<ShoppingCart> getAllProductsByUserId(String userId);
    void updateShoppingRecord(ShoppingCart record);
    void deleteShoppingRecord(ShoppingCart record);//product id 로 지우기
    void deleteAllShoppingRecordByUserId(String userId);//product name 으로 지우기
}

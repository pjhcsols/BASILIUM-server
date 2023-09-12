package basilium.basiliumspring.service.shoppingcart;

import basilium.basiliumspring.domain.shopingcart.ShoppingCart;
import basilium.basiliumspring.repository.shoppingcart.ShoppingCartRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public ShoppingCart addProductToShoppingCart(ShoppingCart shoppingCart){
        shoppingCartRepository.addProductToCart(shoppingCart);
        return shoppingCart;
    }

    public ShoppingCart deleteProductFromShoppingCart(ShoppingCart shoppingCart){
        shoppingCartRepository.deleteShoppingRecord(shoppingCart);
        return shoppingCart;
    }

    public ShoppingCart modifyProductFromShoppingCart(ShoppingCart shoppingCart){
        shoppingCartRepository.updateShoppingRecord(shoppingCart);
        return shoppingCart;
    }

    public List<ShoppingCart> getListByUserId(String userId){
        List<ShoppingCart> result = shoppingCartRepository.getAllProductsByUserId(userId);
        return result;
    }
}

package basilium.basiliumspring.controller.shoppingcart;

import basilium.basiliumspring.domain.purchasetransaction.PurchaseTransaction;
import basilium.basiliumspring.domain.shopingcart.ShoppingCart;
import basilium.basiliumspring.service.shoppingcart.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
    private ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping("/addShoppingRecord")
    public ResponseEntity<String> addShoppingRecord(@RequestBody ShoppingCart shoppingCart){

        shoppingCartService.addProductToShoppingCart(shoppingCart);
        return ResponseEntity.ok("successfully save record");
    }

    @PostMapping("/deleteShoppingRecord")
    public ResponseEntity<String> deleteShoppingRecord(@RequestBody ShoppingCart shoppingCart) {
        shoppingCartService.deleteProductFromShoppingCart(shoppingCart);
        return ResponseEntity.ok("successfully delete record");
    }

    @GetMapping("/shoppingCartInfos")
    public ResponseEntity<List<ShoppingCart>> getTransactionInfoByUserId(@RequestParam String userId){
        List<ShoppingCart> ret = shoppingCartService.getListByUserId(userId);
        return ResponseEntity.ok(ret);
    }
}

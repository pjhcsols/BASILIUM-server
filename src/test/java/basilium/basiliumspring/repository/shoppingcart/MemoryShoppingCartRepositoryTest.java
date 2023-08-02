package basilium.basiliumspring.repository.shoppingcart;

import basilium.basiliumspring.domain.shopingcart.ShoppingCart;
import basilium.basiliumspring.repository.like.LikeRepository;
import basilium.basiliumspring.repository.like.MemoryLikeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemoryShoppingCartRepositoryTest {
    ShoppingCartRepository repository = new MemoryShoppingCartRepository();

    @AfterEach
    public void afterEach(){
        repository = new MemoryShoppingCartRepository();
    }
    @Test
    void addProductToCart() {
        //given
        ShoppingCart record1 = new ShoppingCart(1L, "asdf", 2L, 3L);
        ShoppingCart record2 = new ShoppingCart(1L, "asdf", 2L, 3L);
        //when
        repository.addProductToCart(record1);
        repository.addProductToCart(record2);
        //then
        System.out.println(repository.getAllProductsByUserId(record1.getUserId()).get(1).getOrderId());
        System.out.println(record2.getOrderId());
        Assertions.assertThat(repository.getAllProductsByUserId(record1.getUserId()).get(0)).isEqualTo(record1);
        Assertions.assertThat(repository.getAllProductsByUserId(record1.getUserId()).get(1)).isEqualTo(record2);


    }

    @Test
    void getAllProductsByUserId() {
        //given
        ShoppingCart record1 = new ShoppingCart(1L, "asdf", 2L, 3L);
        ShoppingCart record2 = new ShoppingCart(1L, "asdf", 2L, 3L);
        //when
        repository.addProductToCart(record1);
        repository.addProductToCart(record2);
        //then
        Assertions.assertThat(repository.getAllProductsByUserId(record1.getUserId()).get(0)).isEqualTo(record1);
        Assertions.assertThat(repository.getAllProductsByUserId(record1.getUserId()).get(1)).isEqualTo(record2);
    }

    @Test
    void updateShoppingRecord() {
        //given
        ShoppingCart record1 = new ShoppingCart(1L, "asdf", 2L, 3L);
        ShoppingCart record2 = new ShoppingCart(1L, "asdf", 2L, 3L);
        //when
        repository.addProductToCart(record1);
        repository.addProductToCart(record2);
        record2.setOrderId(77L);
        //then
        Assertions.assertThat(repository.getAllProductsByUserId("asdf").get(0).getProductId()).isNotEqualTo(77L);
        repository.updateShoppingRecord(record2);
        Assertions.assertThat(repository.getAllProductsByUserId("asdf").get(1)).isEqualTo(record2);
    }
    @Test
    void deleteShoppingRecord() {
        //given
        ShoppingCart record1 = new ShoppingCart(1L, "asdf", 2L, 3L);
        ShoppingCart record2 = new ShoppingCart(1L, "asdf", 2L, 3L);
        //when
        repository.addProductToCart(record1);
        repository.addProductToCart(record2);
        repository.deleteShoppingRecord(record1);
        //then
        Assertions.assertThat(repository.getProductById(record1.getOrderId())).isEqualTo(null);
    }

    @Test
    void deleteAllShoppingRecordByUserId() {
        ShoppingCart record1 = new ShoppingCart(1L, "asdf", 2L, 3L);
        ShoppingCart record2 = new ShoppingCart(1L, "asdf", 2L, 3L);
        //when
        repository.addProductToCart(record1);
        repository.addProductToCart(record2);
        repository.deleteAllShoppingRecordByUserId(record1.getUserId());
        //then
        Assertions.assertThat(repository.getAllProductsByUserId(record1.getUserId()).isEmpty()).isEqualTo(true);
    }
}
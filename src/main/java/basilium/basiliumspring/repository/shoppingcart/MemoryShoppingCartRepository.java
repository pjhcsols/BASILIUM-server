package basilium.basiliumspring.repository.shoppingcart;

import basilium.basiliumspring.domain.product.Product;
import basilium.basiliumspring.domain.shopingcart.ShoppingCart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MemoryShoppingCartRepository implements ShoppingCartRepository{
    private Map<Long, ShoppingCart> store;
    private long sequence = 0L;

    public MemoryShoppingCartRepository() {
        this.store = new HashMap<>();
    }

    @Override
    public void addProductToCart(ShoppingCart record) {
        record.setOrderId(++sequence);
        store.put(record.getOrderId(), record);
    }

    @Override
    public List<ShoppingCart> getAllProductsByUserId(String userId) {
        return store.values().stream().filter(m->m.getUserId().equals(userId)).collect(Collectors.toList());
    }

    @Override
    public void updateShoppingRecord(ShoppingCart record) {
        store.replace(record.getOrderId(), record);
    }

    @Override
    public void deleteShoppingRecord(ShoppingCart record) {
        store.remove(record.getOrderId());
    }

    @Override
    public void deleteAllShoppingRecordByUserId(String userId) {
        List<ShoppingCart> temp = store.values().stream().filter(m->m.getUserId().equals(userId)).collect(Collectors.toList());
        for(ShoppingCart item : temp)
            store.remove(item.getOrderId());
    }

    @Override
    public ShoppingCart getProductById(Long id) {
        return store.get(id);
    }
}

package ro.itschool.service;

import org.springframework.stereotype.Service;
import ro.itschool.entity.Order;
import ro.itschool.entity.ShoppingCart;

import java.util.Optional;
@Service
public interface ShoppingCartService {
    public ShoppingCart update(ShoppingCart shoppingCart);

    public Optional<ShoppingCart> findById(Long id);

    public Order convertShoppingCartToOrder(ShoppingCart shoppingCart);

    public void deleteProductByIdFromShoppingCart(Long productId);
}

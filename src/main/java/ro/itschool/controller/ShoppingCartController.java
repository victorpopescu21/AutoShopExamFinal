package ro.itschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.itschool.entity.Product;
import ro.itschool.entity.User;
import ro.itschool.repository.OrderRepository;
import ro.itschool.repository.ProductRepository;
import ro.itschool.service.ShoppingCartService;
import ro.itschool.service.UserService;

import java.util.Optional;

@Controller
@RequestMapping(value = "/shopping-cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;

//    @PutMapping(value = "/add/{cartId}")
//    public ResponseEntity addProductToCart(@PathVariable Long cartId, @RequestParam Long productId){
//        Product product = productRepository.findById(productId).orElseThrow();
//        ShoppingCart cart = shoppingCartService.findById(cartId).orElseThrow();
//
//        cart.addProductToShoppingCart(product);
//        shoppingCartService.update(cart);
//
//        return ResponseEntity.ok().build();
//    }
//
//    @PutMapping(value = "/remove/{cartId}")
//    public ResponseEntity removeProductFromCart(@PathVariable Long cartId, @RequestParam Long productId){
//        Product product = productRepository.findById(productId).orElseThrow();
//        ShoppingCart cart = shoppingCartService.findById(cartId).orElseThrow();
//
//        cart.removeProductFromShoppingCart(product);
//        shoppingCartService.update(cart);
//
//        return ResponseEntity.ok().build();
//    }

    @RequestMapping(value= "/to-order")
    public String convertToOrder(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipleName = auth.getName();
        User userByUserName = userService.findUserByUserName(currentPrincipleName);
        orderRepository.save(shoppingCartService.convertShoppingCartToOrder(userByUserName.getShoppingCart()));
        userByUserName.getShoppingCart().getProducts().clear();
        userService.updateUser(userByUserName);
        return "order-successful";
    }

    @RequestMapping
    public String getShoppingCartForLoggedUser(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipleName = auth.getName();
        User userByUserName = userService.findUserByUserName(currentPrincipleName);

        model.addAttribute("products", userByUserName.getShoppingCart().getProducts());

        return "shopping-cart";
    }
    @RequestMapping(value = "/product/remove/{productId}")
    public String removeProductFromShoppingCart(@PathVariable Long productId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipleName = auth.getName();
        User userByUserName = userService.findUserByUserName(currentPrincipleName);
        Optional<Product> optionalProduct = productRepository.findById(productId);
        userByUserName.getShoppingCart().getProducts().removeIf(product -> product.getId().equals(productId));
        userService.updateUser(userByUserName);

        return "redirect:/shopping-cart";

    }



}

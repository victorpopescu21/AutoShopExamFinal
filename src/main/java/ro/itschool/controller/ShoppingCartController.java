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
import ro.itschool.exception.ProductNotFound;
import ro.itschool.repository.OrderRepository;
import ro.itschool.repository.ProductRepository;
import ro.itschool.service.OrderService;
import ro.itschool.service.ProductService;
import ro.itschool.service.impl.ShoppingCartServiceImpl;
import ro.itschool.service.UserService;
import ro.itschool.util.Constants;

import java.util.Optional;

@Controller
@RequestMapping(value = "/shopping-cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartServiceImpl shoppingCartService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @RequestMapping(value= "/to-order")
    public String convertToOrder(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipleName = auth.getName();
        User userByUserName = userService.findUserByUserName(currentPrincipleName);
        orderService.saveOrder(shoppingCartService.convertShoppingCartToOrder(userByUserName.getShoppingCart()));
        userByUserName.getShoppingCart().getProducts().clear();
        userService.updateUser(userByUserName);
        return Constants.ORDER_SUCCESSFUL;
    }

    @RequestMapping
    public String getShoppingCartForLoggedUser(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipleName = auth.getName();
        User userByUserName = userService.findUserByUserName(currentPrincipleName);
        model.addAttribute("products", userByUserName.getShoppingCart().getProducts());

        return Constants.SHOPPING_CART;
    }
    @RequestMapping(value = "/product/remove/{productId}")
    public String removeProductFromShoppingCart(@PathVariable Long productId) throws ProductNotFound {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipleName = auth.getName();
        User userByUserName = userService.findUserByUserName(currentPrincipleName);

        if(productService.findById(productId).isPresent()) {
            Optional<Product> optionalProduct = productService.findById(productId);
            userByUserName.getShoppingCart().getProducts().removeIf(product -> product.getId().equals(productId));
            userService.updateUser(userByUserName);
        }
        else
            throw new ProductNotFound("Product could not be found!");

        return Constants.REDIRECT_TO_CART;
    }



}

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
import ro.itschool.repository.ProductRepository;
import ro.itschool.service.ShoppingCartService;
import ro.itschool.service.UserService;
import ro.itschool.util.Constants;

import java.util.Optional;

@Controller
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private UserService userService;


//    @GetMapping(value = "/all")
//    public List<Product> getAllProducts(){
//        return productRepository.findAll();
//    }
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @PostMapping
//    public ResponseEntity<Product> addProductToShop(@RequestBody Product product){
//        return  new ResponseEntity<>(productRepository.save(product), HttpStatus.OK);
//    }
//
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/delete/{id}")
    public String removeProduct(@PathVariable Long id){
        shoppingCartService.deleteProductByIdFromShoppingCart(id);
        productRepository.deleteById(id);
        return Constants.REDIRECT_TO_PRODUCTS;

    }
    @RequestMapping(value = "/add/{id}")
    public String addProductToShoppingCart(@PathVariable Long id){
        // searching the product by Id
        Optional<Product> optionalProduct=productRepository.findById(id);
        // getting authenticated user name
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipleName = auth.getName();
        // we bring the authenticated user from DB
        User userByUserName = userService.findUserByUserName(currentPrincipleName);
        // checking if the product is in DB, if yes then add it to shoppingCart of the auth user
        optionalProduct.ifPresent(product-> {
            userByUserName.getShoppingCart().addProductToShoppingCart(product);
            userService.updateUser(userByUserName);
        });

        return Constants.REDIRECT_TO_PRODUCTS;

    }

    @RequestMapping(value = {"/all"})
    public String index(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("products", productRepository.findAll());
        return "products";
    }


}

package ro.itschool.startup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ro.itschool.entity.Product;
import ro.itschool.entity.Role;
import ro.itschool.entity.ShoppingCart;
import ro.itschool.entity.User;
import ro.itschool.repository.RoleRepository;
import ro.itschool.repository.UserRepository;
import ro.itschool.service.ProductService;
import ro.itschool.service.ShoppingCartService;
import ro.itschool.service.UserService;
import ro.itschool.util.Constants;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class RunAtStartup {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private StartupCsvUpload csvUpload;

    @EventListener(ContextRefreshedEvent.class)
    public void contextRefreshedEvent() {

        roleRepository.save(new Role(Constants.ROLE_USER));
        roleRepository.save(new Role(Constants.ROLE_ADMIN));

        csvUpload.uploadFile();

        saveUser();
        saveAdminUser();


    }

    private void saveAdminUser() {
        User myUser = new User();
        myUser.setUsername("admin");
        myUser.setPassword("admin");
        myUser.setRandomToken("randomToken");
        final Set<Role> roles = new HashSet<>();

        roles.add(roleRepository.findByName(Constants.ROLE_USER));
        roles.add(roleRepository.findByName(Constants.ROLE_ADMIN));
        myUser.setRoles(roles);
        myUser.setEnabled(true);
        myUser.setAccountNonExpired(true);
        myUser.setAccountNonLocked(true);
        myUser.setCredentialsNonExpired(true);
        myUser.setEmail("admin1@gmail.com");
        myUser.setFullName("Ion Admin");
        myUser.setPasswordConfirm("admin");
        myUser.setRandomTokenEmail("randomToken");
        userService.saveUser(myUser);
    }
    public void saveUser() {
        User myUser = new User();
        myUser.setUsername("user");
        myUser.setPassword("user");
        myUser.setRandomToken("randomToken");
        final Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(Constants.ROLE_USER));
        myUser.setRoles(roles);
        myUser.setEnabled(true);
        myUser.setAccountNonExpired(true);
        myUser.setAccountNonLocked(true);
        myUser.setCredentialsNonExpired(true);
        myUser.setEmail("user@gmail.com");
        myUser.setFullName("Ion User");
        myUser.setPasswordConfirm("user");
        myUser.setRandomTokenEmail("randomToken");


        User myUser1 =userService.saveUser(myUser); // salvam intai userul ca sa obtinem ID ul userului

        List<Product> products = productService.getProductsByBrand("bmw");
        ShoppingCart cart = myUser1.getShoppingCart();
        cart.setUser(myUser1);
        cart.setProducts(products);
        //ShoppingCart myCart = shoppingCartService.update(cart);

       // myUser1.setShoppingCart(cart);
        userService.updateUser(myUser1);

    }


}

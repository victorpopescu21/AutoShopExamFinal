package ro.itschool.startup;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ro.itschool.entity.*;
import ro.itschool.repository.ContactRepository;
import ro.itschool.repository.RoleRepository;
import ro.itschool.repository.UserRepository;
import ro.itschool.service.ProductService;
import ro.itschool.service.UserService;
import ro.itschool.service.impl.ShoppingCartServiceImpl;
import ro.itschool.util.Constants;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@Component
public class RunAtStartup {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Autowired
    private ShoppingCartServiceImpl shoppingCartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private StartupCsvUpload csvUpload;

    @EventListener(ContextRefreshedEvent.class)
    public void contextRefreshedEvent() {

        roleRepository.save(new Role(Constants.ROLE_USER));
        roleRepository.save(new Role(Constants.ROLE_ADMIN));

        csvUpload.uploadFile();

        saveUser();
        saveAdminUser();
        saveContacts();



    }

    private void saveAdminUser() {
        User myUser = new User();
        myUser.setUsername("victor");
        myUser.setPassword("parola");
        myUser.setRandomToken("randomToken");
        final Set<Role> roles = new HashSet<>();

        roles.add(roleRepository.findByName(Constants.ROLE_USER));
        roles.add(roleRepository.findByName(Constants.ROLE_ADMIN));
        myUser.setRoles(roles);
        myUser.setEnabled(true);
        myUser.setAccountNonExpired(true);
        myUser.setAccountNonLocked(true);
        myUser.setCredentialsNonExpired(true);
        myUser.setEmail("victpop01@gmail.com");
        myUser.setFullName("Victor Popescu");
        myUser.setPasswordConfirm("parola");
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
        userService.updateUser(myUser1);

    }

    public void saveContacts(){

        Faker faker = new Faker();

        Stream
                .generate(()-> contactRepository.save(
                        new Contact(
                                faker.funnyName().name(),
                                faker.internet().emailAddress(),
                                "No subject",
                                "No message"))
                )
                .limit(5)
                .toList();
    }
}

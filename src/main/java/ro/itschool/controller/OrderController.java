package ro.itschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.itschool.entity.Order;
import ro.itschool.entity.User;
import ro.itschool.exception.UserNotFound;
import ro.itschool.repository.OrderRepository;
import ro.itschool.repository.UserRepository;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/get-all/{userId}")
    public List<Order> getAllOrders(@PathVariable Long userId) throws UserNotFound {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent())
            return optionalUser.get().getOrders();
        else
            throw new UserNotFound("User not found");
    }


}

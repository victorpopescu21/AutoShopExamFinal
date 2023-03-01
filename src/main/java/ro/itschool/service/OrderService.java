package ro.itschool.service;

import org.springframework.stereotype.Service;
import ro.itschool.entity.Order;
import ro.itschool.service.impl.OrderServiceImpl;
@Service
public interface OrderService{
    void saveOrder(Order order);
}

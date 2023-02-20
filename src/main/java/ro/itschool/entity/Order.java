package ro.itschool.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "my_orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime orderDate;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Product> products = new ArrayList<>();

    @ManyToOne
    private User user;

    public void addProductToOrder(Product p) {
        this.products.add(p);
    }


}

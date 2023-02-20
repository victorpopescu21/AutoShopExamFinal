package ro.itschool.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Entity
@Getter
@Setter
@AllArgsConstructor
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Product> products = new ArrayList<>();

    @OneToOne(mappedBy = "shoppingCart")
    private User user;

    public void addProductToShoppingCart(Product p) {
        this.products.add(p);
    }

    public void removeProductFromShoppingCart(Product product) {
        this.products.remove(product);
    }

    public ShoppingCart(List<Product> products) {
        this.products = products;
    }

    public ShoppingCart() {
    }
}


package ro.itschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.itschool.entity.Car;

public interface CarRepo extends JpaRepository<Car,Long> {


}

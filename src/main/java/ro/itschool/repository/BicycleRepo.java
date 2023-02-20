package ro.itschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.itschool.entity.Bicycle;

public interface BicycleRepo extends JpaRepository<Bicycle,Long> {


}

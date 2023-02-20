package ro.itschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.itschool.entity.Car;
import ro.itschool.repository.CarRepo;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cars")
public class CarController {
    @Autowired
    private CarRepo carRepo;


    @GetMapping(value = "/all")
    public List<Car> getAllCars(){
        return carRepo.findAll();
    }

    @GetMapping(value="/car-by-id/{id}")
    public ResponseEntity getCarById(@PathVariable Long id){
        Optional<Car> optionalCar = carRepo.findById(id);
        if(optionalCar.isPresent())
            return new ResponseEntity<>(optionalCar, HttpStatus.OK);
        else
            return ResponseEntity.badRequest().build();

    }



    @PutMapping(value = "/update-car")
    public ResponseEntity updateCar(@RequestBody Car car){
        Optional<Car> optionalCar = carRepo.findById(car.getId());
        if(optionalCar.isPresent()) {
            carRepo.save(car);
            return ResponseEntity.ok().build();
        }
        else
            return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Car> deleteCar(@PathVariable Long id) {
        if (carRepo.existsById(id)) {
            carRepo.deleteById(id);
            return ResponseEntity.ok().build();
        } else return ResponseEntity.badRequest().build();
    }




}

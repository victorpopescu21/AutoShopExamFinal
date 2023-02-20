package ro.itschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.itschool.entity.Bicycle;
import ro.itschool.repository.BicycleRepo;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/bicycles")
public class BicycleController {

    @Autowired
    private BicycleRepo bicycleRepo;

    @GetMapping(value = "/all")
    public List<Bicycle> getAllBike(){
        return bicycleRepo.findAll();
    }

    @GetMapping(value="/bicycle-by-id/{id}")
    public ResponseEntity getBikeById(@PathVariable Long id){
        Optional<Bicycle> optionalBicycle = bicycleRepo.findById(id);
        if(optionalBicycle.isPresent())
            return new ResponseEntity<>(optionalBicycle, HttpStatus.OK);
        else
            return ResponseEntity.badRequest().build();

    }


    @PutMapping(value = "/update-bicycle")
    public ResponseEntity<Bicycle> updateBike(@RequestBody Bicycle bike){
        Optional<Bicycle> optionalBike = bicycleRepo.findById(bike.getId());
        if(optionalBike.isPresent()) {
            bicycleRepo.save(bike);
            return ResponseEntity.ok().build();
        }
        else
            return ResponseEntity.badRequest().build();
    }
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Bicycle> deleteBike(@PathVariable Long id) {
        if (bicycleRepo.existsById(id)) {
            bicycleRepo.deleteById(id);
            return ResponseEntity.ok().build();
        } else return ResponseEntity.badRequest().build();
    }

}

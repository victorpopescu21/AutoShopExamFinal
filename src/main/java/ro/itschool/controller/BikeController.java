package ro.itschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.itschool.entity.MotorBike;
import ro.itschool.repository.MotorBikeRepo;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping(value="/bikes")
public class BikeController {

    @Autowired
    private MotorBikeRepo bikeRepo;
    @GetMapping(value = "/all")
    public List<MotorBike> getAllBike(){
        return bikeRepo.findAll();
    }

    @GetMapping(value="/bike-by-id/{id}")
    public ResponseEntity getBikeById(@PathVariable Long id){
        Optional<MotorBike> optionalBike = bikeRepo.findById(id);
        if(optionalBike.isPresent())
            return new ResponseEntity<>(optionalBike, HttpStatus.OK);
        else
            return ResponseEntity.badRequest().build();

    }


    @PutMapping(value = "/update-bike")
    public ResponseEntity updateBike(@RequestBody MotorBike bike){
        Optional<MotorBike> optionalBike = bikeRepo.findById(bike.getId());
        if(optionalBike.isPresent()) {
            bikeRepo.save(bike);
            return ResponseEntity.ok().build();
        }
        else
            return ResponseEntity.badRequest().build();
    }
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<MotorBike> deleteBike(@PathVariable Long id) {
        if (bikeRepo.existsById(id)) {
            bikeRepo.deleteById(id);
            return ResponseEntity.ok().build();
        } else return ResponseEntity.badRequest().build();
    }
}

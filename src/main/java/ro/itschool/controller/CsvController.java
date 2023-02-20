package ro.itschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ro.itschool.controller.model.BicycleModel;
import ro.itschool.controller.model.BikeModel;
import ro.itschool.controller.model.CarModel;
import ro.itschool.csv.helper.CsvHelper;
import ro.itschool.entity.Bicycle;
import ro.itschool.entity.Car;
import ro.itschool.entity.MotorBike;
import ro.itschool.repository.BicycleRepo;
import ro.itschool.repository.CarRepo;
import ro.itschool.repository.MotorBikeRepo;

import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping(value ="/api/csv")
public class CsvController {

    @Autowired
    private CarRepo carRepo;

    @Autowired
    private MotorBikeRepo bikeRepo;

    @Autowired
    private BicycleRepo bicycleRepo;

    @PostMapping(value = "/upload")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) {

        try {
            List<Car> carList = CsvHelper.getAllCars(file).stream()
                    .filter(car-> !car.getBrand().isEmpty() && !car.getModel().isEmpty())
                            .map(CarModel::carToEntity)
                                    .collect(Collectors.toList());

            List<MotorBike> bikeList = CsvHelper.getAllBikes(file).stream()
                    .filter(bike-> !bike.getBrand().isEmpty() && !bike.getModel().isEmpty())
                    .map(BikeModel::bikeToEntity)
                    .collect(Collectors.toList());

            List<Bicycle> bicycleList = CsvHelper.getAllBicycles(file).stream()
                    .filter(bicycle-> !bicycle.getBrand().isEmpty() && !bicycle.getModel().isEmpty())
                    .map(BicycleModel::cycleToEntity)
                    .collect(Collectors.toList());

            carRepo.saveAll(carList);
            bikeRepo.saveAll(bikeList);
            bicycleRepo.saveAll(bicycleList);

            return ResponseEntity.ok().build();

        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }
    }
}

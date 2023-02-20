package ro.itschool.startup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
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

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class StartupCsvUpload {
    @Autowired
    private CarRepo carRepo;

    @Autowired
    private MotorBikeRepo bikeRepo;

    @Autowired
    private BicycleRepo bicycleRepo;
    public ResponseEntity uploadFile() {
        File file = new File("src/main/resources/Autoshop.csv");
        try {
            FileInputStream input = new FileInputStream(file);
            MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "text/plain", input.readAllBytes());


            List<Car> carList = CsvHelper.getAllCars(multipartFile).stream()
                    .filter(car-> !car.getBrand().isEmpty() && !car.getModel().isEmpty())
                    .map(CarModel::carToEntity)
                    .collect(Collectors.toList());

            List<MotorBike> bikeList = CsvHelper.getAllBikes(multipartFile).stream()
                    .filter(bike-> !bike.getBrand().isEmpty() && !bike.getModel().isEmpty())
                    .map(BikeModel::bikeToEntity)
                    .collect(Collectors.toList());

            List<Bicycle> bicycleList = CsvHelper.getAllBicycles(multipartFile).stream()
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

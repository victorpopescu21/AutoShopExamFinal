package ro.itschool.controller.model;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;
import ro.itschool.entity.Car;

@Data
public class CarModel {
    @CsvBindByPosition(position = 0)
    private String brand;
    @CsvBindByPosition(position = 1)
    private String model;
    @CsvBindByPosition(position = 2)
    private Float price;

    public Car carToEntity(){
        Car car = new Car();
        car.setBrand(this.brand);
        car.setModel(this.model);
        car.setPrice(this.price);
        return car;
    }
}


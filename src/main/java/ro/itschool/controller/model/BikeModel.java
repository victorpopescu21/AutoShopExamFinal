package ro.itschool.controller.model;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;
import ro.itschool.entity.MotorBike;

@Data
public class BikeModel {
    @CsvBindByPosition(position = 5)
    private String brand;
    @CsvBindByPosition(position = 6)
    private String model;
    @CsvBindByPosition(position = 7)
    private Float price;
    @CsvBindByPosition(position = 8)
    private String vehicleType;


    public MotorBike bikeToEntity(){
        MotorBike bike = new MotorBike();
        bike.setBrand(this.brand);
        bike.setModel(this.model);
        bike.setPrice(this.price);
        bike.setVehicleType(this.vehicleType);
        return bike;
    }
}

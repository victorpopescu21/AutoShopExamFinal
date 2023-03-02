package ro.itschool.controller.model;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;
import ro.itschool.entity.Bicycle;
@Data
public class BicycleModel {
    @CsvBindByPosition(position = 10)
    private String brand;
    @CsvBindByPosition(position = 11)
    private String model;
    @CsvBindByPosition(position = 12)
    private Float price;
    @CsvBindByPosition(position = 13)
    private String vehicleType;


    public Bicycle cycleToEntity(){
        Bicycle bicycle = new Bicycle();
        bicycle.setBrand(this.brand);
        bicycle.setModel(this.model);
        bicycle.setPrice(this.price);
        bicycle.setVehicleType(this.vehicleType);
        return bicycle;
    }
}

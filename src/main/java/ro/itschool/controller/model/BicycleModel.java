package ro.itschool.controller.model;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;
import ro.itschool.entity.Bicycle;
@Data
public class BicycleModel {
    @CsvBindByPosition(position = 8)
    private String brand;
    @CsvBindByPosition(position = 9)
    private String model;
    @CsvBindByPosition(position = 10)
    private Float price;

    public Bicycle cycleToEntity(){
        Bicycle bicycle = new Bicycle();
        bicycle.setBrand(this.brand);
        bicycle.setModel(this.model);
        bicycle.setPrice(this.price);
        return bicycle;
    }
}

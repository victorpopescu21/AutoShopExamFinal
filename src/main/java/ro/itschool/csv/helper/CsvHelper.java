package ro.itschool.csv.helper;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.web.multipart.MultipartFile;
import ro.itschool.controller.model.BicycleModel;
import ro.itschool.controller.model.BikeModel;
import ro.itschool.controller.model.CarModel;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

public class CsvHelper {

    public static List<CarModel> getAllCars(MultipartFile file) throws IOException {
        Reader reader = new InputStreamReader(file.getInputStream());
        CsvToBean csvReader = new CsvToBeanBuilder(reader)
                .withType(CarModel.class)
                .withSeparator(';')
                .withIgnoreLeadingWhiteSpace(true)
                .withIgnoreEmptyLine(true)
                .withSkipLines(1)
                .build();
        return csvReader.parse();
    }

    public static List<BikeModel> getAllBikes(MultipartFile file) throws IOException {
        Reader reader = new InputStreamReader(file.getInputStream());
        CsvToBean csvReader = new CsvToBeanBuilder(reader)
                .withType(BikeModel.class)
                .withSeparator(';')
                .withIgnoreLeadingWhiteSpace(true)
                .withIgnoreEmptyLine(true)
                .withSkipLines(1)
                .build();
        return csvReader.parse();


    }
    public static List<BicycleModel> getAllBicycles(MultipartFile file) throws IOException {
        Reader reader = new InputStreamReader(file.getInputStream());
        CsvToBean csvReader = new CsvToBeanBuilder(reader)
                .withType(BicycleModel.class)
                .withSeparator(';')
                .withIgnoreLeadingWhiteSpace(true)
                .withIgnoreEmptyLine(true)
                .withSkipLines(1)
                .build();
        return csvReader.parse();


    }
}

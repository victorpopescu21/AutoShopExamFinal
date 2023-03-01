package ro.itschool.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class ProductNotFound extends Exception{
    public ProductNotFound(String message) {
        super(message);
    }
}

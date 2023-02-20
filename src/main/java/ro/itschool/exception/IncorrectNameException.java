package ro.itschool.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@NoArgsConstructor
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such product in the shop.")  // 404
public class IncorrectNameException extends RuntimeException{
    public IncorrectNameException(String message) {
        super(message);
    }
}

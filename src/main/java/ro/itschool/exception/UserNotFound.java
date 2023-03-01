package ro.itschool.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@NoArgsConstructor
@ResponseStatus(value= HttpStatus.NOT_FOUND)  // 404
public class UserNotFound extends Exception{
    public UserNotFound(String message) {
        super(message);
    }
}

package ro.itschool.exception;

import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MyResourceNotFoundExceptions extends RuntimeException{

    public MyResourceNotFoundExceptions(){
        super();
    }

    public MyResourceNotFoundExceptions(String message, Throwable cause){
        super(message, cause);
    }
    public  MyResourceNotFoundExceptions(String message){
        super(message);
    }
}

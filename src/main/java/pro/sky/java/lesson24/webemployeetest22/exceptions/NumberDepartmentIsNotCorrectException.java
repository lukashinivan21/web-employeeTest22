package pro.sky.java.lesson24.webemployeetest22.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NumberDepartmentIsNotCorrectException extends RuntimeException {

    public NumberDepartmentIsNotCorrectException() {

    }

    public NumberDepartmentIsNotCorrectException(String message) {
        super(message);
    }

    public NumberDepartmentIsNotCorrectException(String message, Throwable throwable) {
        super(message, throwable);
    }
}

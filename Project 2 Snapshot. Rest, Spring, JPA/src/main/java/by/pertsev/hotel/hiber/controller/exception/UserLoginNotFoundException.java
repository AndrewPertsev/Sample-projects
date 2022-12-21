package by.pertsev.hotel.hiber.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginNotFoundException extends RuntimeException {
    private String login;
    private int exceptionCode;
    private String message;
}

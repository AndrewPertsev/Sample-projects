package by.pertsev.hotel.hiber.controller.exception;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@ControllerAdvice
@AllArgsConstructor/////////////////////////////////////////
public class ExceptionController {
    public static final int NOT_FOUND_CODE = 4004;
    public static final int NOT_VALID_ENTITY_CODE = 4022;
    public static final int CUSTOM_EXCEPTION_CODE = 4044;
    private static final String DEFAULT_EXCEPTION_MESSAGE = "exception__message";

    private final MessageSource messageSource;

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<HotelEntityExceptionResponse> handleException(NotFoundException exc) {
        HotelEntityExceptionResponse hotelEntityExceptionResponse = new HotelEntityExceptionResponse("Entity not found", LocalDateTime.now(), NOT_FOUND_CODE);
        log.error("Not found entity exception occured");
        return new ResponseEntity<>(hotelEntityExceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HotelEntityExceptionResponse> handleException(MethodArgumentNotValidException exc) {
        List<String> errors = new ArrayList<>();
        for (FieldError error : exc.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }

        HotelEntityExceptionResponse hotelEntityExceptionResponse =
                new HotelEntityExceptionResponse("Validation failed : " + errors, LocalDateTime.now(), NOT_VALID_ENTITY_CODE);
        log.error("Validation failed : " + errors);
        return new ResponseEntity<>(hotelEntityExceptionResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }


    @ExceptionHandler(UserLoginNotFoundException.class)
    public ResponseEntity<HotelEntityExceptionResponse> handleException(UserLoginNotFoundException exc) {
        HotelEntityExceptionResponse hotelEntityExceptionResponse = new HotelEntityExceptionResponse("Login not found", LocalDateTime.now(), NOT_FOUND_CODE);
        log.error("Not found Login exception occured");
        return new ResponseEntity<>(hotelEntityExceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HotelEntityExceptionResponse> handleAll(Exception exception) {
        HotelEntityExceptionResponse hotelEntityExceptionResponse = new HotelEntityExceptionResponse(DEFAULT_EXCEPTION_MESSAGE, LocalDateTime.now(), CUSTOM_EXCEPTION_CODE);
        hotelEntityExceptionResponse.setMessage(exception.getMessage());
        log.error("Exception_occured");
        return new ResponseEntity<>(hotelEntityExceptionResponse, HttpStatus.valueOf(CUSTOM_EXCEPTION_CODE));
    }

}

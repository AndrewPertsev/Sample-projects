package by.pertsev.hotel.hiber.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HotelEntityExceptionResponse {
    private String message;
    private LocalDateTime timestamp;
    private int status;


}

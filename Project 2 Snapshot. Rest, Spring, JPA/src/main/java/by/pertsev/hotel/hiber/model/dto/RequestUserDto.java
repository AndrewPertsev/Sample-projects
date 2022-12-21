package by.pertsev.hotel.hiber.model.dto;

import by.pertsev.hotel.hiber.model.Category;
import by.pertsev.hotel.hiber.model.User;
import lombok.*;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

import static by.pertsev.hotel.hiber.model.DBColumnLabel.DATE_PATTERN;
import static by.pertsev.hotel.hiber.model.RequestUser.DEFAULT_DISTANCE;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestUserDto extends HotelDTOEntity {

    private Integer requestId;

    private User user;

    private Category category;

    @Range(min = 1, max = 8)
    private int quantity;

    private int distance = DEFAULT_DISTANCE;

    @DateTimeFormat(pattern = DATE_PATTERN)
    @Future(message = " should be later.")
    private LocalDate start;


    @DateTimeFormat(pattern = DATE_PATTERN)
    @Future(message = " should be later.")
    private LocalDate end;


    @DateTimeFormat(pattern = DATE_PATTERN)
    @PastOrPresent
    private LocalDate dateRequest;

    private boolean isResponded;

}

package by.pertsev.hotel.hiber.dto;

import by.pertsev.hotel.hiber.model.Category;
import by.pertsev.hotel.hiber.model.User;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.*;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

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

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @DateTimeFormat(pattern = DATE_PATTERN)
    private LocalDate start;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @DateTimeFormat(pattern = DATE_PATTERN)
    private LocalDate end;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @DateTimeFormat(pattern = DATE_PATTERN)
    private LocalDate dateRequest;

    private boolean isResponded;

}

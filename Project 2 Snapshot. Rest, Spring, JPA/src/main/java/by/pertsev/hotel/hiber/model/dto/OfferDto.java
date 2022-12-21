package by.pertsev.hotel.hiber.model.dto;

import by.pertsev.hotel.hiber.model.Apartment;
import by.pertsev.hotel.hiber.model.RequestUser;
import lombok.*;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Future;
import java.math.BigDecimal;
import java.time.LocalDate;

import static by.pertsev.hotel.hiber.model.DBColumnLabel.DATE_PATTERN;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfferDto extends HotelDTOEntity {
    private Integer offerId;

    private RequestUser requestUser;

    private Apartment apartment;

    @Range(min = 1, max = 8, message = " should be between 1 and 8 persons.")
    private int quantity;


    @DateTimeFormat(pattern = DATE_PATTERN)
    @Future(message = " should be later.")
    private LocalDate start;

    @DateTimeFormat(pattern = DATE_PATTERN)
    @Future(message = " should be later.")
    private LocalDate end;

    @DecimalMax("100000")
    @DecimalMin("1")
    private BigDecimal priceOffer;

    private boolean isSent;
    private boolean isPaid;
    private boolean isClosed;


}

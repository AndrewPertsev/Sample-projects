package by.pertsev.hotel.hiber.dto;

import by.pertsev.hotel.hiber.model.Apartment;
import by.pertsev.hotel.hiber.model.RequestUser;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.*;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

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

    @Range(min = 1, max = 8)
    private int quantity;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @DateTimeFormat(pattern = DATE_PATTERN)
    private LocalDate start;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @DateTimeFormat(pattern = DATE_PATTERN)
    private LocalDate end;

    @Range(min = 1, max = 80000)
    private BigDecimal priceOffer;

    private boolean isSent;
    private boolean isPaid;
    private boolean isClosed;


}

package by.pertsev.hotel.hiber.model.dto;

import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Pattern;

import static by.pertsev.hotel.hiber.model.dto.ValidatorConstants.PATTERN_DESCRIPTION;
import static by.pertsev.hotel.hiber.model.dto.ValidatorConstants.PATTERN_PICTURE_PNG;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApartmentDto extends HotelDTOEntity {
    private Integer apartmentId;

    private int category;

    @Range(min = 1, max = 8)
    private int capacity;

    @Pattern(regexp = PATTERN_DESCRIPTION, message = "Size should be between 2 and 228 characters. ")
    private String description;
    @Pattern(regexp = PATTERN_PICTURE_PNG, message = "Size should be between 1 and 100 characters, file format PNG. ")
    private String pathToPicture;
}

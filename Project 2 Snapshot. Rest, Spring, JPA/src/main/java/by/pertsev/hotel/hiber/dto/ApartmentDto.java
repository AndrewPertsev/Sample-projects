package by.pertsev.hotel.hiber.dto;

import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Size;

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

    @Size(min = 2, max = 128, message = "Size should be between 2 and 228 characters. ")
    private String description;

    @Size(min = 2, max = 128, message = "Size should be between 2 and 128 characters. ")
    private String pathToPicture;
}

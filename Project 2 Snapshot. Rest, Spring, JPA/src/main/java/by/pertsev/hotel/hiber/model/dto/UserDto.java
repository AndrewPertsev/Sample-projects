package by.pertsev.hotel.hiber.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static by.pertsev.hotel.hiber.model.dto.ValidatorConstants.PATTERN_COMMENT;
import static by.pertsev.hotel.hiber.model.dto.ValidatorConstants.PATTERN_NAME;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto extends HotelDTOEntity {
    private Integer userId;
    @Range(min = 0, max = 2, message = " should be between 0 and 2 .")
    private int role;
    @Pattern(regexp = PATTERN_NAME, message = "Size should be between 2 and 30 characters. ")

    private String name;

    @Pattern(regexp = PATTERN_NAME, message = "Size should be between 2 and 30 characters. ")

    private String surname;

    @Pattern(regexp = PATTERN_NAME, message = "Size should be between 2 and 30 characters. ")

    private String login;

    @JsonIgnore
    @Length(min = 3, max = 28)
    private String password;

    @Size(max = 128)
    @Email
    private String email;

    @Size(min = 5, max = 28, message = "Size should be between 5 and 28 characters. ")
    private String tel;

    @Size(min = 2, max = 28, message = "Size should be between 2 and 28 characters. ")
    private String country;
    @Pattern(regexp = PATTERN_COMMENT)
    private String comment;

    @Size(min = 4, max = 12, message = "Size should be between 4 and 12 characters. ")
    private String passport;

    private boolean isVip;
    private boolean isNonGrata;
}

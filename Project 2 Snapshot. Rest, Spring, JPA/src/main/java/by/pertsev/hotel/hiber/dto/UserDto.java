package by.pertsev.hotel.hiber.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto extends HotelDTOEntity {
    private Integer userId;

    private int role;

    @Size(min = 2, max = 128, message = "Size should be between 2 and 128 characters. ")
    private String name;

    @Size(min = 2, max = 128, message = "Size should be between 2 and 128 characters. ")
    private String surname;

    @Size(min = 2, max = 128, message = "Size should be between 2 and 128 characters. ")
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

    private String comment;

    @Size(min = 4, max = 12, message = "Size should be between 4 and 12 characters. ")
    private String passport;

    private boolean isVip;
    private boolean isNonGrata;
}

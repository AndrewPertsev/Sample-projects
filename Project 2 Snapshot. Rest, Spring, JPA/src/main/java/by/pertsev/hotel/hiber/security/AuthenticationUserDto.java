package by.pertsev.hotel.hiber.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Size;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthenticationUserDto {
    @Size(min = 2, max = 128, message = "Size should be between 2 and 128 characters. ")
    private String login;

    @Length(min = 3, max = 28)
    private String password;
}

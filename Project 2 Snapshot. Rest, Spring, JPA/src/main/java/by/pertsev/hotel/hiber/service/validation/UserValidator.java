package by.pertsev.hotel.hiber.service.validation;

import by.pertsev.hotel.hiber.dao.UserDao;
import by.pertsev.hotel.hiber.dto.UserDto;
import by.pertsev.hotel.hiber.model.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class UserValidator implements Validator {
    private final UserDao userDao;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserDto userDto = (UserDto) o;
        Optional<User> loginHolder = userDao.findByLogin(userDto.getLogin());

//        boolean isValidPassword;
//        User validGuest = null;
//
//        if (validGuest == null) {
//            logger.error("Service can't validate null guest ");
//        }

//        if (validGuest.isNonGrata()) {
//            logger.error("Service can't validate non grata status ");
//        }
//            isValidPassword = BCrypt.checkpw(passwordToCheck, validGuest.getPassword());
//            if (!isValidPassword) {
//                logger.error("Service can't validate password or login");
//            }

        if (loginHolder.isPresent()) {
            errors.rejectValue("login", "", "User with this login exists");
        }
    }

}

package by.pertsev.hotel.hiber.service.validation;

import by.pertsev.hotel.hiber.dao.UserDao;
import by.pertsev.hotel.hiber.model.User;
import by.pertsev.hotel.hiber.model.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

import static by.pertsev.hotel.hiber.controller.exception.ExceptionController.NOT_FOUND_CODE;

@Slf4j
@Component
@AllArgsConstructor
public class UserValidator implements Validator {
    private final UserDao userDao;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDto.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserDto userDto = (UserDto) o;
        Optional<User> loginHolder = userDao.findByLogin(userDto.getLogin());
        //       User user = loginHolder.get();

        if (loginHolder.isPresent()) {
            errors.rejectValue("login", String.valueOf(NOT_FOUND_CODE), "User with this login exists");
        }
//        else if (user != null & user.isNonGrata()) {
//            errors.rejectValue("isNonGrata", String.valueOf(NOT_FOUND_CODE), "User banned");
//        }
    }

}

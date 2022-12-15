package by.pertsev.hotel.hiber.dto.util;

import by.pertsev.hotel.hiber.dto.UserDto;
import by.pertsev.hotel.hiber.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserConverterDto implements ConverterDTO<UserDto, User> {
    public final static int DEFAULT_ROLE_FOR_NEW_USER = 0;

    @Override
    public UserDto convertEntityToDto(User entity) {
        log.info("Convert Entity To Dto");

        return UserDto.builder()
                .userId(entity.getUserId())
                .role((entity.getRole()))
                .name(entity.getName())
                .surname(entity.getSurname())
                .login(entity.getLogin())
                .password(entity.getPassword())
                .email(entity.getEmail())
                .comment(entity.getComment())
                .isNonGrata(entity.isNonGrata())
                .isVip(entity.isVip())
                .build();
    }

    @Override
    public User convertDtoToEntity(UserDto dto) {
        log.info("Convert DTO To Entity");
        return User.builder()
                .userId(dto.getUserId())
                .role(DEFAULT_ROLE_FOR_NEW_USER)
                .name(dto.getName())
                .surname(dto.getSurname())
                .login(dto.getLogin())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .comment(dto.getComment())
                .isNonGrata(dto.isNonGrata())
                .isVip(dto.isVip())
                .build();
    }
}

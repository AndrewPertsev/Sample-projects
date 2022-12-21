package by.pertsev.hotel.hiber.service.impl;

import by.pertsev.hotel.hiber.dao.UserDao;
import by.pertsev.hotel.hiber.model.User;
import by.pertsev.hotel.hiber.model.converter.ConverterDTO;
import by.pertsev.hotel.hiber.model.dto.UserDto;
import by.pertsev.hotel.hiber.service.UserServiceable;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
@Transactional

public class UserServiceImpl implements UserServiceable {
    private final UserDao userDao;
    private final ConverterDTO<UserDto, User> userConverterDto;


    @Override
    public User save(UserDto dto) {
        User user = userConverterDto.convertDtoToEntity(dto);
        log.debug(" User service  create method");
        return userDao.save(user);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userDao.findAll(pageable);
    }

    @Override
    public User findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return userDao.findByLogin(login);
    }

    @Override
    public void update(UserDto dto, Integer id) {
        User user = userConverterDto.convertDtoToEntity(dto);
        userDao.update(user, id);
    }

    @Override
    public void deleteById(int id) {
        userDao.deleteById(id);
    }


}
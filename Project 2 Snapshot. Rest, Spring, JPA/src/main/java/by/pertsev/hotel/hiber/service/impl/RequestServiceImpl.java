package by.pertsev.hotel.hiber.service.impl;

import by.pertsev.hotel.hiber.dao.RequestUserDao;
import by.pertsev.hotel.hiber.model.RequestUser;
import by.pertsev.hotel.hiber.model.converter.ConverterDTO;
import by.pertsev.hotel.hiber.model.dto.RequestUserDto;
import by.pertsev.hotel.hiber.service.RequestServiceable;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//@Profile("jpa")

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class RequestServiceImpl implements RequestServiceable {
    private final ConverterDTO<RequestUserDto, RequestUser> requestUserConverterDto;
    private RequestUserDao requestUserDao;


    @Override
    public RequestUser save(RequestUserDto dto) {
        RequestUser entity = requestUserConverterDto.convertDtoToEntity(dto);
        return requestUserDao.save(entity);
    }

    @Override
    public Page<RequestUser> findAll(Pageable pageable) {
        return requestUserDao.findAll(pageable);
    }

    @Override
    public RequestUser findById(int id) {
        return requestUserDao.findById(id);
    }

    @Override
    public void update(RequestUserDto dto, Integer id) {
        RequestUser requestUser = requestUserConverterDto.convertDtoToEntity(dto);
        requestUserDao.update(requestUser, id);
    }

    @Override
    public void deleteById(int id) {
        requestUserDao.deleteById(id);
    }

    @Override
    public List<RequestUser> findUnresponded() {
        return requestUserDao.findAll(Pageable.unpaged()).stream().filter(request -> request.isResponded() == false).collect(Collectors.toList());
    }

    @Override
    public Optional<RequestUser> findMaximumRequestId() {
        return requestUserDao.findAll(Pageable.unpaged()).stream().max(Comparator.comparing(RequestUser::getRequestId));
    }
}

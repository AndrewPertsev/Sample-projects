package by.pertsev.hotel.hiber.service.impl;

import by.pertsev.hotel.hiber.dao.ApartmentDao;
import by.pertsev.hotel.hiber.dao.RequestUserDao;
import by.pertsev.hotel.hiber.dto.ApartmentDto;
import by.pertsev.hotel.hiber.dto.util.ConverterDTO;
import by.pertsev.hotel.hiber.model.Apartment;
import by.pertsev.hotel.hiber.model.RequestUser;
import by.pertsev.hotel.hiber.service.ApartmentServiceable;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class ApartmentServiceImpl implements ApartmentServiceable {

    private ApartmentDao apartmentDao;
    private RequestUserDao requestUserDao;
    private final ConverterDTO<ApartmentDto, Apartment> apartmentConverterDto;

    @Override
    public List<Apartment> findApartmentsAvailableForRequest(int idRequestUser) {
        RequestUser requestUser = requestUserDao.findById(idRequestUser);
        LocalDate start = requestUser.getStart();
        LocalDate end = requestUser.getEnd();
        int category = requestUser.getCategory().getCategoryId();
        int quantity = requestUser.getQuantity();

        return apartmentDao.findApartmentsSuitableForRequest(start, end, category, quantity);

    }

    @Override
    public Page<Apartment> findAll(Pageable pageable) {
        return apartmentDao.findAll(pageable);
    }

    // @CacheEvict(value = "apartments", allEntries = true)
    @Override
    public Apartment save(ApartmentDto dto) {
        Apartment apartment = apartmentConverterDto.convertDtoToEntity(dto);
        return apartmentDao.save(apartment);
    }

    // @Cacheable("apartments")
    @Override
    public Apartment findById(int id) {
        return apartmentDao.findById(id);
    }

    @Override
    public void update(ApartmentDto dto, Integer id) {
        Apartment apartment = apartmentConverterDto.convertDtoToEntity(dto);
        apartmentDao.update(apartment, id);
    }

    // @CacheEvict(value = "apartments", allEntries = true)
    @Override
    public void deleteById(int id) {
        apartmentDao.deleteById(id);

    }

    // @CacheEvict(value = "apartments", allEntries = true)
    public void evictCache() {
    }

}


package by.pertsev.hotel.hiber.dao.impl;

import by.pertsev.hotel.hiber.controller.exception.NotFoundException;
import by.pertsev.hotel.hiber.dao.ApartmentDao;
import by.pertsev.hotel.hiber.dao.proxy.ProxyApartmentRepository;
import by.pertsev.hotel.hiber.model.Apartment;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

@Repository
@AllArgsConstructor
@Transactional
public class ApartmentDaoImpl implements ApartmentDao {
    private static final String FIND_APARTMENT_SUITABLE_REQUEST = "SELECT DISTINCT apartments.apartment_id  , apartments.category_id, apartments.capacity, apartments.picture , apartments.description FROM hotelappdb.apartments LEFT JOIN timesheet ON apartments.apartment_id=timesheet.apartment_id WHERE (NOT (timesheet.reserved_date >= ? AND timesheet.reserved_date <= ?) OR timesheet.reserved_date IS NULL) AND apartments.category_id=? AND apartments.capacity>=?";
    @PersistenceContext
    private final EntityManager entityManager;
    private final ProxyApartmentRepository proxyApartmentRepository;


    @Override
    public Apartment save(Apartment entity) {
        proxyApartmentRepository.save(entity);
        return entity;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Apartment> findAll(Pageable pageable) {
        return proxyApartmentRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Apartment findById(Integer id) {
        return proxyApartmentRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public void update(Apartment entity, Integer id) {
        entity.setApartmentId(id);
        entityManager.merge(entity);
    }

    @Override
    public void deleteById(int id) {
        proxyApartmentRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Apartment> findApartmentsSuitableForRequest(LocalDate start, LocalDate end, int category, int quantity) {

        return entityManager.createNativeQuery(FIND_APARTMENT_SUITABLE_REQUEST, Apartment.class)
                .setParameter(1, start)
                .setParameter(2, end)
                .setParameter(3, category)
                .setParameter(4, quantity).getResultList();
    }
}

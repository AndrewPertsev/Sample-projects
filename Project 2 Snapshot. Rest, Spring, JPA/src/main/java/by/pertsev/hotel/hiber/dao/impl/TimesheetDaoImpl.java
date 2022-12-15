package by.pertsev.hotel.hiber.dao.impl;

import by.pertsev.hotel.hiber.controller.exception.NotFoundException;
import by.pertsev.hotel.hiber.dao.TimesheetDao;
import by.pertsev.hotel.hiber.dao.proxy.ProxyTimesheetRepository;
import by.pertsev.hotel.hiber.model.Timesheet;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
@AllArgsConstructor
public class TimesheetDaoImpl implements TimesheetDao {
    private static final String INSERT_INTO_TIMESHEET_CONFIRMED_DAYS = "INSERT INTO hotelappdb.timesheet SET apartment_id=? , reserved_date=?, isreserved=?";

    @PersistenceContext
    private final EntityManager entityManager;

    private final ProxyTimesheetRepository proxyTimesheetRepository;

    @Override
    public Timesheet save(Timesheet entity) {
        proxyTimesheetRepository.save(entity);
        return entity;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Timesheet> findAll(Pageable pageable) {
        return proxyTimesheetRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Timesheet findById(Integer id) {
        return proxyTimesheetRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public void update(Timesheet entity, Integer id) {
        entity.setTimesheetId(id);
        entityManager.merge(entity);
    }

    @Override
    public void deleteById(int id) {
        proxyTimesheetRepository.deleteById(id);
    }

}


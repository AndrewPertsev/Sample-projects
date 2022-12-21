package by.pertsev.hotel.hiber.service.impl;

import by.pertsev.hotel.hiber.dao.TimesheetDao;
import by.pertsev.hotel.hiber.model.RequestUser;
import by.pertsev.hotel.hiber.model.Timesheet;
import by.pertsev.hotel.hiber.model.converter.TimesheetConverterDto;
import by.pertsev.hotel.hiber.model.dto.TimesheetDto;
import by.pertsev.hotel.hiber.service.TimesheetServiceable;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;

@Service
@AllArgsConstructor
@Slf4j
@Transactional

public class TimesheetServiceImpl implements TimesheetServiceable {
    private final TimesheetConverterDto timesheetConverterDto;
    private final TimesheetDao timesheetDao;
    private final boolean IS_RESERVED_APARTMENT_TRUE = true;

    @Override
    public Timesheet save(TimesheetDto dto) {
        Timesheet entity = timesheetConverterDto.convertDtoToEntity(dto);
        return timesheetDao.save(entity);
    }

    @Override
    public Page<Timesheet> findAll(Pageable pageable) {
        return timesheetDao.findAll(pageable);
    }

    @Override
    public Timesheet findById(int id) {
        return timesheetDao.findById(id);
    }

    @Override
    public void update(TimesheetDto dto, Integer id) {
        Timesheet timesheet = timesheetConverterDto.convertDtoToEntity(dto);
        timesheetDao.update(timesheet, id);

    }


    @Override
    public void deleteById(int timesheetId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean reserveConfirmedDaysByOffer(int apartmentId, RequestUser requestUser) {
        boolean isDone;

        LocalDate bookedFrom = requestUser.getStart();
        LocalDate bookedBefore = requestUser.getEnd();
        long duration = Duration.between(bookedFrom.atStartOfDay(), bookedBefore.atStartOfDay()).toDays();
        for (int i = 0; i < duration; i++) {
            bookedFrom = bookedFrom.plusDays(1);
            TimesheetDto timesheet = new TimesheetDto(apartmentId, IS_RESERVED_APARTMENT_TRUE, bookedFrom);
            save(timesheet);
        }
        isDone = true;
        return isDone;
    }


}


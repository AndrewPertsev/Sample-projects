package by.pertsev.hotel.hiber.model.converter;

import by.pertsev.hotel.hiber.model.Timesheet;
import by.pertsev.hotel.hiber.model.dto.TimesheetDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TimesheetConverterDto implements ConverterDTO<TimesheetDto, Timesheet> {

    @Override
    public TimesheetDto convertEntityToDto(Timesheet entity) {
        log.info("Convert Entity To Dto");
        return TimesheetDto.builder()
                .timesheetId(entity.getTimesheetId())
                .apartmentId(entity.getApartmentId())
                .isReserved(entity.isReserved())
                .reservedDate(entity.getReservedDate())
                .build();
    }

    @Override
    public Timesheet convertDtoToEntity(TimesheetDto dto) {
        return Timesheet.builder()
                .apartmentId(dto.getApartmentId())
                .isReserved(dto.isReserved())
                .reservedDate(dto.getReservedDate())
                .build();
    }
}

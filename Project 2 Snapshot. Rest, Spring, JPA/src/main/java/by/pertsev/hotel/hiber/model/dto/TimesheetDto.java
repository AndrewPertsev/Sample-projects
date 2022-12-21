package by.pertsev.hotel.hiber.model.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

import static by.pertsev.hotel.hiber.model.DBColumnLabel.DATE_PATTERN;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class TimesheetDto extends HotelDTOEntity {

    private Integer timesheetId;
    @NonNull
    private int apartmentId;
    @NonNull
    private boolean isReserved;
    @NonNull
    @DateTimeFormat(pattern = DATE_PATTERN)

    private LocalDate reservedDate;


}

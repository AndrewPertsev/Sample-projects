package by.pertsev.hotel.hiber.dto;

import lombok.*;

import java.time.LocalDate;

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
    private LocalDate reservedDate;


}

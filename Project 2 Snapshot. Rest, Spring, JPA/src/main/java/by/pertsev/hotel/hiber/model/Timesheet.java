package by.pertsev.hotel.hiber.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

import static by.pertsev.hotel.hiber.model.DBColumnLabel.*;

@Entity
@Table(name = TIMESHEET)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"timesheetId"})
@Builder
public class Timesheet extends HotelEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = TIMESHEET_ID)
    private Integer timesheetId;

    @Column(name = TIMESHEET_APARTMENT_ID)
    private int apartmentId;

    @Column(name = TIMESHEET_IS_RESERVED)
    private boolean isReserved;

    @Column(name = TIMESHEET_RESERVED_DATE)
    @DateTimeFormat(pattern = DATE_PATTERN)
    private LocalDate reservedDate;

    @Override
    public String toString() {
        return "Timesheet{" +
                "timesheetId=" + timesheetId +
                ", apartmentId=" + apartmentId +
                ", isReserved=" + isReserved +
                ", reservedDate=" + reservedDate +
                '}';
    }
}

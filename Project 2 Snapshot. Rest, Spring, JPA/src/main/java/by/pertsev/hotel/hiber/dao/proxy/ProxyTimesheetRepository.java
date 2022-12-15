package by.pertsev.hotel.hiber.dao.proxy;

import by.pertsev.hotel.hiber.model.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProxyTimesheetRepository extends JpaRepository<Timesheet, Integer> {
}

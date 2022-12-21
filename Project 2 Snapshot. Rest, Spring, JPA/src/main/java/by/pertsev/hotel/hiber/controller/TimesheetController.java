package by.pertsev.hotel.hiber.controller;

import by.pertsev.hotel.hiber.controller.exception.NotFoundException;
import by.pertsev.hotel.hiber.model.Timesheet;
import by.pertsev.hotel.hiber.model.dto.TimesheetDto;
import by.pertsev.hotel.hiber.service.TimesheetServiceable;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static by.pertsev.hotel.hiber.controller.TimesheetController.REST_URL_TIMESHEETS;

@RestController
@RequestMapping(REST_URL_TIMESHEETS)
@AllArgsConstructor
public class TimesheetController {
    public static final String REST_URL_TIMESHEETS = "/timesheets";
    private final TimesheetServiceable timesheetServiceable;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Timesheet save(@Valid @RequestBody TimesheetDto dto) {
        return timesheetServiceable.save(dto);
    }


    @GetMapping("/{id}")
    public Timesheet findById(@PathVariable int id) throws NotFoundException {
        return timesheetServiceable.findById(id);
    }

    @GetMapping
    public Page findAll(Pageable pageable) {
        Page<Timesheet> timesheets = timesheetServiceable.findAll(pageable);
        return timesheets;
    }


    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody TimesheetDto dto, @PathVariable Integer id) {
        timesheetServiceable.update(dto, id);
    }

}

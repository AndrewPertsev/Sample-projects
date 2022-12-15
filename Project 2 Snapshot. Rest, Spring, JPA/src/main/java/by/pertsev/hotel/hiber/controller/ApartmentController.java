package by.pertsev.hotel.hiber.controller;

import by.pertsev.hotel.hiber.controller.exception.NotFoundException;
import by.pertsev.hotel.hiber.dto.ApartmentDto;
import by.pertsev.hotel.hiber.model.Apartment;
import by.pertsev.hotel.hiber.service.ApartmentServiceable;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static by.pertsev.hotel.hiber.controller.ApartmentController.REST_URL_APARTMENTS;

@RestController
@RequestMapping(REST_URL_APARTMENTS)
@AllArgsConstructor

public class ApartmentController {
    public static final String REST_URL_APARTMENTS = "/apartments";
    private final ApartmentServiceable apartmentServiceable;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Apartment save(@RequestBody ApartmentDto dto) {
        return apartmentServiceable.save(dto);
    }


    @GetMapping("/{id}")
    public Apartment findById(@PathVariable int id) throws NotFoundException {
        return apartmentServiceable.findById(id);
    }

    @GetMapping
    public Page<Apartment> findAll(Pageable pageable) {
        Page<Apartment> apartments = apartmentServiceable.findAll(pageable);
        return apartments;
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody ApartmentDto dto, @PathVariable Integer id) {
        apartmentServiceable.update(dto, id);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        apartmentServiceable.deleteById(id);
    }

}

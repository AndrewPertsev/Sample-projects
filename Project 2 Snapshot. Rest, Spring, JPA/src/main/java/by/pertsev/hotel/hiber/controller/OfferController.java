package by.pertsev.hotel.hiber.controller;

import by.pertsev.hotel.hiber.controller.exception.NotFoundException;
import by.pertsev.hotel.hiber.dto.OfferDto;
import by.pertsev.hotel.hiber.model.Offer;
import by.pertsev.hotel.hiber.service.OfferServiceable;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(OfferController.REST_URL_OFFERS)
@AllArgsConstructor
public class OfferController {
    public static final String REST_URL_OFFERS = "/offers";
    private final OfferServiceable offerServiceable;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Offer save(@RequestBody OfferDto dto) {
        return offerServiceable.save(dto);
    }


    @GetMapping("/{id}")
    public Offer findById(@PathVariable int id) throws NotFoundException {
        return offerServiceable.findById(id);
    }

    @GetMapping
    public Page<Offer> findAll(Pageable pageable) {
        Page<Offer> offers = offerServiceable.findAll(pageable);
        return offers;
    }

////todo            throw new ResourceNotFoundException("There are no offers on " + pageable.getPageNumber() + " page");


    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody OfferDto dto, @PathVariable Integer id) {
        offerServiceable.update(dto, id);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        offerServiceable.deleteById(id);
    }


//    @GetMapping("/guest/{id}")
//    public Page<Offer> findByGuestId(@PathVariable int id, Pageable pageable) {
//        Page<Offer> offers = offerServiceable.findOffersByGuestId(id, pageable);
//        return offers;
//    }

    @GetMapping("/pricelist/request/{requestId}")
    public List<OfferDto> makePriceList(@PathVariable int requestId) throws NotFoundException {
        return offerServiceable.makeAvailablePriceList(requestId);
    }
}




package by.pertsev.hotel.hiber.service.impl;

import by.pertsev.hotel.hiber.dao.*;
import by.pertsev.hotel.hiber.model.Apartment;
import by.pertsev.hotel.hiber.model.Offer;
import by.pertsev.hotel.hiber.model.RequestUser;
import by.pertsev.hotel.hiber.model.converter.ConverterDTO;
import by.pertsev.hotel.hiber.model.dto.OfferDto;
import by.pertsev.hotel.hiber.service.ApartmentServiceable;
import by.pertsev.hotel.hiber.service.OfferServiceable;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class OfferServiceImpl implements OfferServiceable {
    private final OfferDao offerDao;
    private final RequestUserDao requestUserDao;
    private final CategoryDao categoryDao;
    private final ApartmentDao apartmentDao;
    private final TimesheetDao timesheetDao;
    private final ConverterDTO<OfferDto, Offer> offerConverterDto;
    private final ApartmentServiceable apartmentServiceable;


    @Override
    public Offer save(OfferDto dto) {  //////////////////////todo
        Offer offer = offerConverterDto.convertDtoToEntity(dto);
        // timesheetDao.reserveConfirmedDaysByOffer(offer.getApartment().getApartmentId(), offer.getRequestUser().getStart(), offer.getRequestUser().getEnd());
        // requestUserDao.setRequestIsRespondedStatus(true, offer.getRequestUser().getRequestId());
////////        return offerDao.create(assembleOfferForApartment(offer.getApartment().getApartmentId(), offer.getRequestUser().getRequestId());;
        return offerDao.save(offer);
    }


    @Override
    public Page<Offer> findAll(Pageable pageable) {
        return offerDao.findAll(pageable);
    }


    @Override
    public Offer findById(int id) {
        return offerDao.findById(id);
    }


    @Override
    public void update(OfferDto dto, Integer id) {

        Offer offer = offerDao.findById(id);

        if (dto.getApartment() != null) {
            offer.setApartment(dto.getApartment());
        }
        if (dto.getPriceOffer() != null) {
            offer.setPriceOffer(dto.getPriceOffer());
        }
        if (dto.getQuantity() > 0) {
            offer.setQuantity(dto.getQuantity());
        }
        if (dto.getRequestUser() != null) {
            offer.setRequestUser(dto.getRequestUser());
        }
        if (dto.getStart() != null) {
            offer.setStart(dto.getStart());
        }
        if (dto.getEnd() != null) {
            offer.setEnd(dto.getEnd());
        }
        if (dto.isClosed() != offer.isClosed()) {
            offer.setClosed(dto.isClosed());
        }
        if (dto.isPaid() != offer.isPaid()) {
            offer.setPaid(dto.isPaid());
        }
        if (dto.isSent() != offer.isSent()) {
            offer.setSent(dto.isSent());
        }

    }

    @Override
    public void deleteById(int id) {
        offerDao.deleteById(id);

    }


    @Override
    public OfferDto assembleOfferForApartment(int suitableApartmentId, int requestId) {
        RequestUser requestUser = requestUserDao.findById(requestId);
        Apartment apartment = apartmentDao.findById(suitableApartmentId);
        BigDecimal offerPrice = calculateOfferTotalPrice(requestId);

        return OfferDto.builder()
                .requestUser(requestUser)
                .apartment(apartment)
                .quantity(requestUser.getQuantity())
                .start(requestUser.getStart())
                .end(requestUser.getEnd())
                .isSent(false)
                .isPaid(false)
                .isClosed(false)
                .priceOffer(offerPrice)
                .build();
    }

    @Override
    public BigDecimal calculateOfferTotalPrice(int requestId) {

        RequestUser requestUser = requestUserDao
                .findById(requestId);
        BigDecimal periodBooking = BigDecimal.valueOf(DAYS.between(requestUser.getStart(), requestUser.getEnd()));

        BigDecimal priceApartment = categoryDao.findById(requestUser.getCategory().getCategoryId())
                .getCategoryPrice();

        BigDecimal quantityPersonDays = periodBooking.multiply(BigDecimal.valueOf(requestUser.getQuantity()));
        BigDecimal total = quantityPersonDays.multiply(priceApartment);

        return total;
    }

    @Override
    public List<OfferDto> makeAvailablePriceList(int requestId) {

        List<OfferDto> availableDtoOffers = apartmentServiceable.findApartmentsAvailableForRequest(requestId)
                .stream()
                .map(apartment -> assembleOfferForApartment(apartment.getApartmentId(), requestId))
                .collect(Collectors.toList());

        return availableDtoOffers;
    }

//    @Override
//    public Page<Offer> findOffersByGuestId(int idGuest, Pageable pageable) {
//        Page<Offer> offers = offerDao.findOffersByUserId(idGuest, pageable);
//        return offers;
//    }
}

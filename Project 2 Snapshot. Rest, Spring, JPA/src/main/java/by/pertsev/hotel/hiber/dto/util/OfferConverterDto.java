package by.pertsev.hotel.hiber.dto.util;

import by.pertsev.hotel.hiber.dto.OfferDto;
import by.pertsev.hotel.hiber.model.Offer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OfferConverterDto implements ConverterDTO<OfferDto, Offer> {

    @Override
    public OfferDto convertEntityToDto(Offer entity) {
        log.info("Convert Entity To Dto");

        return OfferDto.builder()
                .offerId(entity.getOfferId())
                .requestUser(entity.getRequestUser())
                .apartment(entity.getApartment())
                .start(entity.getStart())
                .end(entity.getEnd())
                .isSent(entity.isSent())
                .isPaid(entity.isPaid())
                .isClosed(entity.isClosed())
                .priceOffer(entity.getPriceOffer())
                .build();
    }

    @Override
    public Offer convertDtoToEntity(OfferDto dto) {
        return Offer.builder()
                .requestUser(dto.getRequestUser())
                .apartment(dto.getApartment())
                .start(dto.getStart())
                .end(dto.getEnd())
                .isSent(dto.isSent())
                .isPaid(dto.isPaid())
                .isClosed(dto.isClosed())
                .priceOffer(dto.getPriceOffer())
                .build();
    }
}

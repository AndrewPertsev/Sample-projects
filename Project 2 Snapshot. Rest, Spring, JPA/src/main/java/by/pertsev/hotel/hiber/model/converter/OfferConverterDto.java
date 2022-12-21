package by.pertsev.hotel.hiber.model.converter;

import by.pertsev.hotel.hiber.model.Offer;
import by.pertsev.hotel.hiber.model.dto.OfferDto;
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
                .quantity(entity.getQuantity())
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
                .quantity(dto.getQuantity())
                .isSent(dto.isSent())
                .isPaid(dto.isPaid())
                .isClosed(dto.isClosed())
                .priceOffer(dto.getPriceOffer())
                .build();
    }
}

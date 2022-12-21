package by.pertsev.hotel.hiber.model.converter;

import by.pertsev.hotel.hiber.model.HotelEntity;
import by.pertsev.hotel.hiber.model.dto.HotelDTOEntity;

public interface ConverterDTO<D extends HotelDTOEntity, E extends HotelEntity> {

    E convertDtoToEntity(D dto);

    D convertEntityToDto(E entity);
}

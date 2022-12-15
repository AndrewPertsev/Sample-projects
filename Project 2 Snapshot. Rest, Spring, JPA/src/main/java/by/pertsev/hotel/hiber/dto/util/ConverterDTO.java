package by.pertsev.hotel.hiber.dto.util;

import by.pertsev.hotel.hiber.dto.HotelDTOEntity;
import by.pertsev.hotel.hiber.model.HotelEntity;

public interface ConverterDTO<D extends HotelDTOEntity, E extends HotelEntity> {

    E convertDtoToEntity(D dto);

    D convertEntityToDto(E entity);
}

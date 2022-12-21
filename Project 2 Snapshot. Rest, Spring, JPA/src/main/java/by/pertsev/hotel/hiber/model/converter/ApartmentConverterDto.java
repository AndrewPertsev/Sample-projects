package by.pertsev.hotel.hiber.model.converter;

import by.pertsev.hotel.hiber.model.Apartment;
import by.pertsev.hotel.hiber.model.dto.ApartmentDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ApartmentConverterDto implements ConverterDTO<ApartmentDto, Apartment> {

    @Override
    public ApartmentDto convertEntityToDto(Apartment entity) {
        log.info("Convert Entity To Dto");

        return ApartmentDto.builder()
                .apartmentId(entity.getApartmentId())
                .category(entity.getCategory())
                .capacity(entity.getCapacity())
                .description(entity.getDescription())
                .pathToPicture(entity.getPathToPicture())
                .build();
    }

    @Override
    public Apartment convertDtoToEntity(ApartmentDto dto) {
        return Apartment.builder()
                .apartmentId(dto.getApartmentId())
                .category(dto.getCategory())
                .capacity(dto.getCapacity())
                .description(dto.getDescription())
                .pathToPicture(dto.getPathToPicture())
                .build();
    }

}

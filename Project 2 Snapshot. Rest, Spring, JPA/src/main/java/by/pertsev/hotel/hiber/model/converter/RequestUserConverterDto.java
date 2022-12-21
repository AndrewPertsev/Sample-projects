package by.pertsev.hotel.hiber.model.converter;

import by.pertsev.hotel.hiber.model.RequestUser;
import by.pertsev.hotel.hiber.model.dto.RequestUserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static by.pertsev.hotel.hiber.model.RequestUser.DEFAULT_DISTANCE;

@Slf4j
@Service
public class RequestUserConverterDto implements ConverterDTO<RequestUserDto, RequestUser> {

    @Override
    public RequestUserDto convertEntityToDto(RequestUser entity) {
        log.info("Convert Entity To Dto");

        return RequestUserDto.builder()
                .requestId(entity.getRequestId())
                .user((entity.getUser()))
                .category(entity.getCategory())
                .quantity(entity.getQuantity())
                .distance(DEFAULT_DISTANCE)
                .start(entity.getStart())
                .end(entity.getEnd())
                .dateRequest(entity.getDateRequest())
                .isResponded(entity.isResponded())
                .build();
    }

    @Override
    public RequestUser convertDtoToEntity(RequestUserDto dto) {
        return RequestUser.builder()
                .user(dto.getUser())
                .category(dto.getCategory())
                .quantity(dto.getQuantity())
                .distance(DEFAULT_DISTANCE)
                .start(dto.getStart())
                .end(dto.getEnd())
                .dateRequest(LocalDate.now())
                .isResponded(dto.isResponded())
                .build();
    }
}

package by.pertsev.hotel.hiber.service.validation;

import by.pertsev.hotel.hiber.model.dto.OfferDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@AllArgsConstructor
public class OfferValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return OfferDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        OfferDto offerDto = (OfferDto) target;

    }
}

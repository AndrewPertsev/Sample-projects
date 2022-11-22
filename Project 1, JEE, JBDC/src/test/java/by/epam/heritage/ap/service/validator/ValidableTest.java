package by.epam.heritage.ap.service.validator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static by.epam.heritage.ap.service.validator.ValidatorConstants.PATTERN_EMAIL;
import static by.epam.heritage.ap.service.validator.ValidatorConstants.PATTERN_NAME;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class ValidableTest {

    @ParameterizedTest
    @MethodSource
    void testValidateParameterStringClassName(Boolean expected, String actual) {
        assertEquals(expected, Validable.validateParameterStringClass(actual, PATTERN_NAME));
    }

    static Stream<Arguments> testValidateParameterStringClassName() {
        return Stream.of(
                arguments(TRUE, "Аннa"),      //todo mono language pattern
                arguments(FALSE, "-John"),
                arguments(FALSE, "4+7"),
                arguments(FALSE, ""),
                arguments(FALSE, "Shelby Browning")
        );
    }


    @ParameterizedTest
    @MethodSource("TestIntegerValidatorDataProvider")
    void testValidateStringParameterIntegerClass(Boolean expected, String actual, int max, int min) {
        assertEquals(expected, Validable.validateStringParameterIntegerClass(actual, max, min));
    }

    static Stream<Arguments> TestIntegerValidatorDataProvider() {
        int min = 0;
        int max = 100;
        return Stream.of(
                arguments(TRUE, "42", max, min),
                arguments(FALSE, "l", max, min),
                arguments(FALSE, "digit", max, min),
                arguments(FALSE, "", max, min),
                arguments(FALSE, "101", max, min)
        );
    }


    @ParameterizedTest
    @MethodSource
    void testValidateStringParameterClassEmail(Boolean expected, String actual) {
        assertEquals(expected, Validable.validateParameterStringClass(actual, PATTERN_EMAIL));
    }
    static Stream<Arguments> testValidateStringParameterClassEmail() {
        return Stream.of(
                arguments(TRUE, "good@mail.we"),
                arguments(FALSE, "mail.we"),
                arguments(FALSE, "bad@дом.we"),
                arguments(FALSE, "good@mailwe"),
                arguments(FALSE, "$$$$$@mail.w")
        );
    }
}
package by.epam.heritage.ap.service.validator;


public final class ValidatorFactory {

    private static volatile ValidatorFactory instance ;

    private final ApartmentValidator apartmentValidator = new ApartmentValidator();
    private final OfferStatusValidator offerValidator = new OfferStatusValidator();
    private final RequestValidator requestValidator = new RequestValidator();
    private final GuestValidator guestValidator = new GuestValidator();


    private ValidatorFactory() {
    }

    public static ValidatorFactory getInstance() {
        if (instance == null) {
            synchronized (ValidatorFactory.class) {
                if (instance == null) {
                    instance = new ValidatorFactory();
                }
            }
        }
        return instance;
    }


    public ApartmentValidator getApartmentValidator() {
        return apartmentValidator;
    }

    public RequestValidator getRequestValidator() {
        return requestValidator;
    }

    public OfferStatusValidator getOfferValidator() {
        return offerValidator;
    }

    public GuestValidator getGuestValidator() {
        return guestValidator;
    }
}

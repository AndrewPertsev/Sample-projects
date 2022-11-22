package by.epam.heritage.ap.service.builder;

public final class BuilderFactory {

    private volatile static BuilderFactory instance;

    private final RequestUserBuilder requestBuilder = new RequestUserBuilder();
    private final ApartmentBuilder apartmentBuilder = new ApartmentBuilder();
    private final OfferStatusBuilder offerBuilder = new OfferStatusBuilder();
    private final GuestBuilder guestBuilder = new GuestBuilder();

    private BuilderFactory() {
    }

    public static BuilderFactory getInstance() {
        if (instance == null) {
            synchronized (BuilderFactory.class) {
                if (instance == null) {
                    instance = new BuilderFactory();
                }
            }
        }
        return instance;
    }


    public ApartmentBuilder getApartmentBuilder() {
        return apartmentBuilder;
    }

    public RequestUserBuilder getRequestBuilder() {
        return requestBuilder;
    }

    public OfferStatusBuilder getOfferBuilder() {
        return offerBuilder;
    }

    public GuestBuilder getGuestBuilder() {
        return guestBuilder;
    }


}

package by.epam.heritage.ap.service;

import by.epam.heritage.ap.service.impl.*;

public final class ServiceFactory {

    private static volatile ServiceFactory instance ;

    private final ApartmentServiceable apartmentService = new ApartmentServiceImpl();
    private final RequestServiceable requestService = new RequestServiceImpl();
    private final UserServiceable guestService = new UserServiceImpl();
    private final OfferServiceable offerService = new OfferServiceImpl();

    private  final TimesheetServiceable timesheetService = new TimesheetServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        if (instance == null) {
            synchronized (ServiceFactory.class) {
                if (instance == null) {
                        instance = new ServiceFactory();
                }
            }
        }
        return instance;
    }


    public ApartmentServiceable getApartmentService() {
        return apartmentService;
    }

    public TimesheetServiceable getTimesheetService() {
        return timesheetService;
    }

    public RequestServiceable getRequestService() {
        return requestService;
    }

    public UserServiceable getUserService() {
        return guestService;
    }

    public OfferServiceable getOfferService() {
        return offerService;
    }


}

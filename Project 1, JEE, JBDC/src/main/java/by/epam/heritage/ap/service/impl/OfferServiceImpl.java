package by.epam.heritage.ap.service.impl;

import by.epam.heritage.ap.model.Offer;
import by.epam.heritage.ap.model.RequestUser;
import by.epam.heritage.ap.repository.DAOException;
import by.epam.heritage.ap.repository.DaoFactory;
import by.epam.heritage.ap.repository.OfferDao;
import by.epam.heritage.ap.service.OfferServiceable;
import by.epam.heritage.ap.service.ServiceException;
import by.epam.heritage.ap.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class OfferServiceImpl implements OfferServiceable {
    private static final Logger logger = LogManager.getLogger(OfferServiceImpl.class);
    OfferDao offerDao = DaoFactory.getInstance().getOfferDao();

    @Override
    public boolean add(Offer entity) throws ServiceException {
        boolean done ;
        try {
            done = offerDao.insert(entity);
        } catch (DAOException e) {
            logger.error("Service can't add element ", e);
            throw new ServiceException(e);
        }
        return done;
    }

    public BigDecimal calculateOfferTotalPrice(int requestId) throws ServiceException {
        BigDecimal total = BigDecimal.valueOf(1);
        RequestUser requestUser;
        BigDecimal priceTransfe;
        BigDecimal priceMenu;

        try {
            requestUser = DaoFactory.getInstance().getRequestDao().findById(requestId);
        } catch (DAOException e) {
            logger.error("Service can't find element ", e);
            throw new ServiceException(e);
        }
        if (requestUser != null) {
            BigDecimal periodBooking = BigDecimal.valueOf(DAYS.between(requestUser.getStart(), requestUser.getEnd()));
            BigDecimal quantityPersons = BigDecimal.valueOf(requestUser.getQuantity());
            BigDecimal priceApartment;
            try {
                priceApartment = DaoFactory.getInstance().getCategoryDao().getCategoryPrice(requestUser.getCategory());
                priceMenu = DaoFactory.getInstance().getMenuDao().findById(requestUser.getMenu()).getPrice();
                priceTransfe = DaoFactory.getInstance().getTransferDao().findById(requestUser.getTransfer()).getPrice();
            } catch (DAOException e) {
                logger.error("Service can't calculate price transfer ", e);
                throw new ServiceException(e);
            }
            BigDecimal distance = new BigDecimal(requestUser.getDistance());
            BigDecimal priceTransfer = priceTransfe.multiply(distance);
            BigDecimal quantityPersonDays = periodBooking.multiply(quantityPersons);
            BigDecimal priceSingleServicePerDay = priceApartment.add(priceMenu);
            BigDecimal personDaysWithDailyServices = quantityPersonDays.multiply(priceSingleServicePerDay);
            total = personDaysWithDailyServices.add(priceTransfer);
        }
        return total;
    }


    @Override
    public List<Offer> getOffersByGuestId(int id) throws ServiceException {
        List<Offer> offers;
        try {
            offers = offerDao.getOffersByGuestId(id);
        } catch (DAOException e) {
            logger.error("Service can't get element ", e);
            throw new ServiceException(e);
        }
        return offers;
    }


    @Override
    public List<Offer> findAll() throws ServiceException {
        List<Offer> offers;
        try {
            offers = offerDao.findAll();
        } catch (DAOException e) {
            logger.error("Service can't find element ", e);
            throw new ServiceException(e);
        }
        return offers;
    }

    @Override
    public boolean update(Offer entity) throws ServiceException {
        boolean done ;
        try {
            done = offerDao.update(entity);
        } catch (DAOException e) {
            logger.error("Service can't update element ", e);
            throw new ServiceException(e);
        }
        return done;
    }


    @Override
    public Offer assembleOfferForApartment(int suitableApartmentId, int requestId) throws ServiceException {
        Offer offer = new Offer();
        RequestUser requestUserUsers;

        try {
            requestUserUsers = DaoFactory.getInstance().getRequestDao().findById(requestId);
        } catch (DAOException e) {
            logger.error("Service can't assemble offer", e);
            throw new ServiceException(e);
        }
        BigDecimal offerPrice = ServiceFactory.getInstance().getOfferService().calculateOfferTotalPrice(requestId);

        offer.setRequestId(requestId);
        offer.setQuantity(requestUserUsers.getQuantity());
        offer.setTransfer(requestUserUsers.getTransfer());
        offer.setMenu(requestUserUsers.getMenu());
        offer.setApartmentId(suitableApartmentId);
        offer.setStart(requestUserUsers.getStart());
        offer.setEnd(requestUserUsers.getEnd());
        offer.setDateSent(LocalDate.now());
        offer.setDatePaid(LocalDate.now());
        offer.setDateClosed(LocalDate.now());
        offer.setSent(false);
        offer.setPaid(false);
        offer.setClosed(false);
        offer.setPriceOffer(offerPrice);

        return offer;
    }


    @Override
    public Offer findById(int id) throws ServiceException {
        Offer offer;
        try {
            offer = offerDao.findById(id);
        } catch (DAOException e) {
            logger.error("Service can't find element ", e);
            throw new ServiceException(e);
        }
        return offer;
    }

}

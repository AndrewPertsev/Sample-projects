package by.epam.heritage.ap.repository.impl;

import by.epam.heritage.ap.model.Timesheet;
import by.epam.heritage.ap.repository.DAOException;
import by.epam.heritage.ap.repository.DaoFactory;
import by.epam.heritage.ap.repository.TimesheetDao;
import by.epam.heritage.ap.repository.connection.ConnectionPool;
import by.epam.heritage.ap.repository.connection.PoolException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

class TimesheetDaoImplTest {

    @Mock
    private TimesheetDao timesheetDao = DaoFactory.getInstance().getTimesheetDao();

    @BeforeAll
    public static void initConnection() throws PoolException {
        ConnectionPool.getInstance().init();

    }

    @AfterAll
    public static void closeConnection() {
        ConnectionPool.getInstance().clearConnectionQueue();

    }

    @Test
    void testfindAll() throws DAOException {
        assertNotNull(DaoFactory.getInstance().getTimesheetDao().findAll());

    }

    @Test
    void testfindAllMockito() throws DAOException {
        timesheetDao = Mockito.mock(TimesheetDao.class);

        given(timesheetDao.findAll()).willReturn(new ArrayList<Timesheet>());

        assertNotNull(timesheetDao.findAll().size());

    }

    @Test
    void testReserveConfirmedDaysByOffer() throws DAOException {
        timesheetDao = Mockito.mock(TimesheetDao.class);
        boolean actual ;
        int apartmentId = 102;
        LocalDate bookedFrom = LocalDate.parse( "2022-04-01");
        LocalDate bookedBefore = LocalDate.parse("2022-04-03");

        Mockito.doReturn(true)
                .when(timesheetDao)
                .reserveConfirmedDaysByOffer(apartmentId, bookedFrom, bookedBefore);

        actual = timesheetDao.reserveConfirmedDaysByOffer(apartmentId, bookedFrom, bookedBefore);

        assertTrue(actual);

    }

    @Test
    void findByid() throws DAOException {
        timesheetDao = Mockito.mock(TimesheetDao.class);
        int id = 8;

        given(timesheetDao.findById(id)).willReturn(new Timesheet());

        assertNotNull(timesheetDao.findById(id));

    }

//    @Test
//    void testQuantityReserveConfirmedDaysByOffer() throws DAOException {
//        timesheetDao = Mockito.mock(TimesheetDao.class);
//        int timesheetSizeAfter;
//        int timesheetSizeBefore;
//
//       int expected = 2;
//        int apartmentId = 103;
//        String dateIn = "2022-04-11";
//        String dateOut = "2022-04-13";
//
//        LocalDate bookedFrom = LocalDate.parse(dateIn);
//        LocalDate bookedBefore = LocalDate.parse(dateOut);
//        List<Timesheet> timesheetBef = new ArrayList<>();
//        List<Timesheet> timesheetAft = new ArrayList<>();
//        int diff;
//
//
//        // given(timesheetDao.reserveConfirmedDaysByOffer(apartmentId,bookedFrom,bookedBefore)).willReturn(true);
//        Mockito.doReturn(expected)
//                .when(timesheetDao)
//                .reserveConfirmedDaysByOffer(apartmentId, bookedFrom, bookedBefore);
//
//        timesheetBef = timesheetDao.findAll();
//        timesheetSizeBefore = timesheetBef.size();
//        System.out.println("expecte1d" + timesheetSizeBefore);///////////////////////////////////////
//
//        timesheetDao.reserveConfirmedDaysByOffer(apartmentId, bookedFrom, bookedBefore);
//
//        timesheetAft = timesheetDao.findAll();
//        timesheetSizeAfter = timesheetAft.size();
//        System.out.println("size " + timesheetSizeAfter);/////////////////////////////////////
//        // Mockito.verify(timesheetDao, Mockito.times(0)).reserveConfirmedDaysByOffer(apartmentId, bookedFrom, bookedBefore);
//        //   assertEquals(expected, timesheetSizeAfter - timesheetSizeBefore);
//
//        diff = timesheetSizeBefore - timesheetSizeAfter;
//        assertEquals(expected, diff);
//        ;
//    }


}
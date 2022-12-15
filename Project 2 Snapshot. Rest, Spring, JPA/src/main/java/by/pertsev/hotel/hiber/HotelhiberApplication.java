package by.pertsev.hotel.hiber;

import by.pertsev.hotel.hiber.controller.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.ForkJoinPool;

@SpringBootApplication
@AllArgsConstructor
public class HotelhiberApplication implements ApplicationRunner {

//    private final RequestUserDaoImpl requestUserDaoImpl;
//    private final UserDaoImpl userDaoImpl;
//    private final OfferDaoImpl offerDaoImpl;
//    private final ApartmentDaoImplHiber apartmentDaoImplHiber;
//    private final TimesheetDaoImpl timesheetDaoImpl;
//    private final ApartmentServiceable apartmentService;
//    private final OfferServiceable offerServiceable;


    public static void main(String[] args) {
        SpringApplication.run(HotelhiberApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public void run(ApplicationArguments args) throws NotFoundException {

        final Logger LOGGER = LoggerFactory.getLogger(HotelhiberApplication.class);
        ForkJoinPool commonPool = ForkJoinPool.commonPool();
        System.out.println("00000000000000000000000000000000000000000000000000______" + commonPool.getParallelism());

        //    System.out.println("user APARTMENT ________________________1__________________________::::   " + apartmentDaoImplHiber.findAll());
//        System.out.println("user APARTMENT _________________________1____id______" + apartmentDaoImplHiber.findById(105));
//
        //      System.out.println("user  categoryDao_________________________2_________________ " + categoryDaoImpl.findAll());
//        System.out.println("user  categoryDao_________________________2____id______ " + categoryDaoImpl.findById(2));

        //     System.out.println("OFFER :::: ___________________________________3__________________________________  " + offerDaoImpl.findAll());
        // System.out.println("OFFER :::: __________________________________3____id________  " + offerDaoImpl.findById(131));

        //       System.out.println("user REQUEST _____________________________4__________________________________ " + requestUserDaoImpl.findAll());
//        System.out.println("user REQUEST _____________________________4_____id_____ " + requestUserDaoImpl.findById(56));
//
        //       System.out.println("user timesheetDao __________________________5_________________________ " + timesheetDaoImpl.findAll());
//        System.out.println("user timesheetDao __________________________5___id______ " + timesheetDaoImpl.findById(200));
//
        //    System.out.println("user ALL::::___________________________________6____________________________   " + userDaoImpl.findAll());
//        System.out.println("user ID::::____________________________________6____id__________ " + userDaoImpl.findById(5));
        //    System.out.println("user ID::::____________________________________6____id__________ " + userDaoImpl.findByLogin("third"));


        //NOTWORK   System.out.println("user NAME::::   ________________________________7_nam______________" + userDaoImpl.findByLogin("first"));
        //   System.out.println("user APARTMENT____________________8_DATA_________________::::   " + apartmentDaoSpringDataCRUD.findAll());


//        RequestUser requestUser = new RequestUser(166, null, 1, 2, 10,
//                LocalDate.of(2022, 11, 29),
//                LocalDate.of(2022, 11, 29),
//                LocalDate.of(2022, 10, 29),
//                false);
//        System.out.println("user APARTMENT _________________________11service_______ __id______" + apartmentService.findAll());
//        System.out.println("offersguest _________________________13service_______ __id______" + offerServiceable.findOffersByGuestId(1));

        //    System.out.println("user APARTMENT _________________________11_______ __id______" + apartmentDaoImplHiber.findApartmentsSuitableForRequest((RequestUser) requestUserDaoImpl.findById(83).get()));
        //  System.out.println("category price_________________________13service_______ __id______" + categoryDaoImpl.getCategoryPrice(2));

//        LOGGER.trace("***********************************************************This is TRACE");
//        LOGGER.debug("This is DEBUG");
        LOGGER.info("_______________________________________________________________This is INFO");
//        LOGGER.warn("This is WARN");
//        LOGGER.error("This is ERROR");
        //User user = new User("RYYT", "REET", "GGG", "utituyi", 55551, "112@rrr.tyy", "9998888", "TR");

    }
}
//TEST   MOCK
//////////////////////////// if (entity.isNew){entityManager.persist(entity)} else {entityManager.merge(entity)};
//    @Transactional
//    @Override
//    public User findByLogin(String login) throws DAOException {
//        return (User) getSessionFactory()
//                .getCurrentSession()
//                .createQuery("from User user where name LIKE : name")
//                .setString("name", "%" + login.trim() + "%").getSingleResult();
//        //TODO prepared statement
//       TypedQuery<User> query = entityManager.createQuery("select user  from User user where user=:id", User.class);
//        query.setParameter("id", id);
//        return query.getSingleResult())

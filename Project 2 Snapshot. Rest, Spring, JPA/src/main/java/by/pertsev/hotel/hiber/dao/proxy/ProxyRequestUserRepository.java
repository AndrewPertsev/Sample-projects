package by.pertsev.hotel.hiber.dao.proxy;

import by.pertsev.hotel.hiber.model.RequestUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProxyRequestUserRepository extends JpaRepository<RequestUser, Integer> {
    // Page<Offer> findRequestUserByUserId(int idGuest, Pageable pageable);
}

package by.pertsev.hotel.hiber.dao.proxy;

import by.pertsev.hotel.hiber.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProxyOfferRepository extends JpaRepository<Offer, Integer> {
}

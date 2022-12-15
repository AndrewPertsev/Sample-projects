package by.pertsev.hotel.hiber.dao.proxy;

import by.pertsev.hotel.hiber.model.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProxyApartmentRepository extends JpaRepository<Apartment, Integer> {
}

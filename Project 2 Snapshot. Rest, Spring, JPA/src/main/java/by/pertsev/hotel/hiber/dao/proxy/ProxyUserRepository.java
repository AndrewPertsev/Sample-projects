package by.pertsev.hotel.hiber.dao.proxy;

import by.pertsev.hotel.hiber.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProxyUserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByLogin(String login);

}

package by.pertsev.hotel.hiber.security;

import by.pertsev.hotel.hiber.dao.UserDao;
import by.pertsev.hotel.hiber.model.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class JwtUserDSImpl implements UserDetailsService {
    private final UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> loginHolder = userDao.findByLogin(login);
        if (loginHolder.isEmpty()) {
            log.info("in loadUserByUsername : NOT Found user by login");

            throw new UsernameNotFoundException("User with this login exists");
        }
        log.info("in loadUserByUsername : Found user by login");
        return new UserDetailsImpl(loginHolder.get());                       //todo exception
    }
}

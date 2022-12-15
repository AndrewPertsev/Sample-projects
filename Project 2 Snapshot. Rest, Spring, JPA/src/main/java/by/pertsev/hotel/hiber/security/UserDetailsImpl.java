package by.pertsev.hotel.hiber.security;

import by.pertsev.hotel.hiber.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

import static by.pertsev.hotel.hiber.model.Role.*;

@Slf4j
@AllArgsConstructor
@Getter
public class UserDetailsImpl implements UserDetails {

    private final User userGuest;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String userRole;

        switch (this.userGuest.getRole()) {
            case (1):
                userRole = String.valueOf(ROLE_USER);
                break;
            case (2):
                userRole = String.valueOf(ROLE_ADMIN);
                break;
            default:
                userRole = String.valueOf(ROLE_GUEST);
        }
        log.info("get Authorities for role : " + userRole);
        return Collections.singletonList(new SimpleGrantedAuthority(userRole));
    }

    @Override
    public String getPassword() {
        return this.userGuest.getPassword();
    }

    @Override
    public String getUsername() {
        return this.userGuest.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

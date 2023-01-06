package by.pertsev.hotel.hiber.security;

import by.pertsev.hotel.hiber.model.User;
import by.pertsev.hotel.hiber.util.LogMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor
@Getter
public class UserDetailsImpl implements UserDetails {

    private final User userGuest;

    @LogMethod
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String userRole = String.valueOf(userGuest.getRoleId());
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

package io.everyonecodes.projekt_attendance_manager.security;
import io.everyonecodes.projekt_attendance_manager.presitance.domain.Person;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;


public class UserPrincipal implements UserDetails {

    private final Person person;

    public UserPrincipal(Person person) {
        this.person = person;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<String> authorities = person.getAuthorities();
        return AuthorityUtils.createAuthorityList(authorities.toArray(new String[authorities.size()]));
    }

    @Override
    public String getPassword() {
        return person.getPassword();
    }

    @Override
    public String getUsername() {
        return person.getUserName();
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


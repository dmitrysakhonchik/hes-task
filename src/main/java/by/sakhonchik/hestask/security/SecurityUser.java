package by.sakhonchik.hestask.security;

import by.sakhonchik.hestask.entities.Status;
import by.sakhonchik.hestask.entities.UserAccount;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
public class SecurityUser implements UserDetails {
    private final String username;
    private final String password;
    private final List<SimpleGrantedAuthority> authority;
    private final boolean isActive;

    public SecurityUser(String username, String password, List<SimpleGrantedAuthority> authority, boolean isActive) {
        this.username = username;
        this.password = password;
        this.authority = authority;
        this.isActive = isActive;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authority;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }


    public static UserDetails fromUser(UserAccount userAccount) {
        return new User(
                userAccount.getUsername(), userAccount.getPassword(),
                userAccount.getStatus().equals(Status.ACTIVE),
                userAccount.getStatus().equals(Status.ACTIVE),
                userAccount.getStatus().equals(Status.ACTIVE),
                userAccount.getStatus().equals(Status.ACTIVE),
                userAccount.getRole().getAuthorities());
    }
}

package jxf.auth.dto;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author: Simon
 * @Data:2024/8/6
 */
@Data
public class SecurityUser implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private boolean isEnabled;
    private List<UserAuthority> authorities;


    public SecurityUser(UserDTO user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.isEnabled = user.isEnabled();

        authorities = new ArrayList<>();
        user.getRoles().forEach(t-> {
            UserAuthority a = new UserAuthority();
            a.setAuthority(t);
            authorities.add(a);
        });
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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
        return isEnabled;
    }
}

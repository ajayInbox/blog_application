package com.aj.blog.security;

import com.aj.blog.entity.AppUser;
import com.aj.blog.role.Role;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class AppUserDetails implements UserDetails {

    private String username;
    private String password;
    private boolean isEnabled;
    private List<SimpleGrantedAuthority> authorities;

    public AppUserDetails(AppUser user) {
        this.username = user.getEmail();
        this.password = user.getPassword();
        this.isEnabled = user.isEnabled();
        this.authorities = new ArrayList<>();
        Collection<Role> roles = user.getRoles();
        for(Role role:roles){
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
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

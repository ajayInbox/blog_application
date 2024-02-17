package com.aj.blog.security;

import com.aj.blog.user.ApplicationUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AppUserDetailsService implements UserDetailsService {

    private final ApplicationUserRepository applicationUserRepository;

    public AppUserDetailsService(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return applicationUserRepository.findByEmail(username)
                .map(AppUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not Found"));
    }
}

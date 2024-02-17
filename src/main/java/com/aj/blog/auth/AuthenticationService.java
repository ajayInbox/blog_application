package com.aj.blog.auth;

import com.aj.blog.entity.AppUser;
import com.aj.blog.role.Role;
import com.aj.blog.jwt.JWTService;
import com.aj.blog.repository.ApplicationUserRepository;
import com.aj.blog.role.RoleRepository;
import com.aj.blog.security.AppUserDetailsService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class AuthenticationService {

    private final ApplicationUserRepository applicationUserRepository;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final AppUserDetailsService userDetailsService;

    public AuthenticationService(ApplicationUserRepository applicationUserRepository,
                                 JWTService jwtService, PasswordEncoder passwordEncoder,
                                 AuthenticationManager authenticationManager,
                                 RoleRepository roleRepository,
                                 AppUserDetailsService userDetailsService) {
        this.applicationUserRepository = applicationUserRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
        this.userDetailsService = userDetailsService;
    }

    public String register(RegisterReq registerReq) throws Exception {
        Optional<AppUser> user = applicationUserRepository.findByEmail(registerReq.getEmail());
        if(user.isPresent()){
            throw new Exception("User Already Present");
        }
        Role role = null;
        Optional<Role> optionalRole = roleRepository.findByRoleName("USER");
        if(optionalRole.isPresent()){
            role = optionalRole.get();
        }else{
            Role newRole = new Role("USER");
            role = roleRepository.save(newRole);
        }
        var appUser = AppUser.builder()
                .handleName(registerReq.getHandleName())
                .email(registerReq.getEmail())
                .password(passwordEncoder.encode(registerReq.getPassword()))
                .isEnabled(true)
                .roles(Collections.singleton(role))
                .build();
        applicationUserRepository.save(appUser);
        return "User registered successfully.";
    }

    public AuthenticationResponse authenticate(AuthenticationReq authenticationReq) throws Exception {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationReq.getEmail(),
                authenticationReq.getPassword()
        ));
        if(authentication.isAuthenticated()){
            var user = userDetailsService.loadUserByUsername(authenticationReq.getEmail());
            System.out.println(user);
            var jwtToken = jwtService.generateToken(user);
            var response = AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .build();
            System.out.println(response);
            return response;
        }else{
            throw new Exception("Invalid credentials");
        }

    }
}

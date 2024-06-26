package com.aj.blog.user;

import com.aj.blog.auth.RegisterReq;
import com.aj.blog.exception.AppUserNotFoundException;
import com.aj.blog.exception.ArticleNotFoundException;
import com.aj.blog.response.AppUserDTO;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationUserServiceImpl implements ApplicationUserService{

    private final ApplicationUserRepository userRepository;
    Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    public ApplicationUserServiceImpl(ApplicationUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String getUserWithSecurityContext() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @Override
    public AppUser getUserWithEmail(String email) {
        AppUser user = null;
        try {
            user = userRepository.findByEmail(email).orElseThrow(() -> new AppUserNotFoundException("No Article Availabel"));
        }catch (AppUserNotFoundException exception){
            exception.printStackTrace();
        }
        return user;
    }

    @Override
    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public String registerUserAsAdmin(RegisterReq registerReq) {

        return null;
    }

    @Override
    public AppUser saveUser(AppUser user) {
        return userRepository.save(user);
    }

    @Override
    public String deleteUserByEmail(String email) {
//        AppUser user = null;
//        try {
//            user = userRepository.findByEmail(email).orElseThrow(() -> new AppUserNotFoundException("User Not Found"));
//        }catch (AppUserNotFoundException ex){
//            ex.printStackTrace();
//        }
        String returnedEmail = "";
        try {
            Optional<AppUser> ops = userRepository.findByEmail(email);
            AppUser user = null;
            if(ops.isEmpty()){
                throw new AppUserNotFoundException("User not Found");
            }
            else {
                user = ops.get();
            }
            returnedEmail = user.getEmail();
            userRepository.delete(user);
        }catch (AppUserNotFoundException ex){
            ex.printStackTrace();
        }
        return "User deleted successfully with email: "+ returnedEmail;
    }

    @Override
    public AppUser getUserWithID(Long userId) {
        AppUser user = null;
        AppUserDTO appUserDTO = null;
        try {
            Optional<AppUser> ops = userRepository.findById(userId);
            if (ops.isEmpty()){
                throw new AppUserNotFoundException("User not found");
            }
            user = ops.get();
            //appUserDTO = mapper.map(user, AppUserDTO.class);
        } catch (AppUserNotFoundException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}

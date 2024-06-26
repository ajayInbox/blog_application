package com.aj.blog.user;

import com.aj.blog.auth.RegisterReq;
import com.aj.blog.response.AppUserDTO;

import java.util.List;

public interface ApplicationUserService {

    String getUserWithSecurityContext();

    AppUser getUserWithEmail(String email);

    List<AppUser> getAllUsers();

    String registerUserAsAdmin(RegisterReq registerReq);

    AppUser saveUser(AppUser user);

    String deleteUserByEmail(String email);

    AppUser getUserWithID(Long userId);
}

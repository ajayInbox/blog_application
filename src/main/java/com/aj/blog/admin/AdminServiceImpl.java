package com.aj.blog.admin;

import com.aj.blog.role.Role;
import com.aj.blog.role.RoleRepository;
import com.aj.blog.role.RoleService;
import com.aj.blog.user.AppUser;
import com.aj.blog.user.ApplicationUserRepository;
import com.aj.blog.user.ApplicationUserService;
import org.springframework.stereotype.Service;

import java.util.Collections;
@Service
public class AdminServiceImpl implements AdminService{

    private final ApplicationUserService userService;
    private final RoleService roleService;

    public AdminServiceImpl(ApplicationUserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public AppUser assignUserToAdminRole(String email) {
        Role role = null;
        AppUser user = null;
        try{
            role = roleService.findByName("ADMIN");
            if(role == null){
                role = roleService.createRole("ADMIN");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        user = userService.getUserWithEmail(email);
        user.setRoles(Collections.singleton(role));
        AppUser savedUser = userService.saveUser(user);
        return savedUser;
    }
}

package com.aj.blog.admin;

import com.aj.blog.user.AppUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    @PostMapping("/assign")
    public ResponseEntity<AppUser> assignUserToAdminRole(String email){
        return new ResponseEntity<>(adminService.assignUserToAdminRole(email), HttpStatus.OK);
    }
}

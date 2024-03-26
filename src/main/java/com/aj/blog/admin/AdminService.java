package com.aj.blog.admin;

import com.aj.blog.user.AppUser;

public interface AdminService {
    AppUser assignUserToAdminRole(String email);
}

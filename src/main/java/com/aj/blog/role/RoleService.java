package com.aj.blog.role;

import com.aj.blog.entity.AppUser;

import java.util.List;

public interface RoleService {
    Role createRole(Role role) throws Exception;

    List<Role> getAllRoles();

    void deleteRole(Long roleId);

    Role removeAllUserFromRole(Long roleId);

    AppUser removeUserFromRole(Long userId, Long roleId) throws Exception;

    AppUser assignUerToRole(Long userId, Long roleId) throws Exception;
    Role findByName(String name);
    Role findById(Long roelId);
}

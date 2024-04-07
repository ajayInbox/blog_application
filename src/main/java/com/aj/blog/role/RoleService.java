package com.aj.blog.role;

import com.aj.blog.exception.AppUserNotFoundException;
import com.aj.blog.exception.RoleAlreadyAssignedException;
import com.aj.blog.user.AppUser;

import java.util.List;

public interface RoleService {
    Role createRole(String role) throws RoleAlreadyAssignedException;

    List<Role> getAllRoles();

    void deleteRole(Long roleId);

    Role removeAllUserFromRole(Long roleId);

    AppUser removeUserFromRole(Long userId, Long roleId) throws AppUserNotFoundException;

    AppUser assignUerToRole(Long userId, Long roleId) throws RoleAlreadyAssignedException;
    Role findByName(String name);
    Role findById(Long roelId);
}

package com.aj.blog.role;

import com.aj.blog.exception.AppUserNotFoundException;
import com.aj.blog.exception.RoleAlreadyAssignedException;
import com.aj.blog.user.AppUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.FOUND;

@RestController
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Role>> getAllRoles(){
        return new ResponseEntity<>(roleService.getAllRoles(), FOUND);
    }
    @PostMapping("/create")
    public ResponseEntity<Role> createRole(String role) throws RoleAlreadyAssignedException {
        return new ResponseEntity<>(roleService.createRole(role), CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public void createRole(@PathVariable("id") Long roleId){
        roleService.deleteRole(roleId);
    }
    @PostMapping("/remove-all-users-from-role/{id}")
    public Role removeAllUsersFromRole(@PathVariable("id") Long roleId){
        return roleService.removeAllUserFromRole(roleId);
    }
    @PostMapping("/remove-user-from-role")
    public AppUser removeUserFromRole(@RequestParam("userId")Long userId,
                                   @RequestParam("roleId") Long roleId) throws AppUserNotFoundException {
        return roleService.removeUserFromRole(userId, roleId);
    }

    @PostMapping("/assign-user-to-role")
    public AppUser assignUserToRole(@RequestParam("userId")Long userId,
                                    @RequestParam("roleId") Long roleId) throws RoleAlreadyAssignedException {
        return roleService.assignUerToRole(userId, roleId);
    }
}

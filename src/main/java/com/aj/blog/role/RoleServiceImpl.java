package com.aj.blog.role;

import com.aj.blog.user.AppUser;
import com.aj.blog.user.ApplicationUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;
    private final ApplicationUserRepository userRepository;

    public RoleServiceImpl(RoleRepository roleRepository,
                           ApplicationUserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
    @Override
    public Role createRole(Role theRole) throws Exception {
        Optional<Role> checkRole = roleRepository.findByRoleName(theRole.getRoleName());
        if (checkRole.isPresent()){
            throw new Exception(checkRole.get().getRoleName()+ " role already exist");
        }
        return roleRepository.save(theRole);
    }

    @Override
    public void deleteRole(Long roleId) {
        this.removeAllUserFromRole(roleId);
        roleRepository.deleteById(roleId);
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByRoleName(name).get();
    }
    @Override
    public Role findById(Long roelId) {
        return roleRepository.findById(roelId).get();
    }

    @Override
    public AppUser removeUserFromRole(Long userId, Long roleId) throws Exception {
        Optional<AppUser> user = userRepository.findById(userId);
        Optional<Role> role = roleRepository.findById(roleId);
        if (role.isPresent() && role.get().getUsers().contains(user.get())) {
            role.get().removeUserFromRole(user.get());
            roleRepository.save(role.get());
            return user.get();
        }
        throw new Exception("User not found!");
    }

    @Override
    public AppUser assignUerToRole(Long userId, Long roleId) throws Exception {
        Optional<AppUser> user = userRepository.findById(userId);
        Optional<Role> role = roleRepository.findById(roleId);
        if (user.isPresent() && user.get().getRoles().contains(role.get())){
            throw new Exception(
                    user.get().getHandleName()+ " is already assigned to the " + role.get().getRoleName() +" role");
        }
        role.ifPresent(theRole -> theRole.assignUserToRole(user.get()));
        roleRepository.save(role.get());
        return user.get();
    }

    @Override
    public Role removeAllUserFromRole(Long roleId) {
        Optional<Role> role = roleRepository.findById(roleId);
        role.ifPresent(Role::removeAllUsersFromRole);
        return roleRepository.save(role.get());
    }
}

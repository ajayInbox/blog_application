package com.aj.blog.role;

import com.aj.blog.user.AppUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;
    private String roleName;
    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Collection<AppUser> users = new HashSet<>();

    public Role(String role){
        this.roleName = role;
    }
    public void removeAllUsersFromRole(){
        if (this.getUsers() != null){
            List<AppUser> usersInRole = this.getUsers().stream().toList();
            usersInRole.forEach(this::removeUserFromRole);
        }
    }
    public void removeUserFromRole(AppUser user) {
        user.getRoles().remove(this);
        this.getUsers().remove(user);
    }
    public void assignUserToRole(AppUser user){
        user.getRoles().add(this);
        this.getUsers().add(user);
    }

}

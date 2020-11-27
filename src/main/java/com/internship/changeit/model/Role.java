package com.internship.changeit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum Role {
    USER(Set.of(Permission.VOTES_DELETE, Permission.PROBLEM_PROPERTIES_READ)),

    ADMIN(Set.of(Permission.COMMENTS_DELETE, Permission.USER_CRUD, Permission.VOTES_DELETE,
            Permission.PROBLEM_PROPERTIES_CRUD, Permission.PROBLEM_PROPERTIES_READ));

    private final Set<Permission> permissions;

    public Set<SimpleGrantedAuthority> getAuthorities(){
        return getPermissions().stream()
                               .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                               .collect(Collectors.toSet());
    }
}

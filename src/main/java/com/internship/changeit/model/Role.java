package com.internship.changeit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum Role {
    USER(Set.of(Permission.PROBLEMS_READ, Permission.PROBLEMS_WRITE)),
    MANAGER(Set.of(Permission.PROBLEMS_READ, Permission.PROBLEMS_WRITE)),
    ADMIN(Set.of(Permission.PROBLEMS_READ, Permission.PROBLEMS_WRITE, Permission.PROBLEMS_DELETE));

    private final Set<Permission> permissions;

    public Set<SimpleGrantedAuthority> getAuthorities(){
        return getPermissions().stream()
                               .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                               .collect(Collectors.toSet());
    }

}

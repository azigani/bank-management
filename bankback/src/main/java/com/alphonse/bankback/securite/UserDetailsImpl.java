//package com.alphonse.bankback.securite;
//
//import com.alphonse.bankback.entities.Utilisateur;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.Objects;
//
//public class UserDetailsImpl implements UserDetails {
//    private Long id;
//
//    private String username;
//
//    private String email;
//
//    @JsonIgnore
//    private String password;
//
//    private Collection<? extends GrantedAuthority> authorities;
//
//    public UserDetailsImpl(Long id, String username, String email, String password,
//                           Collection<? extends GrantedAuthority> authorities) {
//        this.id = id;
//        this.username = username;
//        this.email = email;
//        this.password = password;
//        this.authorities = authorities;
//    }
//
//    public  UserDetailsImpl (Utilisateur user) {
//        this.username = user.getLogin();
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o)
//            return true;
//        if (o == null || getClass() != o.getClass())
//            return false;
//        UserDetailsImpl user = (UserDetailsImpl) o;
//        return Objects.equals(id, user.id);
//    }
//}


package com.alphonse.bankback.securite;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.alphonse.bankback.entities.Credentials;
import com.alphonse.bankback.entities.Permission;
import com.alphonse.bankback.entities.Role;
import com.alphonse.bankback.entities.Utilisateur;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;
    private String password;
    private String username;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    List<GrantedAuthority> authorities;
//   private Role role;

    public UserDetailsImpl(Utilisateur user, Credentials credentials) {
        this.username = user.getLogin();
        this.password = credentials.getPassword();
        this.enabled = credentials.isEnabled();
        this.accountNonExpired = credentials.isAccountNonExpired();
        this.credentialsNonExpired = credentials.isCredentialsNonExpired();
        this.accountNonLocked = credentials.isAccountNonLocked();
//        this.role= user.getRole();
        GrantedAuthority authority = () -> user.getRole().getCode();
        authorities = Arrays.asList(authority);
        authorities = convertPermissionsToAutorities(user.getRole().getPermissions());

        GrantedAuthority roleAutority = () -> user.getRole().getCode();
        authorities.add(roleAutority);

    }

    private List<GrantedAuthority> convertPermissionsToAutorities(Set<Permission> permissions) {
        if(permissions!=null){
           return (List<GrantedAuthority>) permissions.stream().
                   map(permission -> {
                       GrantedAuthority authority =() -> permission.getCode();
                       return authority;
                   })
                   .collect(Collectors.toSet());
        }
        return new ArrayList<>();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

//    public Role getRole() {
//        return role;
//    }
}

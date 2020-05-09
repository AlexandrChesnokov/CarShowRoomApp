package com.alex.security.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

/**
 * @author Alexandr Chesnokov
 * @project CarShowRoomApp
 */
public class JwtUser implements UserDetails {

    private final int id;
    private final String firstname;
    private final String lastname;
    private final String phone_number;
    private final String email;
    private final int manager_id;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    public JwtUser(int id,
                   String firstname,
                   String lastname,
                   String phone_number,
                   String email, int manager_id,
                   String password,
                   Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone_number = phone_number;
        this.email = email;
        this.manager_id = manager_id;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    @JsonIgnore
    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhone_number() {
        return phone_number;
    }
    @JsonIgnore
    public String getEmail() {
        return email;
    }

    public int getManager_id() {
        return manager_id;
    }


}

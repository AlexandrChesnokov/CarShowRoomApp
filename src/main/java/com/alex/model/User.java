package com.alex.model;




import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.*;
import java.util.Objects;


@JsonIgnoreProperties
public class User {

    private int id;

    private Role role;


    @Size(min = 1, max = 16, message = "Name should be from 1 to 16 symbols")
    private String firstname;

    @Size(min = 1, max = 16, message = "Surname should be from 1 to 16 symbols")
    private String lastname;


    private String phone_number;

    @Email(message = "Invalid Email Format")
    private String email;

    private int manager_id;

    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    transient private String password2;

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public User() {
    }

    public User(int id, Role role, @Size(min = 1, max = 16, message = "Name should be from 1 to 16 symbols") String firstname, @Size(min = 1, max = 16, message = "Surname should be from 1 to 16 symbols")
            String lastname, @Min(value = 9, message = "need 9") String phone_number,
                @Email(message = "Invalid Email Format")
                        String email, int manager_id,
                @Size(min = 8, message = "Password must be at least 8 characters long") String password, String password2) {
        this.id = id;
        this.role = role;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone_number = phone_number;
        this.email = email;
        this.manager_id = manager_id;
        this.password = password;
        this.password2 = password2;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getManager_id() {
        return manager_id;
    }

    public void setManager_id(int manager_id) {
        this.manager_id = manager_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", role=" + role +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", email='" + email + '\'' +
                ", manager_id=" + manager_id +
                ", password='" + password + '\'' +
                ", password2='" + password2 + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                manager_id == user.manager_id &&
                Objects.equals(role, user.role) &&
                Objects.equals(firstname, user.firstname) &&
                Objects.equals(lastname, user.lastname) &&
                Objects.equals(phone_number, user.phone_number) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(password2, user.password2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role, firstname, lastname, phone_number, email, manager_id, password, password2);
    }
}

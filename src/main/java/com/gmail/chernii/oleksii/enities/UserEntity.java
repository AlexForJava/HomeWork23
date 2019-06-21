package com.gmail.chernii.oleksii.enities;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Set;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ElementCollection(targetClass = Role.class)
    private Set<Role> roles;
}

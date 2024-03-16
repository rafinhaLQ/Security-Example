package com.github.rafinhalq.securityexample.repository.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

@Document(collection = "users")
@Setter
@Getter
public class UserEntity {
    @Id
    private String id;

    private String firstName;

    private String lastName;

    @Indexed(unique = true)
    private String email;

    private String password;

    private List<Address> addresses;

    private String phoneNumber;

    private Set<Authority> authorities;

}

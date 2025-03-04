package com.domain.blog.entity;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role {
    @Id
    private String id;
    private String name;
    private boolean active;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "role")
    @JsonIgnore
    private List<User> users;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = { "roles" })
    @JoinTable(name = "role_permission", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private List<Permission> permissions;

}


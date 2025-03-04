package com.domain.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "permissions")
@Getter
@Setter
public class Permission {

    @Id
    private String id;

    private String name;

    private String apiPath;

    private String method;
    private boolean active;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "permissions")
    @JsonIgnore
    private List<Role> roles;
}

package com.domain.blog.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name="Categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private int position;
    private String slug;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Blog> blogs;
}

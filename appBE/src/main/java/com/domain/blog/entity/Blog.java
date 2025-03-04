package com.domain.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;


@Entity
@Getter
@Setter
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String keyword;
    private String image;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "LONGTEXT")
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="blogger_id")
    private User blogger;

    @OneToMany(mappedBy = "blog", fetch = FetchType.EAGER, cascade=CascadeType.REMOVE , orphanRemoval = true)
    @JsonIgnoreProperties(value ={"user"})
    private List<Comment> comments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category category;

    private Long createdAt;
    private Long updatedAt;

    @PrePersist
    public void handleBeforeCreate() {
        this.createdAt = Instant.now().toEpochMilli();
    }

    @PreUpdate
    public void handleBeforeUpdate() {
        this.updatedAt = Instant.now().toEpochMilli();
    }

}

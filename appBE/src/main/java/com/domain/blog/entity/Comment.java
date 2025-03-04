package com.domain.blog.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="blog_id")
    private Blog blog;

    private Long createdAt;
    private String rootId;

    @PrePersist
    public void handleBeforeCreate() {
        this.createdAt = Instant.now().toEpochMilli();
    }


}

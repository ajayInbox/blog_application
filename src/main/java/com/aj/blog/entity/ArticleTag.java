package com.aj.blog.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;
import java.util.HashSet;

@Entity
@Builder
@Data
public class ArticleTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long tagId;

    @Column(name = "tag_label")
    private String tagLabel;

    @ManyToMany(mappedBy = "tags")
    private Collection<Article> articles = new HashSet<>();

}

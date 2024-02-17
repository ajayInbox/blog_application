package com.aj.blog.article_tag;

import com.aj.blog.article.Article;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.HashSet;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long tagId;

    @Column(name = "tag_label")
    private String tagLabel;

    @JsonIgnore
    @ManyToMany(mappedBy = "tags",fetch = FetchType.LAZY)
    private Collection<Article> articles = new HashSet<>();

}

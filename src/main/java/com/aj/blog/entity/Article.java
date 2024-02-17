package com.aj.blog.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.HashSet;


@Entity
@Builder
@Data
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long articleId;

    @Column(name = "article_title")
    private String articleTitle;

    @Column(name = "article_banner_url")
    private String articleBannerUrl;

    @Column(name = "article_content")
    private String articleContent;

    @Column(name = "article_tags")
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST,
                    CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(name = "article_articleTags",
            joinColumns = @JoinColumn(name = "article_id", referencedColumnName = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "tag_id"))
    private Collection<ArticleTag> tags = new HashSet<>();

    public Article() {
    }

    public Article(Long articleId, String articleTitle, String articleBannerUrl, String articleContent, Collection<ArticleTag> tags) {
        this.articleId = articleId;
        this.articleTitle = articleTitle;
        this.articleBannerUrl = articleBannerUrl;
        this.articleContent = articleContent;
        this.tags = tags;
    }

}

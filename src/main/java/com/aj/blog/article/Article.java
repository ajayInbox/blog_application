package com.aj.blog.article;

import com.aj.blog.article_tag.ArticleTag;
import com.aj.blog.user.AppUser;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;


@Entity
@Builder
@Data
@AllArgsConstructor
@Table(name = "articles")
public class Article implements Serializable {
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


    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "read_time")
    private String readTime;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    @JsonSerialize(using = CustomUserSerializer.class)
    private AppUser user;

    public Article() {
    }


}

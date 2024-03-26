package com.aj.blog.article;

import com.aj.blog.article_tag.ArticleTag;
import com.aj.blog.user.AppUser;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;


@Entity
@Builder
@Data
@AllArgsConstructor
@Table(name = "articles")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "articleId")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private AppUser user;

    public Article() {
    }


}

package com.aj.blog.article;

import com.aj.blog.response.ArticleResultForLatestContainerDTO;
import com.aj.blog.response.ArticleWithoutUserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Query("SELECT NEW com.aj.blog.response.ArticleResultForLatestContainerDTO(a.articleId, a.articleTitle, a.readTime, a.createdAt) FROM Article a WHERE a.articleId = :articleId")
    public ArticleResultForLatestContainerDTO getArticleForLatestContainer( @Param("articleId") Long articleId);

    @Query("SELECT NEW com.aj.blog.response.ArticleWithoutUserDTO(a.articleId, a.articleTitle, a.articleBannerUrl, a.articleContent, a.createdAt, a.tags, a.readTime) FROM Article a")
    public List<ArticleWithoutUserDTO> findArticlesWithoutUser();

    @Query("SELECT a FROM Article a JOIN a.tags t WHERE t.tagId = :tagId")
    public List<Article> findAllArticlesByTagId(@Param("tagId") Long tagId);
}

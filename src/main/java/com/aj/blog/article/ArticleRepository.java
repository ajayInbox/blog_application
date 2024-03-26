package com.aj.blog.article;

import com.aj.blog.response.ArticleResultForLatestContainerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Query("SELECT NEW com.aj.blog.response.ArticleResultForLatestContainerDTO(a.articleId, a.articleTitle, a.readTime, a.createdAt) FROM Article a WHERE a.articleId = :articleId")
   public ArticleResultForLatestContainerDTO getArticleForLatestContainer( @Param("articleId") Long articleId);
}

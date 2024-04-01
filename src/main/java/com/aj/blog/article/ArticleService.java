package com.aj.blog.article;

import com.aj.blog.dto.ArticleDTO;
import com.aj.blog.article.Article;
import com.aj.blog.response.ArticleResultForLatestContainerDTO;
import com.aj.blog.response.ArticleWithoutUserDTO;

import java.util.List;

public interface ArticleService {
    List<Article> getAllArticles() throws Exception;

    void addArticle(ArticleDTO articleDTO);

    Article getIndividualArticle(Long postId) throws Exception;

    List<ArticleWithoutUserDTO> getAllArticlesWithoutUser();

    ArticleResultForLatestContainerDTO getArticleForLatestContainer(Long articleId);

    List<Article> getAllArticlesWithTagId(Long tagId);
}

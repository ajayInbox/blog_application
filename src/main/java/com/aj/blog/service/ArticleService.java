package com.aj.blog.service;

import com.aj.blog.dto.ArticleDTO;
import com.aj.blog.entity.Article;

import java.util.List;

public interface ArticleService {
    List<Article> getAllArticles() throws Exception;

    void addArticle(ArticleDTO articleDTO);

    Article getIndividualArticle(Long postId) throws Exception;
}

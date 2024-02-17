package com.aj.blog.article;

import com.aj.blog.dto.ArticleDTO;
import com.aj.blog.article.Article;
import com.aj.blog.article.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ArticleController {

    @Autowired
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Article>> getAllArticles() throws Exception {
        List<Article> articles = articleService.getAllArticles();
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @PostMapping(value = "/post")
    public ResponseEntity<String> addArticle(@RequestBody ArticleDTO articleDTO){
        articleService.addArticle(articleDTO);
        return new ResponseEntity<>("post added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/post/{articleId}")
    public ResponseEntity<Article> getIndividualArticle(@PathVariable Long articleId) throws Exception {
        Article article = articleService.getIndividualArticle(articleId);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

}

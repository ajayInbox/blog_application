package com.aj.blog.article;

import com.aj.blog.dto.ArticleDTO;
import com.aj.blog.response.ArticleResultForLatestContainerDTO;
import com.aj.blog.response.ArticleWithoutUserDTO;
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

    @GetMapping("/posts-with-user")
    public ResponseEntity<List<Article>> getAllArticlesWithUser() throws Exception {
        List<Article> articles = articleService.getAllArticles();
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @GetMapping("/posts-without-user")
    public ResponseEntity<List<ArticleWithoutUserDTO>> getAllArticlesWithoutUser() {
        return new ResponseEntity<>(articleService.getAllArticlesWithoutUser(), HttpStatus.OK);
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

    @GetMapping("/post/for-latest")
    public ResponseEntity<ArticleResultForLatestContainerDTO> getArticleForLatest(@RequestParam Long articleId){
        return new ResponseEntity<>(articleService.getArticleForLatestContainer(articleId), HttpStatus.OK);
    }

    @GetMapping("/posts/with-tag/{tagId}")
    public ResponseEntity<List<Article>> getAllArticlesWithTagId(@PathVariable Long tagId){
        return new ResponseEntity<>(articleService.getAllArticlesWithTagId(tagId), HttpStatus.OK);
    }

}

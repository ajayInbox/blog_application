package com.aj.blog.controller;

import com.aj.blog.entity.ArticleTag;
import com.aj.blog.service.ArticleTagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tags")
public class ArticleTagController {

    private final ArticleTagService tagService;

    public ArticleTagController(ArticleTagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ArticleTag>> getAllTags(){
        return new ResponseEntity<>(tagService.getAllTags(), HttpStatus.OK);
    }

    public ResponseEntity<ArticleTag> createTag(String tagLabel){
        return new ResponseEntity<>(tagService.createTag(tagLabel), HttpStatus.CREATED);
    }

    public ResponseEntity<String> deleteTag(String tagLabel) throws Exception {
        return new ResponseEntity<>(tagService.deleteTag(tagLabel), HttpStatus.OK);
    }
}

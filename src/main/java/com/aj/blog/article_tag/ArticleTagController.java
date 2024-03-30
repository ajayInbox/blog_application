package com.aj.blog.article_tag;

import com.aj.blog.article_tag.ArticleTag;
import com.aj.blog.article_tag.ArticleTagService;
import com.aj.blog.response.TagsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ArticleTagController {

    Logger logger = LoggerFactory.getLogger(ArticleTagController.class);

    private final ArticleTagService tagService;

    public ArticleTagController(ArticleTagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/tags/all")
    public ResponseEntity<List<ArticleTag>> getAllTags(){
        System.out.println("in controller method");
        return new ResponseEntity<>(tagService.getAllTags(), HttpStatus.OK);
    }

    @GetMapping("/tags/all/v2")
    public ResponseEntity<List<TagsDTO>> getAllTagsV2(){
        return new ResponseEntity<>(tagService.getAllTagsV2(), HttpStatus.OK);
    }

    @GetMapping("/tag")
    public ResponseEntity<ArticleTag> getTagAssociatedArticlesByTagLabel(@RequestParam String tagLabel) throws Exception {
        return new ResponseEntity<>(tagService.getTagByTagLabel(tagLabel), HttpStatus.OK);
    }

    @GetMapping("/tag/{tagId}")
    public ResponseEntity<ArticleTag> getTagAssociatedArticlesByTagId(@PathVariable Long tagId){
        return new ResponseEntity<>(tagService.getTagAssociatedArticlesByTagId(tagId), HttpStatus.OK);
    }

    @PostMapping("/tag/create")
    public ResponseEntity<ArticleTag> createTag(@RequestParam String tagLabel){
        return new ResponseEntity<>(tagService.createTag(tagLabel), HttpStatus.CREATED);
    }

    @DeleteMapping("/tag/delete")
    public ResponseEntity<String> deleteTag(@RequestParam String tagLabel) throws Exception {
        return new ResponseEntity<>(tagService.deleteTag(tagLabel), HttpStatus.OK);
    }
}

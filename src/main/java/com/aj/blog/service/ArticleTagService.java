package com.aj.blog.service;

import com.aj.blog.entity.ArticleTag;

import java.util.List;

public interface ArticleTagService {
    List<ArticleTag> getAllTags();

    ArticleTag createTag(String tagLabel);

    String deleteTag(String tagLabel) throws Exception;
}

package com.aj.blog.article_tag;

import com.aj.blog.article_tag.ArticleTag;

import java.util.List;

public interface ArticleTagService {
    List<ArticleTag> getAllTags();

    ArticleTag createTag(String tagLabel);

    String deleteTag(String tagLabel) throws Exception;
}

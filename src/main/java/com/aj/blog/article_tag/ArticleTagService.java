package com.aj.blog.article_tag;

import com.aj.blog.article_tag.ArticleTag;
import com.aj.blog.response.TagsDTO;

import java.util.List;

public interface ArticleTagService {
    List<ArticleTag> getAllTags();

    ArticleTag createTag(String tagLabel);

    String deleteTag(String tagLabel) throws Exception;

    ArticleTag getTagByTagLabel(String tagLabel) throws Exception;

    List<TagsDTO> getAllTagsV2();

    ArticleTag getTagAssociatedArticlesByTagId(Long tagId);
}

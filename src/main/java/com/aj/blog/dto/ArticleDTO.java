package com.aj.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ArticleDTO {

    private String articleBannerUrl;
    private String articleTitle;
    private String articleContent;
    private String articleTags;
}

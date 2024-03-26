package com.aj.blog.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Collection;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleWithoutUserDTO {

    private Long articleId;
    private String articleTitle;
    private String articleBannerUrl;
    private String articleContent;
    private LocalDate createdAt;
    private Collection<TagsDTO> tags;

}

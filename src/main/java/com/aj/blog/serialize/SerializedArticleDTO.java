package com.aj.blog.serialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data@Builder@AllArgsConstructor@NoArgsConstructor
public class SerializedArticleDTO {

    private Long articleId;
    private String articleBannerUrl;
    private String articleTitle;
    private String articleContent;
    private LocalDate createdAt;
    private String readTime;
}

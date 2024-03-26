package com.aj.blog.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleResultForLatestContainerDTO {

    private Long articleId;
    private String articleTitle;
    private String readTime;
    private LocalDate createdAt;
}

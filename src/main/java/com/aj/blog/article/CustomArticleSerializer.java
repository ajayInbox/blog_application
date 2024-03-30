package com.aj.blog.article;

import com.aj.blog.serialize.SerializedArticleDTO;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomArticleSerializer extends JsonSerializer<Collection<Article>> {

    @Override
    public void serialize(Collection<Article> articles, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        List<SerializedArticleDTO> obj = new ArrayList<>();
        for(Article article: articles){
            SerializedArticleDTO newObj = SerializedArticleDTO.builder()
                    .articleBannerUrl(article.getArticleBannerUrl())
                    .articleId(article.getArticleId())
                    .articleTitle(article.getArticleTitle())
                    .articleContent(article.getArticleContent())
                    .createdAt(article.getCreatedAt())
                    .readTime(article.getReadTime())
                    .build();
            obj.add(newObj);
        }
        jsonGenerator.writeObject(obj);
    }
}

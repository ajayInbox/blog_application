package com.aj.blog.article;

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
        List<Long> ids = new ArrayList<>();
        for(Article article: articles){
            ids.add(article.getArticleId());
        }
        jsonGenerator.writeObject(ids);
    }
}

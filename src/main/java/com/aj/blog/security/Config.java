package com.aj.blog.security;

import com.aj.blog.article.Article;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;

@Configuration
public class Config {

//    @Bean
//    public Mapper dozerBeanMapper(){
//        return new Mapper();
//    }

//    @Bean
//    public ObjectMapper objectMapper() {
//        ObjectMapper mapper = new ObjectMapper();
//        SimpleModule module = new SimpleModule();
//        module.addSerializer(Article.class, new CustomUserSerializer());
//        mapper.registerModule(module);
//        return mapper;
//    }
}

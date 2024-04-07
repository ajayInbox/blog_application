package com.aj.blog.article;

import com.aj.blog.user.AppUser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Collection;

public class CustomUserSerializer extends JsonSerializer<AppUser>{
    @Override
    public void serialize(AppUser user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeObject(user.getUserId());
    }
}

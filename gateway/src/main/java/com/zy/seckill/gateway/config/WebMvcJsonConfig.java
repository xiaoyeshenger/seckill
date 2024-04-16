package com.zy.seckill.gateway.config;

import com.google.gson.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import springfox.documentation.spring.web.json.Json;

import java.lang.reflect.Type;

@Configuration
public class WebMvcJsonConfig {
    @Bean
    GsonHttpMessageConverter gsonHttpMessageConverter() {
        GsonHttpMessageConverter converter = new GsonHttpMessageConverter();
        converter.setGson(
                new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls()
                        .registerTypeAdapter(Json.class, new WebMvcJsonConfig.SpringfoxJsonToGsonAdapter())
                        .create());
        return converter;
    }

    private static class SpringfoxJsonToGsonAdapter implements JsonSerializer<Json> {
        @Override
        public JsonElement serialize(Json json, Type type, JsonSerializationContext context) {
            final JsonParser parser = new JsonParser();
            return parser.parse(json.value());
        }
    }
}
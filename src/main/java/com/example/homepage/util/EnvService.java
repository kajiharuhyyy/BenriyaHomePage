package com.example.homepage.util;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Component;

@Component
public class EnvService {
    private final Dotenv dotenv = Dotenv.configure()
            .ignoreIfMissing() // .envが無くてもエラーにしない
            .load();

    public String get(String key) {
        return dotenv.get(key);
    }
}

package com.ruifu.pls.resourceoauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@SpringBootApplication
@EnableResourceServer
@RestController
public class ResourceOauthApplication {

    @RequestMapping("/")
    public Message home() {
        return new Message("Hello oauth2 World");
    }

    public static void main(String[] args) {
        SpringApplication.run(ResourceOauthApplication.class, args);
    }


    class Message {
        private String id = UUID.randomUUID().toString();
        private String content;

        Message() {
        }

        public Message(String content) {
            this.content = content;
        }

        public String getId() {
            return id;
        }

        public String getContent() {
            return content;
        }
    }
}

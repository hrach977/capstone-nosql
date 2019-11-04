package com.nosql.client.config;

import com.nosql.client.Client;
import messages.proto.Messages;
import messages.proto.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class AppConfig {

    @Bean
    Client client() throws IOException {
        return new Client(host(), port());
    }

    @Bean
    String host() {
        return "localhost";
    }

    @Bean
    int port() {
        return 9080;
    }

    @Bean
    Test.Builder builder() {
        return Test.newBuilder();
    }
}

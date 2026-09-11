package com.app.auction.config;

import static org.zalando.logbook.core.HeaderFilters.authorization;
import static org.zalando.logbook.core.HeaderFilters.removeHeaders;
import static org.zalando.logbook.json.JsonPathBodyFilters.jsonPath;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.Logbook;
import org.zalando.logbook.core.DefaultHttpLogWriter;
import org.zalando.logbook.core.DefaultSink;
import org.zalando.logbook.json.JsonHttpLogFormatter;

@Configuration
public class LogbookConfig {

    @Bean
    public Logbook logbook() {
        return Logbook.builder()
                .headerFilter(removeHeaders("Connection", "accept", "accept-encoding", "cache-control", "host",
                        "postman-token", "content-length", "Keep-Alive", "Transfer-Encoding", "Vary", "Date"))
                .headerFilter(authorization())
                // .bodyFilter(JsonBodyFilters.replaceJsonStringProperty(Set.of("password"), "***"))
                .bodyFilter(jsonPath("$.password").replace("***"))
                .bodyFilter(jsonPath("$.token").replace("***"))
                .sink(new DefaultSink(
                        new CustomHttpLogFormatter(new JsonHttpLogFormatter()),
                        new DefaultHttpLogWriter()))
                .build();

    }
    
}

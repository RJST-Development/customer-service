package de.rjst.cs.datasource;

import org.springframework.boot.web.server.servlet.context.ServletWebServerApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.web.servlet.client.RestTestClient;

@Configuration
public class RestTestClientConfig {

    @Bean
    public RestTestClient restTestClient(final ServletWebServerApplicationContext context) {
        final var webServer = context.getWebServer();
        final var port = webServer.getPort();
        return RestTestClient.bindToServer()
                             .baseUrl("http://localhost:" + port)
                             .build();
    }

}

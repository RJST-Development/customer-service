package de.rjst.cs.api;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class OpenApiConfig {

    private final BuildProperties buildProperties;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(buildProperties.getName() + " OpenAPI")
                        .version(buildProperties.getVersion())
                        .description("Provides customer data and operations.")
                );
    }
}

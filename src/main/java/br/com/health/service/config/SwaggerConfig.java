package br.com.health.service.config;

import io.swagger.models.auth.In;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;

import java.util.List;

@ComponentScan(basePackages = {
        "br.com.health.controller.AuthController",
        "br.com.health.controller.UserController",
        "br.com.health.controller.BeneficiaryController",
        "br.com.health.controller.DocumentController"
})
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getAPIInfo())
                .securityContexts(List.of(securityContext()))
                .securitySchemes(List.of(new ApiKey("Bearer", HttpHeaders.AUTHORIZATION, In.HEADER.name())))
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.health.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return List.of(new SecurityReference("Bearer", authorizationScopes));
    }

    private ApiInfo getAPIInfo() {
        return new ApiInfoBuilder()
                .title("HEALTH")
                .description("Test API for registering a health plan beneficiary")
                .version("0.0.1")
                .contact(new Contact("George Piter", null,"george.piter@terra.com.br"))
                .build();
    }

    @Bean
    UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
                .defaultModelsExpandDepth(-1)
                .defaultModelExpandDepth(-1)
                .build();
    }

}

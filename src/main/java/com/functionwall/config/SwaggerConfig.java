package com.functionwall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docketTest(Environment environment) {

        Profiles profiles = Profiles.of("dev");
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(flag)
                .groupName("Test")//分组
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.functionwall.controller")).build();
    }

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(true)
                .groupName("Function Wall")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.functionwall.controller")).build();

    }

    public ApiInfo apiInfo() {
        Contact contact = new Contact("YT", "http://localhost:8080", "soft_yt@163.com");
        return new ApiInfoBuilder()
                .title("Swagger接口文档")
                .description("Function Wall")
                .version("version 1.0.0")
                .contact(contact)
                .build();
    }
}
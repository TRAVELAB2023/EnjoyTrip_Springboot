package com.enjoytrip.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;
import java.util.Set;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
    private String version="V1";
    private String title="SSAFY EnjoyTrip API "+version;

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2).consumes(getConsumeContentTypes()).produces(getProduceContentTypes())
                .apiInfo(apiInfo()).groupName(version).select()
                .apis(RequestHandlerSelectors.basePackage("com.enjoytrip"))
                .paths(regex("/.*")).build()
                .useDefaultResponseMessages(false);
    }
    private Set<String> getConsumeContentTypes(){
        Set<String> consumes=new HashSet<>();
        consumes.add("application/json;charset=UTF-8");
        return consumes;
    }
    private Set<String> getProduceContentTypes(){
        Set<String> produces=new HashSet<>();
        produces.add("application/json;charset=UTF-8");
        return produces;
    }
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder().title(title)
                .description("enjoytrip API")
                .contact(new Contact("SSAFY","http://edu.ssafy.com","ssafy@ssafy.com"))
                .license("SSAFY license")
                .licenseUrl("https://www.ssafy.com/ksp/jsp/swp/etc/swpPrivacy.jsp")
                .build();
    }
}

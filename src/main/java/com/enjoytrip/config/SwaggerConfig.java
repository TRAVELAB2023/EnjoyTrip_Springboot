package com.enjoytrip.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
    private String version="V1";
    private String title="SSAFY EnjoyTrip API "+version;
    private static final String REFERENCE = "Authorization 헤더 값";

    @Bean
    public Docket api(){
        ArrayList<SecurityScheme> apiKeyArrayList = new ArrayList<>();
        apiKeyArrayList.add(securityScheme());
        ArrayList<SecurityContext> securityContextArrayList = new ArrayList<>();
        securityContextArrayList.add(securityContext());



        return new Docket(DocumentationType.SWAGGER_2).consumes(getConsumeContentTypes()).produces(getProduceContentTypes())
                .apiInfo(apiInfo()).groupName(version)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.enjoytrip"))
                .paths(regex("/.*")).build()
                .securityContexts(securityContextArrayList)
                .securitySchemes(apiKeyArrayList)
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
    private SecurityContext securityContext() {
        return springfox.documentation
                .spi.service.contexts
                .SecurityContext
                .builder()
                .securityReferences(defaultAuth())
                .operationSelector(operationContext -> true)
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = new AuthorizationScope("global", "accessEverything");
        ArrayList<SecurityReference> list = new ArrayList<>();
        list.add(new SecurityReference(REFERENCE, authorizationScopes));
        return list;
    }

    private ApiKey securityScheme() {
        String targetHeader = "auth-token"; // 어떠한 헤더에 값을 대입할 것인가: Authorization 헤더
        return new ApiKey(REFERENCE, targetHeader, "header");
    }

}

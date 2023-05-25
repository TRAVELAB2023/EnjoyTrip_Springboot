package com.enjoytrip.config;

import com.enjoytrip.common.CustomArgumentResolver;
import com.enjoytrip.interceptor.JwtInterceptor;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {


    private final JwtInterceptor jwtInterceptor;

    private final CustomArgumentResolver customArgumentResolver;

    @Value("${cloud.aws.s3.baseurl}")
    private String S3baseUrl;

    public WebConfig(JwtInterceptor jwtInterceptor, CustomArgumentResolver customArgumentResolver) {
        this.jwtInterceptor = jwtInterceptor;
        this.customArgumentResolver = customArgumentResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(customArgumentResolver);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations(S3baseUrl);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080")
                .allowedMethods(HttpMethod.PATCH.name(), HttpMethod.OPTIONS.name(), HttpMethod.HEAD.name(), HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.DELETE.name(), HttpMethod.PUT.name());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/share/plan/*",
                        "/chat",
                        "/auth/**"
                        , "/swagger-ui/**"
                        , "/swagger-resources/**"
                        , "/v2/api-docs"
                );
    }

    @Bean
    public RestTemplate restTemplate(){
        HttpClient client=HttpClients.custom()
                .setMaxConnTotal(50)
                .setMaxConnPerRoute(30)
                .build();
        HttpComponentsClientHttpRequestFactory factory=new HttpComponentsClientHttpRequestFactory(client);
        factory.setConnectTimeout(3*60*1000);
        factory.setReadTimeout(3*60*1000);
        return new RestTemplate(factory);
    }


}

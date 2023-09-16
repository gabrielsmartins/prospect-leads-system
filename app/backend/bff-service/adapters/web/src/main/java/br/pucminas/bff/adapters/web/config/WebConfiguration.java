package br.pucminas.bff.adapters.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.ReactivePageableHandlerMethodArgumentResolver;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer;

@Configuration
@EnableWebFlux
public class WebConfiguration implements WebFluxConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .maxAge(0);
    }

    @Override
    public void configureArgumentResolvers(ArgumentResolverConfigurer configurer) {
        var resolver = new ReactivePageableHandlerMethodArgumentResolver();
        resolver.setPageParameterName("page");
        resolver.setSizeParameterName("page_size");
        resolver.setMaxPageSize(30);
        configurer.addCustomResolver(resolver);
    }

}

package com.javatechie.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class CorsConfig
        extends CorsConfiguration
{
    @Bean
    public CorsWebFilter corsFilter()
    {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials( true );
        config.setAllowedOrigins( List.of( "http://localhost:3000",
                "http://ec2-15-237-179-131.eu-west-3.compute.amazonaws.com:3000",
                "http://ec2-15-236-239-30.eu-west-3.compute.amazonaws.com:3000",
                "http://a5b394e7adf534ead841f51a8558766a-802380742.eu-west-3.elb.amazonaws.com",
                "http://www.ecomsi.store") );
        config.setAllowedMethods( List.of( "GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD" ) );
        config.setAllowedHeaders( List.of( "origin", "content-type", "accept", "authorization", "cookie" ) );

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration( "/**", config );

        return new CorsWebFilter( source );
    }
}
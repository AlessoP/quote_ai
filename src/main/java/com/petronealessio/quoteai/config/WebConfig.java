package com.petronealessio.quoteai.config;

import com.petronealessio.quoteai.util.StringToQuoteTopicConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * The WebConfig class is a configuration class for Spring MVC.
 * It implements the WebMvcConfigurer interface to customize the Spring MVC configuration.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Overrides the addFormatters method of WebMvcConfigurer to register custom converters.
     *
     * @param registry The FormatterRegistry used to register custom converters.
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        // Add the StringToQuoteTopicConverter to the FormatterRegistry
        registry.addConverter(new StringToQuoteTopicConverter());
    }

    /**
     * Configures CORS rules for the web application.
     * This method is called during application context configuration
     * to specify CORS rules for request mappings.
     *
     * @param registry The CORS registry used to configure CORS rules
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Configure CORS mappings for all requests
        registry.addMapping("/**")
                // Allow access from localhost on port 3000 and from https://petronealessio.com
                .allowedOrigins("http://localhost:3000", "https://petronealessio.com")
                // Allow HTTP methods GET, POST, PUT, and DELETE
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                // Allow headers Authorization and Content-Type in requests
                .allowedHeaders("Authorization", "Content-Type")
                // Enable sending credentials (such as cookies) in CORS requests
                .allowCredentials(true)
                // Set the maximum age (in seconds) for which the preflight request can be cached
                .maxAge(3600);
    }
}

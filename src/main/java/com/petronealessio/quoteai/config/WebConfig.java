package com.petronealessio.quoteai.config;

import com.petronealessio.quoteai.util.StringToQuoteTopicConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
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
}

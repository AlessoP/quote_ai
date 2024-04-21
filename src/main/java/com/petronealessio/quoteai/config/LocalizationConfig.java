package com.petronealessio.quoteai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

/**
 * Resolves the locale using an “Accept-Language” HTTP header retrieved from an HTTP request.
 * If into HTTP header there isn't the key “Accept-Language”, the locale is ITALIAN
 */
@Configuration
public class LocalizationConfig {

    /**
     * Configure locale resolver
     *
     * @return LocalResolver
     */
    @Bean
    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver lr = new AcceptHeaderLocaleResolver();
        //Set default locale for this locale resolver to return in the case no Accept-Language is found
        lr.setDefaultLocale(Locale.ENGLISH);
        return lr;
    }

}

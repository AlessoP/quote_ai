package com.petronealessio.quoteai.util;

import com.petronealessio.quoteai.model.QuoteTopic;
import org.springframework.core.convert.converter.Converter;

/**
 * The StringToQuoteTopicConverter class converts a string representation to a QuoteTopic enum value.
 * It implements the Converter interface to provide conversion functionality.
 */
public class StringToQuoteTopicConverter implements Converter<String, QuoteTopic> {

    /**
     * Converts the given string representation to a QuoteTopic enum value.
     *
     * @param source The string representation of the QuoteTopic.
     * @return The corresponding QuoteTopic enum value.
     */
    @Override
    public QuoteTopic convert(String source) {
        try {
            // Convert the string to uppercase, retrieve the corresponding QuoteTopic value and return
            return QuoteTopic.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            // If the string does not match any QuoteTopic, return the default value
            return QuoteTopic.DEFAULT;
        }
    }
}

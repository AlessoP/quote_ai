package com.petronealessio.quoteai.service;

import com.petronealessio.quoteai.model.Quote;
import com.petronealessio.quoteai.model.QuoteTopic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class QuoteGeneratorServiceIntegrationTest {

    @Autowired
    private QuoteGeneratorService quoteGeneratorService;

    @Test
    void generate_Success() {
        // Arrange
        QuoteTopic topic = QuoteTopic.DEFAULT;
        Locale locale = Locale.ITALIAN;

        // Act
        Quote result = quoteGeneratorService.generate(topic, locale);

        // Assert
        assertNotNull(result);

        System.out.println(result);
    }
}

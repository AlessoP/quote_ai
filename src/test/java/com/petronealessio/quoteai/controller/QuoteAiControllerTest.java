package com.petronealessio.quoteai.controller;

import com.petronealessio.quoteai.model.Quote;
import com.petronealessio.quoteai.model.QuoteTopic;
import com.petronealessio.quoteai.service.QuoteDatabaseManager;
import com.petronealessio.quoteai.service.QuoteGeneratorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuoteAiControllerTest {

    @Mock
    private QuoteGeneratorService quoteGeneratorService;

    @Mock
    private QuoteDatabaseManager quoteDatabaseManager;

    @InjectMocks
    private QuoteAiController quoteAiController;

    @Test
    void generateQuote_Success() {
        // Arrange
        QuoteTopic topic = QuoteTopic.DEFAULT;
        Locale locale = Locale.ENGLISH;

        Quote expectedQuote = new Quote();
        expectedQuote.setCreatedDate(Timestamp.from(Instant.now()));
        expectedQuote.setTopic(topic);
        expectedQuote.setLanguageIsoCode(locale.getISO3Language());
        expectedQuote.setSentence("Test");

        when(quoteGeneratorService.generate(topic,locale)).thenReturn(expectedQuote);
        when(quoteDatabaseManager.saveQuote(expectedQuote)).thenReturn(expectedQuote);

        // Act
        ResponseEntity<Object> response = quoteAiController.generateQuote(topic, locale);

        // Assert
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        System.out.println(response.getBody());
    }
}
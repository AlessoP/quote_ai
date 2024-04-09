package com.petronealessio.quoteai.controller;

import com.petronealessio.quoteai.model.GeneratedQuoteResponse;
import com.petronealessio.quoteai.model.QuoteTopic;
import com.petronealessio.quoteai.service.PromptBuilder;
import com.petronealessio.quoteai.service.QuoteGeneratorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuoteAiControllerTest {

    @Mock
    private QuoteGeneratorService quoteGeneratorService;

    @InjectMocks
    private QuoteAiController quoteAiController;

    @Test
    void generateQuote_Success() {
        // Arrange
        QuoteTopic topic = QuoteTopic.DEFAULT;
        Locale locale = Locale.US;
        Prompt prompt = new PromptBuilder().build(topic, locale);
        String expectedQuote = "Test quote";
        when(quoteGeneratorService.generate(prompt)).thenReturn(expectedQuote);

        // Act
        ResponseEntity<Object> response = quoteAiController.generateQuote(topic, locale);

        // Assert
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedQuote, ((GeneratedQuoteResponse) response.getBody()).getQuote());
    }
}
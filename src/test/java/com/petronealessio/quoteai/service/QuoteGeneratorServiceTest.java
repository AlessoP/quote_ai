package com.petronealessio.quoteai.service;

import com.petronealessio.quoteai.model.QuoteTopic;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuoteGeneratorServiceTest {

    @Mock
    private ChatClient chatClient;

    @InjectMocks
    private QuoteGeneratorService quoteGeneratorService;

    @Test
    void generate_ExceptionThrown() {
        // Arrange
        QuoteTopic topic = QuoteTopic.DEFAULT;
        Locale locale = Locale.ITALIAN;
        Prompt prompt = new Prompt("Test prompt");
        when(chatClient.call(prompt)).thenThrow(new RuntimeException("Test exception"));

        // Act & Assert
        assertThrows(Exception.class, () -> quoteGeneratorService.generate(topic, locale));
    }
}
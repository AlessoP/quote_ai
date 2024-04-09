package com.petronealessio.quoteai.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.Generation;
import org.springframework.ai.chat.prompt.Prompt;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuoteGeneratorServiceTest {

    @Mock
    private ChatClient chatClient;

    @InjectMocks
    private QuoteGeneratorService quoteGeneratorService;

    @Test
    void generate_Success() {
        // Arrange
        Prompt prompt = new Prompt("Test prompt");
        String expectedResult = "Test quote";
        when(chatClient.call(prompt)).thenReturn(new ChatResponse(List.of(new Generation(expectedResult))));

        // Act
        String result = quoteGeneratorService.generate(prompt);

        // Assert
        assertEquals("\"Test quote\"", result);

        System.out.println(result);
    }

    @Test
    void generate_ExceptionThrown() {
        // Arrange
        Prompt prompt = new Prompt("Test prompt");
        when(chatClient.call(prompt)).thenThrow(new RuntimeException("Test exception"));

        // Act & Assert
        assertThrows(Exception.class, () -> {
            quoteGeneratorService.generate(prompt);
        });
    }
}
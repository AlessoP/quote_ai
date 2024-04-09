package com.petronealessio.quoteai.controller;

import ch.qos.logback.classic.Logger;
import com.petronealessio.quoteai.model.GeneratedQuoteResponse;
import com.petronealessio.quoteai.model.QuoteTopic;
import com.petronealessio.quoteai.service.PromptBuilder;
import com.petronealessio.quoteai.service.QuoteGeneratorService;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

/**
 * The QuoteAiController class handles requests related to generating quotes.
 */
@RestController
public class QuoteAiController {

    /**
     * Logger for logging messages.
     */
    private static final Logger logger = (Logger) LoggerFactory.getLogger(QuoteAiController.class);

    /**
     * Service for generating quotes.
     */
    @Autowired
    private QuoteGeneratorService quoteGeneratorService;

    /**
     * Handles the GET request to generate a quote based on the specified topic.
     *
     * @param topic  The topic of the quote.
     * @param locale The locale used for generating the quote.
     * @return ResponseEntity containing the generated quote.
     */
    @GetMapping(value = "v1/generate")
    public ResponseEntity<Object> generateQuote(final @RequestParam(value = "topic", defaultValue = "default") QuoteTopic topic,
                                                final Locale locale){

        // Constructs the prompt based on the topic and locale
        Prompt prompt = PromptBuilder.build(topic, locale);
        logger.info("Prompt constructed");

        // Generates the quote
        String quote = quoteGeneratorService.generate(prompt);
        logger.info("Quote generated");

        // Returns the generated quote as ResponseEntity
        return ResponseEntity.ok(new GeneratedQuoteResponse(quote));
    }
}

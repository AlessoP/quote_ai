package com.petronealessio.quoteai.controller;

import com.petronealessio.quoteai.model.Quote;
import com.petronealessio.quoteai.model.QuoteTopic;
import com.petronealessio.quoteai.service.QuoteDatabaseManager;
import com.petronealessio.quoteai.service.QuoteGeneratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(QuoteAiController.class);

    @Autowired
    private QuoteGeneratorService quoteGeneratorService;

    @Autowired
    private QuoteDatabaseManager quoteDatabaseManager;

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

        // Generates the quote
        Quote quote = quoteGeneratorService.generate(topic,locale);
        logger.info("Quote generated successfully");

        // Saves the quote on database
        Quote savedQuote = quoteDatabaseManager.saveQuote(quote);

        // Returns generated quote
        if (savedQuote != null){
            logger.info("Quote saved with id " + savedQuote.getId());

            // Returns the saved quote as ResponseEntity
            return ResponseEntity.ok(savedQuote);
        } else {
            logger.warn("The quote could not be saved. The generated quote is provided without an id");

            // Returns the generated quote as ResponseEntity
            return ResponseEntity.ok(quote);
        }
    }
}

package com.petronealessio.quoteai.service;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * The QuoteGeneratorService class is responsible for generating quotes based on prompts using an AI model.
 * It utilizes a ChatClient to interact with the AI model and retrieve the generated quotes.
 */
@Service
public class QuoteGeneratorService {

    /**
     * The logger instance for logging messages.
     */
    private static final Logger logger = (Logger) LoggerFactory.getLogger(QuoteGeneratorService.class);

    /**
     * The ChatClient instance used to communicate with the AI model.
     */
    @Autowired
    private final ChatClient chatClient;

    /**
     * Constructs a new instance of QuoteGeneratorService with the specified ChatClient.
     *
     * @param chatClient The ChatClient instance to be used for communication with the AI model.
     */
    public QuoteGeneratorService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    /**
     * Generates a quote based on the given prompt.
     *
     * @param prompt The prompt to generate the quote.
     * @return The generated quote.
     * @throws ResponseStatusException If there is an error retrieving the response from the AI model.
     */
    public String generate(final Prompt prompt){

        String quote;
        try {
            // Call the AI model via ChatClient to retrieve the quote
            quote = chatClient.call(prompt).getResult().getOutput().getContent();
        } catch (Exception e) {
            // Log an error message if unable to retrieve response from AI model
            logger.error("Unable to retrieve response from AI model", e);
            // Throw an exception with HTTP status 500 (Internal Server Error) and error message
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Unable to retrieve response from AI model", e);
        }

        // Add quotation mark and return
        return addQuotationMark(quote);
    }

    /**
     * Adds quotation marks to the given quote if it does not already start with one.
     *
     * @param quote The quote to which quotation marks need to be added.
     * @return The modified quote with quotation marks.
     */
    private String addQuotationMark(String quote) {
        if (! quote.startsWith("\"")) {
            return "\"" + quote + "\"";
        } else {
            return quote;
        }
    }
}

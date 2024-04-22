package com.petronealessio.quoteai.service;

import com.petronealessio.quoteai.model.Quote;
import com.petronealessio.quoteai.model.QuoteTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Locale;

/**
 * The QuoteGeneratorService class is responsible for generating quotes based on prompts using an AI model.
 * It utilizes a ChatClient to interact with the AI model and retrieve the generated quotes.
 */
@Service
public class QuoteGeneratorService {

    private static final Logger logger = LoggerFactory.getLogger(QuoteGeneratorService.class);

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
     * Generates a quote based on the topic and locale.
     *
     * @param topic The topic of the quote.
     * @param locale The locale used for generating the quote.
     * @return The generated quote.
     * @throws ResponseStatusException If there is an error retrieving the response from the AI model.
     */
    public Quote generate(final QuoteTopic topic, final Locale locale){

        // Constructs the prompt based on the topic and locale
        Prompt prompt = PromptBuilder.build(topic, locale);

        String sentence;
        try {
            // Call the AI model via ChatClient to generate the quote
            sentence = chatClient.call(prompt).getResult().getOutput().getContent();
            logger.info("AI model generated the quote successfully");
        } catch (Exception e) {
            // Log an error message if unable to retrieve response from AI model
            logger.error("Unable to retrieve response from AI model", e);
            // Throw an exception with HTTP status 500 (Internal Server Error) and error message
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Unable to retrieve response from AI model", e);
        }
        // Add quotation mark to the sentence
        sentence = addQuotationMark(sentence);

        // Construct quote
        Quote quote = new Quote();
        quote.setSentence(sentence);
        quote.setTopic(topic);
        quote.setLanguageIsoCode(locale.getISO3Language());
        quote.setCreatedDate(Timestamp.from(Instant.now()));

        return quote;
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

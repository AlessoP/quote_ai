package com.petronealessio.quoteai.service;

import com.petronealessio.quoteai.model.Quote;
import com.petronealessio.quoteai.repository.QuoteRepository;
import jakarta.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * This class manages operations for interacting with the database.
 *
 * Please note that, due to the demonstrative nature of this project,
 * the system saves only the latest 10 quotes per language (i.e., 10 for Italian, 10 for English)
 * to prevent exceeding memory limits on the database server, which has limited memory capacity
 * due to cost considerations. As a result, the system checks if there are more than 10 quotes,
 * and if so, it deletes the oldest one to make room for the new one.
 */
@Service
public class QuoteDatabaseManager {

    private static final Logger logger = LoggerFactory.getLogger(QuoteDatabaseManager.class);

    @Autowired
    private QuoteRepository quoteRepository;

    /**
     * Saves a new quote in the database.
     *
     * @param quote The quote to save
     * @return The saved quote, or null if an error occurred during saving
     */
    @Transactional
    @Nullable
    public Quote saveQuote(final Quote quote){

        // Deletes excess quotes if necessary
        deleteExcessQuotes(quote.getLanguageIsoCode());

        try {
            // Save and return the saved quote
            return quoteRepository.save(quote);
        } catch (Exception e) {
            logger.error("Unable to save quote into the database: ", e);
            return null;
        }
    }

    /**
     * Deletes excess quotes for a given language if the number exceeds 10.
     *
     * @param languageIsoCode The language ISO code of the newly generated quote to save
     */
    @Transactional
    private void deleteExcessQuotes(final String languageIsoCode){
        try {
            // Gets all quotes for the language of the new quote
            List<Quote> allQuotes = quoteRepository
                    .findAllByLanguageIsoCodeOrderByCreatedDateDesc(languageIsoCode);

            int THRESHOLD = 10;

            if (allQuotes.size() >= THRESHOLD) {
                // If there are more than 10 quotes, delete the excess ones
                List<Quote> quotesToDelete = allQuotes.subList(THRESHOLD-1, allQuotes.size());
                quoteRepository.deleteAll(quotesToDelete);
            }
        } catch (Exception ex) {
            // If excess quotes are not deleted, it's not a problem
            logger.error("Unable to delete excess quotes from the database: ", ex);
        }
    }
}

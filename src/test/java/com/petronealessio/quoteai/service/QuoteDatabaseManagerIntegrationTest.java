package com.petronealessio.quoteai.service;

import com.petronealessio.quoteai.model.Quote;
import com.petronealessio.quoteai.model.QuoteTopic;
import com.petronealessio.quoteai.repository.QuoteRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
public class QuoteDatabaseManagerIntegrationTest {

    @Autowired
    private QuoteDatabaseManager quoteDatabaseManager;

    @Autowired
    private QuoteRepository quoteRepository;

    @Test
    void testSaveQuote_Success() {
        // Arrange
        Quote quoteToSave = new Quote();
        quoteToSave.setCreatedDate(Timestamp.from(Instant.now()));
        quoteToSave.setSentence("Test");
        quoteToSave.setTopic(QuoteTopic.DEFAULT);
        quoteToSave.setLanguageIsoCode(Locale.ITALIAN.getISO3Language());

        List<Quote> mockQuotes = new ArrayList<>();
        for (int i=0; i<20; i++){
            Quote q = new Quote();
            q.setSentence("Test italian "+i);
            q.setTopic(QuoteTopic.DEFAULT);
            q.setLanguageIsoCode(Locale.ITALIAN.getISO3Language());
            Timestamp timestamp = Timestamp.from(Instant.now().plusSeconds(10*i));
            q.setCreatedDate(timestamp);
            mockQuotes.add(q);
        }

        // Act
        List<Quote> allSavedQuotes = quoteRepository.saveAll(mockQuotes);
        Quote savedQuote = quoteDatabaseManager.saveQuote(quoteToSave);

        // Assert
        assertEquals(20, allSavedQuotes.size());
        assertNotNull(savedQuote);

        // Actually check in the database that the record has been saved
        Quote retrievedQuote = quoteRepository.findById(savedQuote.getId()).orElse(null);
        assertNotNull(retrievedQuote);

        // Check that there are only 10 quotes in the database
        List<Quote> newAllSavedQuotes = quoteRepository
                .findAllByLanguageIsoCodeOrderByCreatedDateDesc(Locale.ITALIAN.getISO3Language());
        assertEquals(10, newAllSavedQuotes.size());
    }
}

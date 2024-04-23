package com.petronealessio.quoteai.repository;

import com.petronealessio.quoteai.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * This interface represents a repository for managing Quote entities.
 *
 * It extends the JpaRepository interface, providing methods for CRUD operations
 * on Quote entities with Integer as the type of the entity's identifier.
 */
public interface QuoteRepository extends JpaRepository<Quote, Integer> {

    /**
     * Retrieves a list of quotes filtered by the specified language ISO code,
     * ordered by created date in descending order.
     *
     * @param languageIsoCode The ISO code of the language to filter quotes by.
     * @return A list of quotes filtered by the specified language ISO code, ordered by created date.
     */
    List<Quote> findAllByLanguageIsoCodeOrderByCreatedDateDesc(String languageIsoCode);

}

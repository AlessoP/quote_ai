package com.petronealessio.quoteai.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

/**
 * Represents a quote entity in the database.
 */
@Entity
@Table(name = "quotes")
public class Quote {

    /**
     * The primary key of the quote.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * The text content of the quote.
     */
    @Column(nullable = false)
    private String sentence;

    /**
     * The ISO code of the language in which the quote is written.
     */
    @Column(name = "lang_iso", nullable = false)
    private String languageIsoCode;

    /**
     * The topic of the quote.
     */
    @Column(nullable = false)
    private QuoteTopic topic;

    /**
     * The timestamp indicating when the quote was created.
     */
    @Column(name = "created_at", nullable = false)
    private Timestamp createdDate;

    /**
     * Retrieves the ID of the quote.
     *
     * @return The ID of the quote.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of the quote.
     *
     * @param id The ID of the quote.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retrieves the text content of the quote.
     *
     * @return The text content of the quote.
     */
    public String getSentence() {
        return sentence;
    }

    /**
     * Sets the text content of the quote.
     *
     * @param sentence The text content of the quote.
     */
    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    /**
     * Retrieves the ISO code of the language in which the quote is written.
     *
     * @return The ISO code of the language.
     */
    public String getLanguageIsoCode() {
        return languageIsoCode;
    }

    /**
     * Sets the ISO code of the language in which the quote is written.
     *
     * @param languageIsoCode The ISO code of the language.
     */
    public void setLanguageIsoCode(String languageIsoCode) {
        this.languageIsoCode = languageIsoCode;
    }

    /**
     * Retrieves the topic of the quote.
     *
     * @return The topic of the quote.
     */
    public QuoteTopic getTopic() {
        return topic;
    }

    /**
     * Sets the topic of the quote.
     *
     * @param topic The topic of the quote.
     */
    public void setTopic(QuoteTopic topic) {
        this.topic = topic;
    }

    /**
     * Retrieves the timestamp indicating when the quote was created.
     *
     * @return The timestamp of when the quote was created.
     */
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets the timestamp indicating when the quote was created.
     *
     * @param createdDate The timestamp of when the quote was created.
     */
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Returns a string representation of the quote.
     *
     * @return A string representation of the quote.
     */
    @Override
    public String toString() {
        return "Quote{" +
                "id=" + id +
                ", sentence='" + sentence + '\'' +
                ", languageIsoCode='" + languageIsoCode + '\'' +
                ", topic=" + topic +
                ", createdDate=" + createdDate +
                '}';
    }
}

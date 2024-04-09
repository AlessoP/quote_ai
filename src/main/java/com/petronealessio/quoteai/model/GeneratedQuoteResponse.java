package com.petronealessio.quoteai.model;

/**
 * The GeneratedQuoteResponse class represents the response body for a generated quote.
 */
public class GeneratedQuoteResponse {

    /**
     * The generated quote.
     */
    private String quote;

    /**
     * Constructs a new instance of GeneratedQuoteResponse with the specified quote.
     *
     * @param quote The generated quote.
     */
    public GeneratedQuoteResponse(String quote) {
        this.quote = quote;
    }

    /**
     * Retrieves the generated quote.
     *
     * @return The generated quote.
     */
    public String getQuote() {
        return quote;
    }

    /**
     * Sets the generated quote.
     *
     * @param quote The generated quote.
     */
    public void setQuote(String quote) {
        this.quote = quote;
    }
}

package com.petronealessio.quoteai.service;

import com.petronealessio.quoteai.model.QuoteTopic;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * The PromptBuilder class is responsible for constructing a prompt for generating quotes
 * based on the specified topic and locale.
 */
@Service
public class PromptBuilder {

    /**
     * Builds a prompt for generating quotes based on the specified topic and locale.
     *
     * @param topic The topic of the quote.
     * @param locale The locale to determine the language of AI model response.
     * @return The constructed prompt.
     */
    public static Prompt build(final QuoteTopic topic, final Locale locale){

        //Get the language of the locale in lowercase
        String language = locale.getDisplayLanguage(Locale.ENGLISH).toLowerCase();

        //Determine the user message based on the specified topic
        Message userMessage = switch (topic) {
            case MOTIVATIONAL -> new UserMessage("Generate a motivational aphorism");
            case HUMOROUS -> new UserMessage("Generate a humorous aphorism");
            case FRIENDSHIP -> new UserMessage("Generate an aphorism about friendship");
            case LOVE -> new UserMessage("Generate an aphorism about love");
            case EXISTENTIAL -> new UserMessage("Generate an existential aphorism");
            default -> new UserMessage("Generate an aphorism");
        };

        //Create a system message template to prompt the model to reply in a specific language
        SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate("You're a quote generator that writes " +
                "aphorisms. Reply in {language}.");

        //Create the system message by replacing the placeholder with the language
        Message systemMessage = systemPromptTemplate.createMessage(Map.of("language", language));

        //Construct and return the prompt with the user and system messages
        return new Prompt(List.of(userMessage, systemMessage));
    }
}

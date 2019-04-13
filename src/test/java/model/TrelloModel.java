package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Model that represents the Trello objects' data
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public interface TrelloModel {

    /**
     * Provides the object unique identifier
     *
     * @return Identifier
     */
    String getId();

    /**
     * Provides the object name
     *
     * @return Name
     */
    String getName();
}

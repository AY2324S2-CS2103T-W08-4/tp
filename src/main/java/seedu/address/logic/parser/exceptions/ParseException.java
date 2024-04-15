package seedu.address.logic.parser.exceptions;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a parse error encountered by a parser.
 */
public class ParseException extends IllegalValueException {
    /**
     * Constructs a new {@code ParseException} with the specified detail message.
     *
     * @param message the detail message of the exception
     */
    public ParseException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code ParseException} with the specified detail message and cause.
     *
     * @param message the detail message of the exception
     * @param cause the cause of the exception
     */
    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }

}

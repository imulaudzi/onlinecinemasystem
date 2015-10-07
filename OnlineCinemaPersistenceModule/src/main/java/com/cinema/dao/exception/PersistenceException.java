package com.cinema.dao.exception;

/**
 * Persistence exception.
 */
public class PersistenceException extends RuntimeException {

    private static final long serialVersionUID = 5597498802998013912L;

    /**
     * Persistence exception constructor.
     *
     * @param message error description.
     */
    public PersistenceException(String message) {
        super(message);
    }

    /**
     * Persistence exception constructor.
     *
     * @param message error description
     * @param cause the reason this exception occurred.
     */
    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}

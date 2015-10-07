package com.cinema.dao.generic.finder;

import org.hibernate.type.Type;

/**
 * Used to locate any specific type mappings that might be necessary for a dto implementation.
 */
public interface FinderArgumentTypeFactory {
    /**
     * Method to get argument type details.
     *
     * @param arg object argument
     * @return type
     */
    Type getArgumentType(final Object arg);
}


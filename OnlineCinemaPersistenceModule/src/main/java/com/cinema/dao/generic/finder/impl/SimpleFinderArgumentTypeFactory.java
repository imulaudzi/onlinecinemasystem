package com.cinema.dao.generic.finder.impl;


import com.cinema.dao.generic.finder.FinderArgumentTypeFactory;
import org.hibernate.type.Type;

/**
 * Maps Enums to a custom Hibernate user type.
 */
public class SimpleFinderArgumentTypeFactory implements FinderArgumentTypeFactory {

    /**
     * Method to get argument type.
     *
     * @param arg object argument
     * @return null
     */
    public Type getArgumentType(final Object arg) {
        return null;
    }

}

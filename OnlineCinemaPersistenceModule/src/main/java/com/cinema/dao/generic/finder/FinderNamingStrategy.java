package com.cinema.dao.generic.finder;

import java.lang.reflect.Method;

/**
 * Used to locate a named query based on the called finder method.
 */
public interface FinderNamingStrategy {

    /**
     * Method to query for functions with given name.
     *
     * @param findTargetType target
     * @param finderMethod   method
     * @return class.method name
     */
    String queryNameFromMethod(final Class findTargetType, final Method finderMethod);
}

package com.cinema.dao.generic.finder.impl;




import com.cinema.dao.generic.finder.FinderNamingStrategy;

import java.lang.reflect.Method;

/**
 * Looks up Hibernate named queries based on the simple name of the invoked class and the method name of the invocation.
 */
public class SimpleFinderNamingStrategy implements FinderNamingStrategy {

    /**
     * Method to query for method by its name.
     *
     * @param findTargetType target
     * @param finderMethod   method name
     * @return class.method name
     */
    public String queryNameFromMethod(final Class findTargetType, final Method finderMethod) {

        String methodName = finderMethod.getName();
        String methodPart = methodName;

        final String page = "page";
        final String list = "list";

        if (methodName.startsWith(page)) {
            methodPart = list + methodName.substring(page.length());
        }

        return findTargetType.getSimpleName() + "." + methodPart;
    }

}

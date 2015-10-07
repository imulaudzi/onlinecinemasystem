package com.cinema.dao.generic.finder.impl;


import com.cinema.dao.generic.finder.FinderNamingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.lang.reflect.Method;

/**
 * Looks up Hibernate named queries based on the simple name of the invoked class and the method name of the invocation.
 * Always look for queries that start with findBy (even if method is iterateBy.. or scrollBy...)
 */
public class ExtendedFinderNamingStrategy implements FinderNamingStrategy {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ExtendedFinderNamingStrategy.class);

    /**
     * Method to query for a method by its name.
     *
     * @param findTargetType target
     * @param finderMethod   method name
     * @return class.method name
     */
    public String queryNameFromMethod(final Class findTargetType, final Method finderMethod) {
        String methodName = finderMethod.getName();
        String methodPart = methodName;
        final String findBy = "findBy";
        final String listBy = "listBy";
        final String page = "page";
        final String list = "list";
        final String iterateBy = "iterateBy";
        final String scrollBy = "scrollBy";
        if (methodName.startsWith(findBy)) {
            LOGGER.info("method starts with findBy");
        } else if (methodName.startsWith(listBy)) {
            methodPart = findBy + methodName.substring(listBy.length());
        } else if (methodName.startsWith(iterateBy)) {
            methodPart = findBy + methodName.substring(iterateBy.length());
        } else if (methodName.startsWith(scrollBy)) {
            methodPart = findBy + methodName.substring(scrollBy.length());
        }else if (methodName.startsWith(page)) {
            methodPart = list + methodName.substring(page.length());
        }
        return findTargetType.getSimpleName() + "." + methodPart;
    }

}

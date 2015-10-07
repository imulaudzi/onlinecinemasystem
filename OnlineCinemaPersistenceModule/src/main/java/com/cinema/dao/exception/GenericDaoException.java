package com.cinema.dao.exception;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * Generic DAO Exception.
 */
public class GenericDaoException extends PersistenceException {
    private static final long serialVersionUID = 3917537935376152140L;
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericDaoException.class);

    /**
     * Constructor for exception.
     * 
     * @param method
     * @param queryArgs 
     * @param namedQuery 
     * @param exception
     */
    public GenericDaoException(Method method, Object[] queryArgs, Query namedQuery, Throwable exception) {
        super(getTraceCall(method, queryArgs, namedQuery, exception), exception);
    }

    private static String getTraceCall(Method method, Object[] queryArgs, Query namedQuery, Throwable ex) {
        try {
            String traceMessage = traceSqlCall(method, queryArgs, namedQuery);
            traceMessage = String.format("Underlying exception: [%s] \nDetails:\n%s", ex.getMessage(), traceMessage);

            return traceMessage;
        } catch (Exception generalEx) {
            // failed to get trace message, will continue anyway.
            LOGGER.info(String.format("Failed to get trace message, will continue anyway. \n Exception caught: [%s]", generalEx));
            return String.format("Could not resolve SQL Trace. Message [%s]", generalEx.getMessage());
        }
    }

    private static String traceSqlCall(Method method, Object[] queryArgs, Query namedQuery) {

        StringBuilder builder = new StringBuilder(
                String.format("Called method [%s] with return type [%s]\n", method.getName(), method.getReturnType().getCanonicalName()));

        String queryString = namedQuery.getQueryString();
        if(queryArgs.length == namedQuery.getNamedParameters().length) {
            for(int i = 0; i < queryArgs.length; i++) {
                String parameter = namedQuery.getNamedParameters()[i];
                String value = queryArgs[i].toString();
                builder.append(String.format("Parameter [%s = %s]\n",
                        parameter,
                        value));
                queryString = queryString.replace(":" + parameter, value);
            }
        } else {
            builder.append("*** Query args were not equal to named parameters, this should not happen.\n");
        }

        builder.append(String.format("Best guess SQL query - [%s]\n", queryString));

        return builder.toString();
    }
}

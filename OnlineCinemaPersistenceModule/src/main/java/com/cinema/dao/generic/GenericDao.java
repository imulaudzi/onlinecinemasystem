package com.cinema.dao.generic;

import com.cinema.dao.pagination.Page;
import org.hibernate.Query;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by ISAAC on 8/19/2015.
 */
public interface GenericDao<T, P extends Serializable> {

    /**
     * Create.
     *
     * @param newInstance instance
     * @return PK
     */
    P create(T newInstance);

    /**
     * Read.
     *
     * @param id id
     * @return T
     */
    T read(P id);

    /**
     * Update.
     *
     * @param transientObject object
     */
    void update(T transientObject);

    /**
     * Delete.
     *
     * @param persistentObject object
     */
    void delete(T persistentObject);

    /**
     * Executes a query and returns a list of objects of type T.
     *
     * @param query the query to execute.
     * @param parameters a parameter map to use for placeholders in query.
     * @return List<T> list of objects of type T.
     */
    List<T> executeQuery(String query, Map<String, Object> parameters);

    int executeUpdateNamedQuery(String queryName, Map<String, Object> parameters);

    int executeUpdateQuery(String query, Map<String, Object> parameters);

    /**
     * Executes a query and returns a list of objects of type T.
     *
     * @param query the query to execute.
     * @param parameters a parameter map to use for placeholders in query.
     * @return List<T> list of bjects of type T.
     */
    List<T> executeQuery(Query query, Map<String, Object> parameters);

    List<T> executeQuery(String query, Map<String, Object> parameters, Page pagingHelper);

    /**
     *   Executes a native query and returns a list of objects of type T.
     * @param query
     * @param parameters
     * @param paging
     * @return
     */
    List<T> executeNativeQuery(String query, Map<String, Object> parameters,Page paging);
}
package com.cinema.dao.generic;

import org.hibernate.Query;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by ISAAC on 8/19/2015.
 */
public interface GenericDao<T, P extends Serializable> {

    /*
     */
    P create(T instance);

    T read(P id);

    void update(T transientObject);

    void delete(T persistentObject);


    List<T> executeQuery(String query, Map<String, Object> parameters);

    int executeUpdateNamedQuery(String query, Map<String, Object> parameters);

    int executeUpdateQuery(String query, Map<String, Object> parameters);

    List<T> executeQuery(Query query, Map<String, Object> parameters);

//    List<T> executeQuery(String query,Map<String,Object> parameters,Page);
//    List<T> executeNativeQuery(String query,Map<String,Object> parameters,Page);


}

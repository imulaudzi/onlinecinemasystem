package com.cinema.dao.generic.Impl;

import com.cinema.dao.generic.GenericDao;
import org.hibernate.Query;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 8/21/2015.
 */
public class GenericDaoHibernateImpl<T, P extends Serializable> implements GenericDao<T, P> {
    public P create(T instance) {
        return null;
    }

    public T read(P id) {
        return null;
    }

    public void update(T transientObject) {

    }

    public void delete(T persistentObject) {

    }

    public int executeUpdateNamedQuery(String query, Map<String, Object> parameters) {
        return 0;
    }

    public int executeUpdateQuery(String query, Map<String, Object> parameters) {
        return 0;
    }

    public List<T> executeQuery(Query query, Map<String, Object> parameters) {
        return null;
    }

    public List<T> executeQuery(String query, Map<String, Object> parameters) {
        return null;
    }
//
//    public List<T> executeNativeQuery(String query, Map<String, Object> parameters) {
//        return null;
//    }
}

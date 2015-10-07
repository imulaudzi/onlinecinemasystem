package com.cinema.dao.generic.Impl;

import com.cinema.dao.exception.GenericDaoException;
import com.cinema.dao.generic.GenericDao;
import com.cinema.dao.generic.finder.FinderArgumentTypeFactory;
import com.cinema.dao.generic.finder.FinderExecutor;
import com.cinema.dao.generic.finder.FinderNamingStrategy;
import com.cinema.dao.generic.finder.impl.SimpleFinderArgumentTypeFactory;
import com.cinema.dao.generic.finder.impl.SimpleFinderNamingStrategy;
import com.cinema.dao.pagination.Page;
import org.hibernate.Query;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 8/21/2015.
 */
/**
 * Hibernate implementation of GenericDao A type safe implementation of CRUD and finder methods based on Hibernate and Spring AOP The finders are implemented through the executeFinder method. Normally
 * called by the FinderIntroductionInterceptor.
 *
 * @param <T> Dao Type
 * @param <P> Primary Key for the Dao
 */
@SuppressWarnings("unchecked")
public class GenericDaoHibernateImpl<T, P extends Serializable> implements GenericDao<T, P>, FinderExecutor {

    /**
     * Logger.
     */
    private static final Logger LOGGER;
    /**
     * session factory.
     */
    private SessionFactory sessionFactory;
    /**
     * naming strategy.
     */
    private FinderNamingStrategy namingStrategy = new SimpleFinderNamingStrategy();
    /**
     * argument type factory.
     */
    private FinderArgumentTypeFactory argumentTypeFactory = new SimpleFinderArgumentTypeFactory();
    /**
     * type.
     */
    private Class<T> type;

    private DefaultConversionService conversionService = new DefaultConversionService();

    static {
        LOGGER = LoggerFactory.getLogger(GenericDaoHibernateImpl.class);
    }

    /**
     * Constructor.
     *
     * @param aType type
     */
    public GenericDaoHibernateImpl(final Class<T> aType) {
        this.type = aType;
    }

    /**
     * Constructor.
     */
    public GenericDaoHibernateImpl() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Create.
     *
     * @param o object
     * @return PK
     */
    @Transactional
    public P create(final T o) {
        LOGGER.trace("create [{}]", o.toString());
        return (P) getSession().save(o);
    }

    /**
     * Read.
     *
     * @param id id
     * @return session details
     */
    @Transactional(readOnly = true)
    public T read(final P id) {
        LOGGER.trace("read [{}]", id.toString());
        return (T) getSession().get(type, id);
    }

    /**
     * Update.
     *
     * @param o object
     */
    @Transactional
    public void update(final T o) {
        LOGGER.trace("update [{}]", o.toString());
        getSession().saveOrUpdate(o);
    }

    /**
     * Delete.
     *
     * @param o object
     */
    @Transactional
    public void delete(final T o) {
        LOGGER.trace("delete [{}]", o.toString());
        getSession().delete(o);
    }

    /**
     * Execute finder.
     *
     * @param method    method name
     * @param queryArgs query argument
     * @return list of named queries
     */
    @Transactional
    public List<T> executeFinderOld(final Method method, final Object[] queryArgs) {
        final String queryName = getNamingStrategy().queryNameFromMethod(type, method);
        final Query namedQuery = getSession().getNamedQuery(queryName);
        String[] namedParameters = findParameters(namedQuery.getQueryString());
        if (namedParameters.length == 0) {
            setPositionalParams(queryArgs, namedQuery);
        } else {
            setNamedParams(namedParameters, queryArgs, namedQuery);
        }
        return namedQuery.list();
    }

    public List<T> executeLister(Method method, final Object[] queryArgs) {
        Query namedQuery = createNamedQuery(method, queryArgs);
        try {
            return namedQuery.list();
        } catch (HibernateException ex) {
            throw new GenericDaoException(method, queryArgs, namedQuery, ex);
        }
    }


    @Transactional
    public Integer executeUpdate(Method method, final Object[] queryArgs) {
        Query namedQuery = createNamedQuery(method, queryArgs);
        try {
            return namedQuery.executeUpdate();
        } catch (HibernateException ex) {
            throw new GenericDaoException(method, queryArgs, namedQuery, ex);
        }
    }

    public BigInteger executeNextSequenceFinder(Method method, Object[] queryArgs){
        Query namedQuery = createNamedQuery(method, queryArgs);
        try {
            return conversionService.convert(namedQuery.uniqueResult(), BigInteger.class);
        } catch (HibernateException ex) {
            throw new GenericDaoException(method, queryArgs, namedQuery, ex);
        }
    }

    public T executeFinder(Method method, final Object[] queryArgs) {
        Query namedQuery = createNamedQuery(method, queryArgs);
        try {
            return (T) namedQuery.uniqueResult();
        } catch (HibernateException ex) {
            throw new GenericDaoException(method, queryArgs, namedQuery, ex);
        }
    }

    public List<T> executeFinder(Method method, final Object[] queryArgs, final Page pagingHelper) {
        final String queryName = getNamingStrategy().queryNameFromMethod(type, method);
        final Query namedQuery = getSession().getNamedQuery(queryName);
        String[] namedParameters = findParameters(namedQuery.getQueryString());
        if (namedParameters.length == 0) {
            setPositionalParams(queryArgs, namedQuery);
        } else {
            setNamedParams(namedParameters, queryArgs, namedQuery);
        }
        if (pagingHelper != null) {
            if (pagingHelper.isLimited()) {
                namedQuery.setMaxResults(pagingHelper.getPageSize());
            }
            namedQuery.setFirstResult(pagingHelper.getCurrentOffset());
        }
        return namedQuery.list();
    }

    private Query createNamedQuery(Method method, final Object[] queryArgs) {
        final String queryName = getNamingStrategy().queryNameFromMethod(type, method);
        final Query namedQuery = getSession().getNamedQuery(queryName);
        String[] namedParameters = findParameters(namedQuery.getQueryString());
        if (namedParameters.length == 0) {
            setPositionalParams(queryArgs, namedQuery);
        } else {
            setNamedParams(namedParameters, queryArgs, namedQuery);
        }
        return namedQuery;
    }




    /**
     * Method to find parameters.
     *
     * @param query query
     * @return names
     */
    private String[] findParameters(final String query) {
        List<String> list = new ArrayList<String>(0);
        Pattern pattern = Pattern.compile("[:].[\\w]*\\b");
        Matcher matcher = pattern.matcher(query);
        while (matcher.find()) {
            String group = matcher.group();
            list.add(group.substring(1));
        }
        String[] names = new String[list.size()];
        return list.toArray(names);
    }

    /**
     * method to iterate finder methods.
     *
     * @param method    method
     * @param queryArgs arguments
     * @return list
     */
    @Transactional
    public Iterator<T> iterateFinder(final Method method, final Object[] queryArgs) {
        final Query namedQuery = prepareQuery(method, queryArgs);
        return (Iterator<T>) namedQuery.iterate();
    }

    /**
     * Method to prepare query.
     *
     * @param method    method
     * @param queryArgs arguments
     * @return query
     */
    private Query prepareQuery(final Method method, final Object[] queryArgs) {
        final String queryName = getNamingStrategy().queryNameFromMethod(type, method);
        LOGGER.debug("%%%:QueryName:" + queryName);
        final Query namedQuery = getSession().getNamedQuery(queryName);
        String[] namedParameters = namedQuery.getNamedParameters();
        if (namedParameters.length == 0) {
            LOGGER.debug("No Parameters");
            setPositionalParams(queryArgs, namedQuery);
        } else {
            LOGGER.debug("Set Parameters:");
            setNamedParams(namedParameters, queryArgs, namedQuery);
        }
        return namedQuery;
    }

    @Override
    public List<T> executeQuery(String query, Map<String, Object> parameters) {
        Query compiledQuery = getSession().createQuery(query);
        this.setMappedNamedParams(parameters, compiledQuery);
        return compiledQuery.list();
    }


    @Override
    public int executeUpdateNamedQuery(String queryName, Map<String, Object> parameters) {
        Query namedQuery = getSession().getNamedQuery(queryName);

        this.setMappedNamedParams(parameters, namedQuery);
        return namedQuery.executeUpdate();
    }

    @Override
    public int executeUpdateQuery(String query, Map<String, Object> parameters) {
        Query compiledQuery = getSession().createSQLQuery(query);
        this.setMappedNamedParams(parameters, compiledQuery);
        return compiledQuery.executeUpdate();
    }

    @Override
    public List<T> executeQuery(Query query, Map<String, Object> parameters) {
        this.setMappedNamedParams(parameters, query);
        return query.list();
    }

    @Override
    public List<T> executeQuery(String query, Map<String, Object> parameters,Page paging) {
        Query compiledQuery = getSession().createQuery(query);
        compiledQuery.setFirstResult(paging.getCurrentOffset());
        compiledQuery.setMaxResults(paging.getPageSize());
        this.setMappedNamedParams(parameters, compiledQuery);
        return compiledQuery.list();
    }



    public List<T> executeNativeQuery(String query, Map<String, Object> parameters,Page paging) {
        Query compiledQuery = getSession().createSQLQuery(query).addEntity(type);
        compiledQuery.setFirstResult(paging.getCurrentOffset());
        compiledQuery.setMaxResults(paging.getPageSize());
        this.setMappedNamedParams(parameters, compiledQuery);
        return compiledQuery.list();
    }

    /**
     * Method to set position parameters.
     *
     * @param queryArgs  arguments
     * @param namedQuery named query
     */
    private void setPositionalParams(final Object[] queryArgs, final Query namedQuery) {
        // Set parameter. Use custom Hibernate Type if necessary
        if (queryArgs != null) {
            for (int i = 0; i < queryArgs.length; i++) {
                Object arg = queryArgs[i];
                Type argType = getArgumentTypeFactory().getArgumentType(arg);
                if (argType != null) {
                    namedQuery.setParameter(i, arg, argType);
                } else {
                    namedQuery.setParameter(i, arg);
                }
            }
        }
    }

    /**
     * Method to set named parameters.
     *
     * @param namedParameters parameters
     * @param queryArgs       arguments
     * @param namedQuery      named query
     */
    private void setNamedParams(final String[] namedParameters, final Object[] queryArgs, final Query namedQuery) {
        // Set parameter. Use custom Hibernate Type if necessary
        if (queryArgs != null) {
            if (queryArgs[0] instanceof Map) {
                setMappedNamedParams((Map) queryArgs[0], namedQuery);
            } else {
                for (int i = 0; i < queryArgs.length; i++) {
                    Object arg = queryArgs[i];
                    setNamedParam(namedParameters[i], arg, namedQuery);
                }
            }
        }
    }

    /**
     * Method to set named parameters.
     *
     * @param queryArgs  arguments
     * @param namedQuery named query
     */
    private void setMappedNamedParams(final Map queryArgs, final Query namedQuery) {
        // Set parameter. Use custom Hibernate Type if necessary
        if (queryArgs != null) {
            Set<String> objectSet = queryArgs.keySet();
            for (String key : objectSet) {
                setNamedParam(key, queryArgs.get(key), namedQuery);
            }
        }
    }

    /**
     * Method to set a single named parameter.
     *
     * @param namedParameter parameters
     * @param arg            argument
     * @param namedQuery     named query
     */
    private void setNamedParam(final String namedParameter, final Object arg, final Query namedQuery) {
        Type argType = getArgumentTypeFactory().getArgumentType(arg);
        if (argType != null) {
            namedQuery.setParameter(namedParameter, arg, argType);
            LOGGER.debug("NameParameter:" + namedParameter);
        } else {
            if (arg instanceof Collection) {
                namedQuery.setParameterList(namedParameter, (Collection) arg);
            } else {
                namedQuery.setParameter(namedParameter, arg);
            }
        }
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void setSessionFactory(final SessionFactory aSessionFactory) {
        this.sessionFactory = aSessionFactory;
    }

    public FinderNamingStrategy getNamingStrategy() {
        return namingStrategy;
    }

    public void setNamingStrategy(final FinderNamingStrategy aNamingStrategy) {
        this.namingStrategy = aNamingStrategy;
    }

    public FinderArgumentTypeFactory getArgumentTypeFactory() {
        return argumentTypeFactory;
    }

    public void setArgumentTypeFactory(final FinderArgumentTypeFactory anArgumentTypeFactory) {
        this.argumentTypeFactory = anArgumentTypeFactory;
    }
}

package com.cinema.dao.generic.finder;



import com.cinema.dao.pagination.Page;

import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;

/**
 * Finder executor.
 *
 * @param <T>
 */
public interface FinderExecutor<T> {

  /**
   * Execute a finder method with the appropriate arguments.
   *
   * @param method    method name
   * @param queryArgs query argument
   * @return single object of execute finder method
   */
  T executeFinder(Method method, Object[] queryArgs);

  List<T> executeFinder(Method method, Object[] queryArgs, Page pagingHelper);

  /**
   * Execute a lister method with the appropriate arguments.
   *
   * @param method    method name
   * @param queryArgs query argument
   * @return list of execute lister method
   */
  List<T> executeLister(Method method, Object[] queryArgs);

  /**
   * Method to iterate a finder method with the appropriate arguments.
   *
   * @param method    method
   * @param queryArgs arguments
   * @return list if iterated finder method
   */
  Iterator<T> iterateFinder(Method method, Object[] queryArgs);

  /**
   * Method to get the next sequnce value.
   *
   * @param method    method
   * @param queryArgs arguments
   * @return An integer value of the next sequence
   */
  BigInteger executeNextSequenceFinder(Method method, Object[] queryArgs);


   /**
    *  Method to execute updates named queries
    * @param method
    * @param queryArgs
    * @return
   */
  Integer executeUpdate(Method method, final Object[] queryArgs);


}

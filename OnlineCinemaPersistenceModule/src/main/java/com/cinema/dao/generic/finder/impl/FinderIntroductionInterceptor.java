package com.cinema.dao.generic.finder.impl;


import com.cinema.dao.generic.finder.FinderExecutor;
import com.cinema.dao.pagination.Page;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.IntroductionInterceptor;

import java.util.Arrays;

/**
 * Connects the Spring AOP magic with the Hibernate DAO magic. For any method beginning with "find" this interceptor will use the FinderExecutor to call a Hibernate named query.
 */
public class FinderIntroductionInterceptor implements IntroductionInterceptor {

  /**
   * Logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(FinderIntroductionInterceptor.class);

  /**
   * Invoke method.
   *
   * @param methodInvocation method invocation
   * @return object
   * @throws Throwable exception
   */
  @Override
  public Object invoke(final MethodInvocation methodInvocation) throws Throwable {
    FinderExecutor executor = (FinderExecutor) methodInvocation.getThis();
    String methodName = methodInvocation.getMethod().getName();
    LOGGER.trace("Invoke called for method [{}]", methodName);
    Object returnObject;
    if (methodName.startsWith("find")) {
      Object[] arguments = methodInvocation.getArguments();
      returnObject = executor.executeFinder(methodInvocation.getMethod(), arguments);
    } else if (methodName.startsWith("list")) {
      Object[] arguments = methodInvocation.getArguments();
      returnObject = executor.executeLister(methodInvocation.getMethod(), arguments);
    }else if (methodName.startsWith("update") && methodName.length() > 7) {
        Object[] arguments = methodInvocation.getArguments();
        returnObject = executor.executeUpdate(methodInvocation.getMethod(), arguments);
    } else if (methodName.startsWith("iterate")) {
      Object[] arguments = methodInvocation.getArguments();
      returnObject = executor.iterateFinder(methodInvocation.getMethod(), arguments);
    } else if (methodName.startsWith("next")) {
      Object[] arguments = methodInvocation.getArguments();
      returnObject = executor.executeNextSequenceFinder(methodInvocation.getMethod(), arguments);
    } else if (methodName.startsWith("page")) {
      Object[] arguments = methodInvocation.getArguments();
        Page pagingHelper = (Page) arguments[0];
      Object[] args = Arrays.copyOfRange(arguments, 1, arguments.length);
      return executor.executeFinder(methodInvocation.getMethod(), args, pagingHelper);
    } else {
      returnObject = methodInvocation.proceed();
    }
    return returnObject;
  }

  /**
   * Method to implements interface.
   *
   * @param intf interface
   * @return true or false
   */
  public boolean implementsInterface(final Class intf) {
    return intf.isInterface() && FinderExecutor.class.isAssignableFrom(intf);
  }
}


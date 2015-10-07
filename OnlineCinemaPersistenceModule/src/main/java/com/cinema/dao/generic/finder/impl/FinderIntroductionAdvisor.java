package com.cinema.dao.generic.finder.impl;


import com.cinema.dao.generic.finder.FinderExecutor;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DefaultIntroductionAdvisor;

/**
 * Custom finder introduction advisor.
 */
public class FinderIntroductionAdvisor extends DefaultIntroductionAdvisor {

    /**
     * Default constructor.
     */
    public FinderIntroductionAdvisor() {
        super(new FinderIntroductionInterceptor());
    }

    @Override
    public ClassFilter getClassFilter() {
        return new ClassFilter() {

            public boolean matches(final Class c) {
                return FinderExecutor.class.isAssignableFrom(c);
            }
        };
    }

}

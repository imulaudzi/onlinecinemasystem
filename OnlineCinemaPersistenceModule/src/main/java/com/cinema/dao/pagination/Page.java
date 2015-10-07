package com.cinema.dao.pagination;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA. User: leonard Date: 2012/06/18 Time: 9:35 AM To change this template use File | Settings | File Templates.
 */
public interface Page extends Serializable {

    /**
     * @return the number of results per page
     */
    public int getPageSize();

    /**
     * @return index of a position in a result set
     */
    public int getCurrentOffset();

    /**
     * @return true if <code>pageSize</code> is set (to <code>Integer.MAX_VALUE</code>)
     */
    public boolean isLimited();
}

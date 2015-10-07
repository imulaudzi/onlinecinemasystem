package com.cinema.dao.pagination;

/**
 * Created by IntelliJ IDEA. User: leonard12 Date: 7/4/12 Time: 5:03 PM To change this template use File | Settings | File Templates.
 */
public class LimitedPage implements Page {

  /**
   * Index of the first record starting from 0
   */
  private int currentOffset;
  /**
   * Number of records on each page.
   * <p/>
   * page = records[currentOffset], records[currentOffset + 1], ... records[currentOffset + pageSize-1];
   */
  private int pageSize;

  public LimitedPage(int currentOffset, int pageSize) {
    this.currentOffset = currentOffset;
    this.pageSize = pageSize;
  }

  @Override
  public int getPageSize() {
    return pageSize;
  }

  @Override
  public int getCurrentOffset() {
    return currentOffset;
  }

  @Override
  public boolean isLimited() {
    return true;
  }
}

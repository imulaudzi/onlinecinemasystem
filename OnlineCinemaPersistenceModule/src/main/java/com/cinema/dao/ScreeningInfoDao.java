package com.cinema.dao;

import com.cinema.dao.generic.GenericDao;
import com.cinema.dao.model.ScreeningInfo;
import org.joda.time.DateTime;

import java.util.List;

/**
 * Created by isaac on 2015/11/11.
 */
public interface ScreeningInfoDao extends GenericDao<ScreeningInfo, Long> {

    ScreeningInfo findByScreeningInfoId(Long screeningInfoId);

    List<ScreeningInfo> findScreeningInfoByMovieInfoId(Long movieInfoId);

    List<ScreeningInfo> findScreeningInfoByScreeningRoomId(Long screeningRoomId);

    List<ScreeningInfo> findScreeningInfoByShowTime(DateTime showTime);
}

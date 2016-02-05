package com.cinema.dao;

import com.cinema.dao.generic.GenericDao;
import com.cinema.dao.model.ScreeningRoom;

import java.util.List;

/**
 * Created by isaac on 2015/11/11.
 */
public interface ScreeningRoomDao extends GenericDao<ScreeningRoom, Long> {

    ScreeningRoom findByScreeningRoomId(Long screeningRoomId);

    List<ScreeningRoom> listScreeningRooms();
}

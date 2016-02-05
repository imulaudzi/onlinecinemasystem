package com.cinema.dao;

import com.cinema.dao.generic.GenericDao;
import com.cinema.dao.model.ScreeningRoomSeat;

import java.util.List;

/**
 * Created by isaac on 2015/11/11.
 */
public interface ScreeningRoomSeatDao extends GenericDao<ScreeningRoomSeat, Long> {

    ScreeningRoomSeat findSeatbyId(Long screeningRoomSeat);

    List<ScreeningRoomSeat> listScreeningRoomSeats();
}

package com.cinema.dao;

import com.cinema.dao.generic.GenericDao;
import com.cinema.dao.model.Booking;

import java.util.List;

/**
 * Created by isaac on 2015/11/11.
 */
public interface BookingDao extends GenericDao<Booking, Long> {

    Booking findByBookingId(Long bookingId);

    List<Booking> findBookingByUserId(Long UserId);

    Booking findBookingByTicketId(Long ticketId);

    List<Booking> findBookingByTicketTypeId(Long ticketTypeId);
}

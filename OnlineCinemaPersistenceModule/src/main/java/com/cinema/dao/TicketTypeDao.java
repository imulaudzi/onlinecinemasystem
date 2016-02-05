package com.cinema.dao;

import com.cinema.dao.generic.GenericDao;
import com.cinema.dao.model.TicketType;

import java.util.List;

/**
 * Created by Administrator on 10/29/2015.
 */
public interface TicketTypeDao extends GenericDao<TicketType, Long> {

    TicketType findTicketTypeById(Long ticketTypeId);

    List<TicketType> listTicketTypes();

}

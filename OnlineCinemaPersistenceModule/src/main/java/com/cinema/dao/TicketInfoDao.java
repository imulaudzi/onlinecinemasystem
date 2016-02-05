package com.cinema.dao;

import com.cinema.dao.generic.GenericDao;
import com.cinema.dao.model.TicketInfo;

/**
 * Created by isaac on 2015/11/11.
 */
public interface TicketInfoDao extends GenericDao<TicketInfo, Long> {

    TicketInfo findTicketById(Long ticketInfoId);

    TicketInfo findTicketByTicketTypeId(Long ticketTypeId);

    TicketInfo findTicketByScreeningInfoId(Long screeningInfoId);
}

package com.cinema.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Created by ISAAC on 7/21/2015.
 */
@Entity
@Table(name = "BOOKING")
public class Booking {

    @Id
    @SequenceGenerator(name = "BOOKING_SEQ", sequenceName = "BOOKING_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOOKING_SEQ")
    @Column(name = "BOOKING_ID")
    private Long bookingId;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserInfo userInfo;
    @ManyToOne
    @JoinColumn(name = "TICKET_ID")
    private TicketInfo ticketInfo;
    @ManyToOne
    @JoinColumn(name = "TICKET_TYPE_ID")
    private TicketType ticketType;

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public TicketInfo getTicketInfo() {
        return ticketInfo;
    }

    public void setTicketInfo(TicketInfo ticketInfo) {
        this.ticketInfo = ticketInfo;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }
}

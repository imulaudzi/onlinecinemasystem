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
import java.math.BigDecimal;

/**
 * Created by ISAAC on 7/21/2015.
 */
@Entity
@Table(name = "TICKET_INFO")
public class TicketInfo {

    @Id
    @SequenceGenerator(name = "TICKET_INFO_SEQ", sequenceName = "TICKET_INFO_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TICKET_INFO_SEQ")
    @Column(name = "TICKET_ID")
    private Long ticketId;
    @ManyToOne
    @JoinColumn(name = "TICKET_TYPE_ID")
    private TicketType ticketType;
    @ManyToOne
    @JoinColumn(name = "SCREENING_INFO_ID")
    private ScreeningInfo screeningInfo;
    @Column(name = "SEAT_NUMBER")
    private String seatNo;
    @Column(name = "PRICE")
    private BigDecimal price;

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ScreeningInfo getScreeningInfo() {
        return screeningInfo;
    }

    public void setScreeningInfo(ScreeningInfo screeningInfo) {
        this.screeningInfo = screeningInfo;
    }
}

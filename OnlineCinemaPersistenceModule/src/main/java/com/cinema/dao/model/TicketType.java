package com.cinema.dao.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Created by ISAAC on 7/21/2015.
 */
@Entity
@Table(name = "TICKET_TYPE")
public class TicketType {

    @Id
    @SequenceGenerator(name = "TICKET_TYPE_SEQ", sequenceName = "TICKET_TYPE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TICKET_TYPE_SEQ")
    @Column(name = "TICKET_TYPE_ID")
    private Long ticketTypeId;
    @Column(name = "TYPE")
    private String type;
    @Column(name = "DISCOUNT")
    private String discount;

    public Long getTicketTypeId() {
        return ticketTypeId;
    }

    public void setTicketTypeId(Long ticketTypeId) {
        this.ticketTypeId = ticketTypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
}

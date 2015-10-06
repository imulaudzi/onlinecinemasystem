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
@Table(name = "SCREENING_ROOM_SEAT")
public class ScreeningRoomSeat {

    @Id
    @SequenceGenerator(name = "SCREENING_ROOM_SEAT_SEQ", sequenceName = "SCREENING_ROOM_SEAT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SCREENING_ROOM_SEAT_SEQ")
    @Column(name = "SCREENING_ROOM_SEAT_ID")
    private Long seatId;
    @ManyToOne
    @JoinColumn(name = "SCREENING_ROOM_ID")
    private ScreeningRoom screeningRoom;
    @Column(name = "SEAT_NUMBER")
    private String seatNo;

    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    public ScreeningRoom getScreeningRoom() {
        return screeningRoom;
    }

    public void setScreeningRoom(ScreeningRoom screeningRoom) {
        this.screeningRoom = screeningRoom;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }
}

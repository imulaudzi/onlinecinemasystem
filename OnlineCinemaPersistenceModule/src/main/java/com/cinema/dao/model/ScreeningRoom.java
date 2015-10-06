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
@Table(name = "SCREENING_ROOM")
public class ScreeningRoom {

    @Id
    @SequenceGenerator(name = "SCREENING_ROOM_SEQ", sequenceName = "SCREENING_ROOM_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SCREENING_ROOM_SEQ")
    @Column(name = "SCREENING_ROOM_ID")
    private Long screeningRoomId;
    @Column(name = "CAPACITY")
    private Integer capacity;

    public Long getScreeningRoomId() {
        return screeningRoomId;
    }

    public void setScreeningRoomId(Long screeningRoomId) {
        this.screeningRoomId = screeningRoomId;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}

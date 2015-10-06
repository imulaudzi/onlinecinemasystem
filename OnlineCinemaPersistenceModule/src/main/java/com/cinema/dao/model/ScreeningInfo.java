package com.cinema.dao.model;

import org.joda.time.DateTime;

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
@Table(name = "SCREENING_INFO")
public class ScreeningInfo {

    @Id
    @SequenceGenerator(name = "SCREENING_INFO_SEQ", sequenceName = "SCREENING_INFO_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SCREENING_INFO_SEQ")
    @Column(name = "SCREENING_INFO_ID")
    private Long screeningId;
    @ManyToOne
    @JoinColumn(name = "MOVIE_INFO_ID")
    private MovieInfo movieInfo;
    @ManyToOne
    @JoinColumn(name = "SCREENING_ROOM_ID")
    private ScreeningRoom screeningRoom;
    @Column(name = "SHOWTIMES")
    private DateTime showTimes;
    @Column(name = "ADDMISSION_TIME")
    private DateTime admissionTime;

    public Long getScreeningId() {
        return screeningId;
    }

    public void setScreeningId(Long screeningId) {
        this.screeningId = screeningId;
    }

    public MovieInfo getMovieInfo() {
        return movieInfo;
    }

    public void setMovieInfo(MovieInfo movieInfo) {
        this.movieInfo = movieInfo;
    }

    public ScreeningRoom getScreeningRoom() {
        return screeningRoom;
    }

    public void setScreeningRoom(ScreeningRoom screeningRoom) {
        this.screeningRoom = screeningRoom;
    }

    public DateTime getShowTimes() {
        return showTimes;
    }

    public void setShowTimes(DateTime showTimes) {
        this.showTimes = showTimes;
    }

    public DateTime getAdmissionTime() {
        return admissionTime;
    }

    public void setAdmissionTime(DateTime admissionTime) {
        this.admissionTime = admissionTime;
    }
}

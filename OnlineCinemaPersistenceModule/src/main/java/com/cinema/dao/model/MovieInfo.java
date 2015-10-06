package com.cinema.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Created by ISAAC on 7/21/2015.
 */
@Entity
@Table(name = "MOVIE_INFO")
public class MovieInfo {

    @Id
    @SequenceGenerator(name = "MOVIE_INFO_SEQ", sequenceName = "MOVIE_INFO_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MOVIE_INFO_SEQ")
    @Column(name = "MOVIE_INFO_ID")
    private Long movieId;
    @Column(name = "MOVIE_TITLE")
    private String movieTitle;
    @Column(name = "DIRECTOR")
    private String director;
    @Column(name = "ROLES")
    private String roles;
    @Column(name = "POSTER")
    private String poster;
    @Column(name = "SYNOPSIS")
    private String synopsis;
    @Column(name = "PRICE")
    private BigDecimal price;
    @Column(name = "TRAILER")
    private String trailer;

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }
}

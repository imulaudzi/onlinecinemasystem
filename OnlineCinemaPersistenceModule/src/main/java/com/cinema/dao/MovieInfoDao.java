package com.cinema.dao;

import com.cinema.dao.generic.GenericDao;
import com.cinema.dao.model.MovieInfo;

import java.util.List;

/**
 * Created by Administrator on 10/10/2015.
 */
public interface MovieInfoDao extends GenericDao<MovieInfo, Long> {

    MovieInfo findMovieById(Long movieInfoId);

    List<MovieInfo> findMovieByTitle(String movieTitle);

    List<MovieInfo> findMovieByDirector(String directorName);

    List<MovieInfo> findMovieByStars(String roles);
}

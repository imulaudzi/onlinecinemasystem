package com.cinema.dao;

import com.cinema.dao.generic.GenericDao;
import com.cinema.dao.model.UserInfo;

import java.util.List;

/**
 * Created by Administrator on 9/24/2015.
 */
public interface UserInfoDao extends GenericDao<UserInfo, Long> {

    UserInfo findByUserID(Long userId);

    UserInfo findByIdNumber(String IdNumber);

    List<UserInfo> listAllUsers();

}

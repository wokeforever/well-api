package com.well.wellapicommon.service;

import com.well.wellapicommon.model.entity.User;




/**
 * 用户服务
 *
 * @author yupi
 */
public interface InnerUserService{
    //实际情况要去数据库查是否分配给该用户

    /**
     * 获取
     * @param accessKey
     * @return
     */
    User getInvokeUser(String accessKey);
}

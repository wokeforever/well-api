package com.well.wellapicommon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.well.wellapicommon.model.entity.InterfaceInfo;


/**
* @author 蜗壳
* @description 针对表【interface_info(接口信息)】的数据库操作Service
* @createDate 2023-11-23 15:28:07
*/
public interface InnerInterfaceInfoService  {

    /**
     * 查询接口是否存在
     * @param path
     * @param method
     * @return
     */
    InterfaceInfo getInterfaceInfo(String path,String method);
}

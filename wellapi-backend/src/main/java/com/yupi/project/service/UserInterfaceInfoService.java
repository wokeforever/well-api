package com.yupi.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.well.wellapicommon.model.entity.UserInterfaceInfo;


/**
* @author 蜗壳
* @description 针对表【user_interface_info(接口信息)】的数据库操作Service
* @createDate 2023-11-30 18:11:24
*/
public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {

    void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add);


}

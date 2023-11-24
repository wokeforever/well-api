package com.yupi.project.service;

import com.yupi.project.model.entity.InterfaceInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 蜗壳
* @description 针对表【interface_info(接口信息)】的数据库操作Service
* @createDate 2023-11-23 15:28:07
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {

    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);
}

package com.yupi.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.well.wellapicommon.model.entity.UserInterfaceInfo;

import java.util.List;


/**
* @author 蜗壳
* @description 针对表【user_interface_info(接口信息)】的数据库操作Mapper
* @createDate 2023-11-30 18:11:24
* @Entity com.yupi.project.model.entity.UserInterfaceInfo
*/
public interface UserInterfaceInfoMapper extends BaseMapper<UserInterfaceInfo> {
//    select interfaceInfoId, sum(totalNum) as totalNum
//    from user_interface_info
//    group by interfaceInfoId
//    order by totalNum desc
//    limit 3;
    List<UserInterfaceInfo> listTopInvokeInterfaceInfo(int limit);

}





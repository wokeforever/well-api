package com.yupi.project.service.impl;
import java.util.Date;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.project.common.ErrorCode;
import com.yupi.project.exception.BusinessException;
import com.yupi.project.model.entity.InterfaceInfo;
import com.yupi.project.model.enums.PostGenderEnum;
import com.yupi.project.model.enums.PostReviewStatusEnum;
import com.yupi.project.service.InterfaceInfoService;
import com.yupi.project.mapper.InterfaceInfoMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
* @author 蜗壳
* @description 针对表【interface_info(接口信息)】的数据库操作Service实现
* @createDate 2023-11-23 15:28:07
*/
@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo>
    implements InterfaceInfoService{

    @Override
    public void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add) {



        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String name = interfaceInfo.getName();
        if (add) {
            if (StringUtils.isAnyBlank(name)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR);
            }
        }
        // 创建时，所有参数必须非空

        if (StringUtils.isNotBlank(name) && name.length() > 50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "内容过长");
        }
    }
}





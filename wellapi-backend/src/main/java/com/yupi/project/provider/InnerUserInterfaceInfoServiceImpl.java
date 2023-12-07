package com.yupi.project.provider;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.well.wellapicommon.model.entity.UserInterfaceInfo;
import com.well.wellapicommon.service.InnerUserInterfaceInfoService;
import com.yupi.project.common.ErrorCode;
import com.yupi.project.exception.BusinessException;
import com.yupi.project.service.UserInterfaceInfoService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * @Author：woke
 * @Date：2023/12/5
 */
@DubboService
public class InnerUserInterfaceInfoServiceImpl implements InnerUserInterfaceInfoService {

    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;
    @Override
    public boolean invokeCount(Long userId, Long interfaceId) {
        //这里没有校验存不存在
        if (userId <= 0 || interfaceId <= 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        UpdateWrapper<UserInterfaceInfo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("userId",userId)
                .eq("interfaceInfoId",interfaceId);
        updateWrapper.setSql("leftNum = leftNum - 1,totalNum = totalNum + 1");
        return userInterfaceInfoService.update(updateWrapper);
    }

    @Override
    public UserInterfaceInfo invokeUserLeftNum(Long userId, Long interfaceId) {
        if (userId <= 0 || interfaceId <= 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<UserInterfaceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId",userId)
                .eq("interfaceInfoId",interfaceId);
        return userInterfaceInfoService.getOne(queryWrapper);

    }
}

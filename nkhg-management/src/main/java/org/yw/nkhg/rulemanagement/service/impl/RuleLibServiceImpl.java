package org.yw.nkhg.rulemanagement.service.impl;


import org.jeecg.common.constant.SymbolConstant;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.common.util.MinioUtil;
import org.jeecg.common.util.oConvertUtils;
import org.yw.nkhg.rulemanagement.entity.RuleLib;
import org.yw.nkhg.rulemanagement.mapper.RuleLibMapper;
import org.yw.nkhg.rulemanagement.service.IRuleLibService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 规章制度管理模块
 * @Author: jeecg-boot
 * @Date:   2023-08-28
 * @Version: V1.0
 */
@Service
public class RuleLibServiceImpl extends ServiceImpl<RuleLibMapper, RuleLib> implements IRuleLibService {

    @Override
    public String getFileUrl(String fileName, String bizPath) {
        if (oConvertUtils.isNotEmpty(bizPath)) {
            if(bizPath.contains(SymbolConstant.SPOT_SINGLE_SLASH) || bizPath.contains(SymbolConstant.SPOT_DOUBLE_BACKSLASH)){
                throw new JeecgBootException("上传目录bizPath，格式非法！");
            }
        }
        if(oConvertUtils.isEmpty(bizPath)) {
            bizPath = "/temp";
        }
        return MinioUtil.getObjectUrlDefault(fileName, bizPath);
    }
}

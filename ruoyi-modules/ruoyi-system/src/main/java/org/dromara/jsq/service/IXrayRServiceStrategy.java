package org.dromara.jsq.service;

import org.dromara.common.core.exception.ServiceException;
import org.dromara.common.core.utils.SpringUtils;

public interface IXrayRServiceStrategy {
    String BASE_NAME = "XrayRServiceImpl";

    static IXrayRService getService(String nodeType){
        String beanName = nodeType + BASE_NAME;
        if (!SpringUtils.containsBean(beanName)) {
            throw new ServiceException("授权类型不正确!");
        }
        return SpringUtils.getBean(beanName);
    }
}

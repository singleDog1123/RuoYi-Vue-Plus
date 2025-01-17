package org.dromara.jsq.service;

import org.dromara.jsq.domain.bo.NewV2BoardParamBo;
import org.dromara.jsq.domain.bo.UserTrafficBo;
import org.dromara.jsq.domain.vo.JsqNodeInfoVo;
import org.dromara.jsq.domain.vo.UserTrafficVo;
import org.dromara.jsq.domain.vo.XrayRUserVo;

import java.util.List;

public interface IXrayRService {
    /**
     * /api/v1/server/UniProxy/config 业务处理
     */
    JsqNodeInfoVo getNodeInfo(NewV2BoardParamBo paramBo);

    /**
     * /api/v1/server/UniProxy/user 获取用户列表
     */
    XrayRUserVo getUserList(NewV2BoardParamBo paramBo);

    /**
     * /api/v1/server/UniProxy/push 用户流量上报
     */
    UserTrafficVo userTraffic(List<UserTrafficBo> userTrafficBoList);

    void handUserTraffic(UserTrafficBo userTrafficBo);
}

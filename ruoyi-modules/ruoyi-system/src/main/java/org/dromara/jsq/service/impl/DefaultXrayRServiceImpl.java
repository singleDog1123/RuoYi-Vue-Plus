package org.dromara.jsq.service.impl;

import com.baomidou.lock.annotation.Lock4j;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.constant.CacheConstants;
import org.dromara.common.redis.utils.RedisUtils;
import org.dromara.jsq.domain.JsqUser;
import org.dromara.jsq.domain.JsqUserTraffic;
import org.dromara.jsq.domain.bo.NewV2BoardParamBo;
import org.dromara.jsq.domain.bo.UserTrafficBo;
import org.dromara.jsq.domain.vo.JsqNodeInfoVo;
import org.dromara.jsq.domain.vo.UserTrafficVo;
import org.dromara.jsq.domain.vo.XrayRUserVo;
import org.dromara.jsq.mapper.JsqUserMapper;
import org.dromara.jsq.mapper.JsqUserTrafficMapper;
import org.dromara.jsq.service.IXrayRService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultXrayRServiceImpl implements IXrayRService {
    protected final JsqUserMapper jsqUserMapper;
    protected final JsqUserTrafficMapper jsqUserTrafficMapper;

    /**
     * /api/v1/server/UniProxy/config 业务处理
     */
    @Override
    public JsqNodeInfoVo getNodeInfo(NewV2BoardParamBo paramBo) {
        return null;
    }

    /**
     * /api/v1/server/UniProxy/user 获取用户列表
     */
    @Override
    public XrayRUserVo getUserList(NewV2BoardParamBo paramBo) {
        return null;
    }

    /**
     * /api/v1/server/UniProxy/push 用户流量上报
     */
    @Override
    public UserTrafficVo userTraffic(List<UserTrafficBo> userTrafficBoList) {
        for (UserTrafficBo userTrafficBo : userTrafficBoList) {
            RedisUtils.publish(CacheConstants.USER_TRAFFIC_KEY, userTrafficBo);
        }
        UserTrafficVo userTrafficVo = new UserTrafficVo();
        userTrafficVo.setData(true);
        userTrafficVo.setStatus("SUCCESS");
        userTrafficVo.setMessage("操作成功");
        return userTrafficVo;
    }

    @Lock4j(keys = {"#userTrafficBo.userId"})
    @Override
    public void handUserTraffic(UserTrafficBo userTrafficBo) {
        JsqUserTraffic jsqUserTrafficDo = jsqUserTrafficMapper.selectById(userTrafficBo.getId());
        if (jsqUserTrafficDo == null) {
            JsqUser jsqUser = jsqUserMapper.selectById(userTrafficBo.getUserId());
            jsqUser.setTrafficUp(jsqUser.getTrafficUp() + userTrafficBo.getUpload());
            jsqUser.setTrafficDown(jsqUser.getTrafficDown() + userTrafficBo.getDownload());
            jsqUserMapper.updateById(jsqUser);

            JsqUserTraffic jsqUserTraffic = new JsqUserTraffic();
            jsqUserTraffic.setId(userTrafficBo.getId());
            jsqUserTraffic.setUserId(userTrafficBo.getUserId());
            jsqUserTraffic.setTrafficUp(userTrafficBo.getUpload());
            jsqUserTraffic.setTrafficDown(userTrafficBo.getDownload());
            jsqUserTrafficMapper.insert(jsqUserTraffic);
        }
    }
}

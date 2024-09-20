package org.dromara.jsq.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.constant.GlobalConstants;
import org.dromara.common.core.constant.UserConstants;
import org.dromara.common.redis.utils.RedisUtils;
import org.dromara.jsq.domain.JsqNode;
import org.dromara.jsq.domain.JsqUser;
import org.dromara.jsq.domain.bo.NewV2BoardParamBo;
import org.dromara.jsq.domain.vo.JsqNodeInfoVo;
import org.dromara.jsq.domain.vo.XrayRUserVo;
import org.dromara.jsq.mapper.JsqNodeMapper;
import org.dromara.jsq.mapper.JsqUserMapper;
import org.dromara.jsq.mapper.JsqUserTrafficMapper;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;
import java.util.List;

@Service
public class ShadowsocksXrayRServiceImpl extends DefaultXrayRServiceImpl {
    private final JsqNodeMapper jsqNodeMapper;

    public ShadowsocksXrayRServiceImpl(JsqUserMapper jsqUserMapper, JsqUserTrafficMapper jsqUserTrafficMapper, JsqNodeMapper jsqNodeMapper) {
        super(jsqUserMapper, jsqUserTrafficMapper);
        this.jsqNodeMapper = jsqNodeMapper;
    }


    /**
     * /api/v1/server/UniProxy/config 业务处理
     */
    @Override
    public JsqNodeInfoVo getNodeInfo(NewV2BoardParamBo paramBo) {
        LambdaQueryWrapper<JsqNode> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(JsqNode::getId, paramBo.getNode_id())
            .eq(JsqNode::getAgreement, paramBo.getNode_type());
        JsqNode jsqNode = jsqNodeMapper.selectOne(queryWrapper);
        Assert.notNull(jsqNode, "节点配置不存在");
        // 刷新活跃状态
        RedisUtils.setCacheObject(GlobalConstants.NODE_PING + jsqNode.getId(), StrUtil.EMPTY, Duration.ofMinutes(2));

        return JsqNodeInfoVo.builder()
            .server_port(jsqNode.getServicePort())
            .cipher(jsqNode.getEncryptionAlgorithm())
            .obfs(jsqNode.getConfuse())
            .obfs_settings(new JsqNodeInfoVo.ObfsSetting(jsqNode.getConfusePath(), jsqNode.getConfuseHost()))
            .base_config(new JsqNodeInfoVo.BaseConfig(60, 60)).build();
    }

    /**
     * /api/v1/server/UniProxy/user 获取用户列表
     */
    @Override
    public XrayRUserVo getUserList(NewV2BoardParamBo paramBo) {
        Date currentDate = new Date();
        LambdaQueryWrapper<JsqUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(JsqUser::getStatus, UserConstants.USER_NORMAL)
            .gt(JsqUser::getExpireDate, currentDate);
        List<JsqUser> jsqUsers = jsqUserMapper.selectList(queryWrapper);
        XrayRUserVo xrayRUserVo = new XrayRUserVo();
        List<XrayRUserVo.UserVo> list = jsqUsers.stream()
            .filter(obj -> obj.getTrafficDown() + obj.getTrafficUp() < obj.getTrafficMax())
            .map(obj -> {
                XrayRUserVo.UserVo userVo = new XrayRUserVo.UserVo();
                userVo.setId(obj.getId());
                userVo.setUuid(obj.getToken());
                userVo.setSpeed_limit(obj.getSpeedLimit());
                return userVo;
            }).toList();
        xrayRUserVo.setUsers(list);
        return xrayRUserVo;
    }
}

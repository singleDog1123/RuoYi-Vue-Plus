package org.dromara.jsq.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import org.dromara.common.core.constant.CacheConstants;
import org.dromara.common.core.constant.GlobalConstants;
import org.dromara.common.redis.utils.RedisUtils;
import org.dromara.jsq.domain.JsqNode;
import org.dromara.jsq.domain.bo.NewV2BoardParamBo;
import org.dromara.jsq.domain.vo.JsqNodeInfoVo;
import org.dromara.jsq.domain.vo.XrayRUserVo;
import org.dromara.jsq.mapper.JsqNodeMapper;
import org.dromara.jsq.service.IXrayRService;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
@AllArgsConstructor
public class ShadowsocksXrayRServiceImpl implements IXrayRService {
    private final JsqNodeMapper jsqNodeMapper;

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

        JsqNodeInfoVo jsqNodeInfoVo = JsqNodeInfoVo.builder()
            .server_port(jsqNode.getServicePort())
            .cipher(jsqNode.getEncryptionAlgorithm())
            .obfs(jsqNode.getConfuse())
            .obfs_settings(new JsqNodeInfoVo.ObfsSetting(jsqNode.getConfusePath(), jsqNode.getConfuseHost()))
            .base_config(new JsqNodeInfoVo.BaseConfig(60,60)).build();
        return jsqNodeInfoVo;
    }

    /**
     * /api/v1/server/UniProxy/user 获取用户列表
     */
    @Override
    public List<XrayRUserVo> getUserList(NewV2BoardParamBo paramBo) {
        return List.of();
    }
}

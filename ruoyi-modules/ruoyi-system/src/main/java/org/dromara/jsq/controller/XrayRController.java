package org.dromara.jsq.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import lombok.AllArgsConstructor;
import org.dromara.jsq.domain.bo.NewV2BoardParamBo;
import org.dromara.jsq.domain.bo.UserTrafficBo;
import org.dromara.jsq.domain.vo.JsqNodeInfoVo;
import org.dromara.jsq.domain.vo.UserTrafficVo;
import org.dromara.jsq.domain.vo.XrayRUserVo;
import org.dromara.jsq.service.IXrayRServiceStrategy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SaIgnore
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/server/UniProxy")
public class XrayRController {
    private final IdentifierGenerator identifierGenerator;

    @GetMapping("/config")
    public JsqNodeInfoVo getNodeInfo(@Validated NewV2BoardParamBo paramBo) {
        return IXrayRServiceStrategy.getService(paramBo.getNode_type()).getNodeInfo(paramBo);
    }

    @RequestMapping("/user")
    public XrayRUserVo getUserList(@Validated NewV2BoardParamBo paramBo) {
        return IXrayRServiceStrategy.getService(paramBo.getNode_type()).getUserList(paramBo);
    }

    /**
     * 该接口参数请阅读XrayR源代码
     */
    @RequestMapping("/push")
    public UserTrafficVo reportUserTraffic(@Validated NewV2BoardParamBo paramBo, @RequestBody Map<String, long[]> map) {
        List<UserTrafficBo> userTrafficBos = new ArrayList<>();
        for (String userId : map.keySet()) {
            long[] traffic = map.get(userId);
            UserTrafficBo userTrafficBo = new UserTrafficBo();
            userTrafficBo.setId(identifierGenerator.nextId(userTrafficBo).longValue());
            userTrafficBo.setUserId(Long.valueOf(userId));
            userTrafficBo.setUpload(traffic[0]);
            userTrafficBo.setDownload(traffic[1]);
            userTrafficBo.setNodeId(paramBo.getNode_id());
            userTrafficBo.setToken(paramBo.getToken());
            userTrafficBo.setNodeType(paramBo.getNode_type());
            userTrafficBos.add(userTrafficBo);
        }
        return IXrayRServiceStrategy.getService(paramBo.getNode_type()).userTraffic(userTrafficBos);
    }

}

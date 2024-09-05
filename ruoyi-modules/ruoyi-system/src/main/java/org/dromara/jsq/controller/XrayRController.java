package org.dromara.jsq.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import jakarta.servlet.http.HttpServletRequest;
import org.dromara.common.json.utils.JsonUtils;
import org.dromara.jsq.domain.bo.NewV2BoardParamBo;
import org.dromara.jsq.domain.vo.JsqNodeInfoVo;
import org.dromara.jsq.domain.vo.XrayRUserVo;
import org.dromara.jsq.service.IXrayRServiceStrategy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@SaIgnore
@RestController
@RequestMapping("/api/v1/server/UniProxy")
public class XrayRController {


    @GetMapping("/config")
    public JsqNodeInfoVo getNodeInfo(@Validated NewV2BoardParamBo paramBo) {
        return IXrayRServiceStrategy.getService(paramBo.getNode_type()).getNodeInfo(paramBo);
    }

    @RequestMapping("/user")
    public List<XrayRUserVo> getUserList(@Validated NewV2BoardParamBo paramBo) {
        return IXrayRServiceStrategy.getService(paramBo.getNode_type()).getUserList(paramBo);
    }

    @RequestMapping("/push")
    public JsqNodeInfoVo reportUserTraffic(Map<String, Object> map, HttpServletRequest request) {
        System.out.println("ReportUserTraffic方法开始--------------------");
        System.out.println(JsonUtils.toJsonString(map));
        request.getParameterNames().asIterator().forEachRemaining(name -> System.out.println(name + "=" + request.getParameter(name)));
        System.out.println("ReportUserTraffic方法结束--------------------");
        return null;
    }

}

package org.dromara.jsq.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dromara.jsq.domain.JsqUserTraffic;
import org.dromara.jsq.domain.vo.JsqUserTrafficVo;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;

/**
 * 用户流量使用记录Mapper接口
 *
 * @author lys
 * @date 2024-09-19
 */
@Mapper
public interface JsqUserTrafficMapper extends BaseMapperPlus<JsqUserTraffic, JsqUserTrafficVo> {

}

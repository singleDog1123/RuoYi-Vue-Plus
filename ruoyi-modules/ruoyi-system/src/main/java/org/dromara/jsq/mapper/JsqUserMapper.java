package org.dromara.jsq.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.jsq.domain.JsqUser;
import org.dromara.jsq.domain.vo.JsqUserVo;

/**
 * 加速器用户Mapper接口
 *
 * @author lys
 * @date 2024-09-05
 */
@Mapper
public interface JsqUserMapper extends BaseMapperPlus<JsqUser, JsqUserVo> {

}

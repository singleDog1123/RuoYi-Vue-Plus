package org.dromara.jsq.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dromara.jsq.domain.JsqUser;
import org.dromara.jsq.domain.vo.JsqUserVo;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;

/**
 * 用户Mapper接口
 *
 * @author Lys
 * @date 2024-07-30
 */
@Mapper
public interface JsqUserMapper extends BaseMapperPlus<JsqUser, JsqUserVo> {

}

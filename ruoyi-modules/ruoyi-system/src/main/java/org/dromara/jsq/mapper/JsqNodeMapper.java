package org.dromara.jsq.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dromara.jsq.domain.JsqNode;
import org.dromara.jsq.domain.vo.JsqNodeVo;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;

/**
 * 节点信息Mapper接口
 *
 * @author Lys
 * @date 2024-08-16
 */
@Mapper
public interface JsqNodeMapper extends BaseMapperPlus<JsqNode, JsqNodeVo> {

}

package org.dromara.jsq.service;

import org.dromara.jsq.domain.vo.JsqNodeVo;
import org.dromara.jsq.domain.bo.JsqNodeBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 节点信息Service接口
 *
 * @author Lys
 * @date 2024-08-16
 */
public interface IJsqNodeService {

    /**
     * 查询节点信息
     *
     * @param id 主键
     * @return 节点信息
     */
    JsqNodeVo queryById(Long id);

    /**
     * 分页查询节点信息列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 节点信息分页列表
     */
    TableDataInfo<JsqNodeVo> queryPageList(JsqNodeBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的节点信息列表
     *
     * @param bo 查询条件
     * @return 节点信息列表
     */
    List<JsqNodeVo> queryList(JsqNodeBo bo);

    /**
     * 新增节点信息
     *
     * @param bo 节点信息
     * @return 是否新增成功
     */
    Boolean insertByBo(JsqNodeBo bo);

    /**
     * 修改节点信息
     *
     * @param bo 节点信息
     * @return 是否修改成功
     */
    Boolean updateByBo(JsqNodeBo bo);

    /**
     * 校验并批量删除节点信息信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 开启或关闭节点
     */
    Boolean editOnOff(JsqNodeBo bo);
}

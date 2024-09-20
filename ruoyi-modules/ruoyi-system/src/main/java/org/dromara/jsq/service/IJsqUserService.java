package org.dromara.jsq.service;

import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.jsq.domain.bo.JsqUserBo;
import org.dromara.jsq.domain.vo.JsqUserVo;

import java.util.Collection;
import java.util.List;

/**
 * 加速器用户Service接口
 *
 * @author lys
 * @date 2024-09-05
 */
public interface IJsqUserService {

    /**
     * 查询加速器用户
     *
     * @param id 主键
     * @return 加速器用户
     */
    JsqUserVo queryById(Long id);

    /**
     * 分页查询加速器用户列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 加速器用户分页列表
     */
    TableDataInfo<JsqUserVo> queryPageList(JsqUserBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的加速器用户列表
     *
     * @param bo 查询条件
     * @return 加速器用户列表
     */
    List<JsqUserVo> queryList(JsqUserBo bo);

    /**
     * 新增加速器用户
     *
     * @param bo 加速器用户
     * @return 是否新增成功
     */
    Boolean insertByBo(JsqUserBo bo);

    /**
     * 修改加速器用户
     *
     * @param bo 加速器用户
     * @return 是否修改成功
     */
    Boolean updateByBo(JsqUserBo bo);

    /**
     * 校验并批量删除加速器用户信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}

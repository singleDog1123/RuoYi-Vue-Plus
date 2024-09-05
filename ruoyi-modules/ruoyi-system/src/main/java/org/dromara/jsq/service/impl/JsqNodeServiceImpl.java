package org.dromara.jsq.service.impl;

import org.dromara.common.core.constant.CacheConstants;
import org.dromara.common.core.constant.GlobalConstants;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.common.redis.utils.RedisUtils;
import org.springframework.stereotype.Service;
import org.dromara.jsq.domain.bo.JsqNodeBo;
import org.dromara.jsq.domain.vo.JsqNodeVo;
import org.dromara.jsq.domain.JsqNode;
import org.dromara.jsq.mapper.JsqNodeMapper;
import org.dromara.jsq.service.IJsqNodeService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 节点信息Service业务层处理
 *
 * @author Lys
 * @date 2024-08-16
 */
@RequiredArgsConstructor
@Service
public class JsqNodeServiceImpl implements IJsqNodeService {

    private final JsqNodeMapper baseMapper;
    private final JsqNodeMapper jsqNodeMapper;

    /**
     * 查询节点信息
     *
     * @param id 主键
     * @return 节点信息
     */
    @Override
    public JsqNodeVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询节点信息列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 节点信息分页列表
     */
    @Override
    public TableDataInfo<JsqNodeVo> queryPageList(JsqNodeBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<JsqNode> lqw = buildQueryWrapper(bo);
        Page<JsqNodeVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);

        Collection<String> keys = RedisUtils.keys(GlobalConstants.NODE_PING+"*");
        for (JsqNodeVo record : result.getRecords()) {
            String key = GlobalConstants.NODE_PING + record.getId();
            record.setHealthStatus(keys.contains(key));
        }

        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的节点信息列表
     *
     * @param bo 查询条件
     * @return 节点信息列表
     */
    @Override
    public List<JsqNodeVo> queryList(JsqNodeBo bo) {
        LambdaQueryWrapper<JsqNode> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<JsqNode> buildQueryWrapper(JsqNodeBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<JsqNode> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getName()), JsqNode::getName, bo.getName());
        lqw.eq(StringUtils.isNotBlank(bo.getAddress()), JsqNode::getAddress, bo.getAddress());
        lqw.eq(StringUtils.isNotBlank(bo.getEncryptionAlgorithm()), JsqNode::getEncryptionAlgorithm, bo.getEncryptionAlgorithm());
        lqw.eq(StringUtils.isNotBlank(bo.getConfuse()), JsqNode::getConfuse, bo.getConfuse());
        lqw.eq(bo.getParentId() != null, JsqNode::getParentId, bo.getParentId());
        return lqw;
    }

    /**
     * 新增节点信息
     *
     * @param bo 节点信息
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(JsqNodeBo bo) {
        JsqNode add = MapstructUtils.convert(bo, JsqNode.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改节点信息
     *
     * @param bo 节点信息
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(JsqNodeBo bo) {
        JsqNode update = MapstructUtils.convert(bo, JsqNode.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(JsqNode entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除节点信息信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteByIds(ids) > 0;
    }

    /**
     * 开启或关闭节点
     */
    @Override
    public Boolean editOnOff(JsqNodeBo bo) {
        JsqNode jsqNode = jsqNodeMapper.selectById(bo.getId());
        jsqNode.setShowFlag(bo.getShowFlag());
        return baseMapper.updateById(jsqNode) > 0;
    }
}

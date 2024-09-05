package org.dromara.jsq.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.EncryptUtils;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.dromara.jsq.domain.bo.JsqUserBo;
import org.dromara.jsq.domain.vo.JsqUserVo;
import org.dromara.jsq.domain.JsqUser;
import org.dromara.jsq.mapper.JsqUserMapper;
import org.dromara.jsq.service.IJsqUserService;

import java.util.List;
import java.util.Collection;

/**
 * 用户Service业务层处理
 *
 * @author Lys
 * @date 2024-07-30
 */
@RequiredArgsConstructor
@Service
public class JsqUserServiceImpl implements IJsqUserService {

    private final JsqUserMapper baseMapper;

    /**
     * 查询用户
     *
     * @param id 主键
     * @return 用户
     */
    @Override
    public JsqUserVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询用户列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 用户分页列表
     */
    @Override
    public TableDataInfo<JsqUserVo> queryPageList(JsqUserBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<JsqUser> lqw = buildQueryWrapper(bo);
        Page<JsqUserVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的用户列表
     *
     * @param bo 查询条件
     * @return 用户列表
     */
    @Override
    public List<JsqUserVo> queryList(JsqUserBo bo) {
        LambdaQueryWrapper<JsqUser> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<JsqUser> buildQueryWrapper(JsqUserBo bo) {
        LambdaQueryWrapper<JsqUser> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getAccount()), JsqUser::getAccount, bo.getAccount());
        lqw.eq(bo.getStatus() != null, JsqUser::getStatus, bo.getStatus());
        lqw.eq(bo.getPayStatus() != null, JsqUser::getPayStatus, bo.getPayStatus());
        return lqw;
    }

    /**
     * 新增用户
     *
     * @param bo 用户
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(JsqUserBo bo) {
        JsqUser add = MapstructUtils.convert(bo, JsqUser.class);
        assert add != null;
        add.setPassword(EncryptUtils.md5Base64(add.getPassword()));
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改用户
     *
     * @param bo 用户
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(JsqUserBo bo) {
        JsqUser update = MapstructUtils.convert(bo, JsqUser.class);
        assert update != null;
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(JsqUser entity){
        if (StrUtil.isEmpty(entity.getSsPassword())){
            entity.setSsPassword(UUID.randomUUID().toString(true));
        }
    }

    /**
     * 校验并批量删除用户信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            // 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteByIds(ids) > 0;
    }
}

package org.dromara.jsq.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.web.core.BaseController;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.jsq.domain.vo.JsqUserVo;
import org.dromara.jsq.domain.bo.JsqUserBo;
import org.dromara.jsq.service.IJsqUserService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 加速器用户
 *
 * @author lys
 * @date 2024-09-05
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/jsq/user")
public class JsqUserController extends BaseController {

    private final IJsqUserService jsqUserService;

    /**
     * 查询加速器用户列表
     */
    @SaCheckPermission("jsq:user:list")
    @GetMapping("/list")
    public TableDataInfo<JsqUserVo> list(JsqUserBo bo, PageQuery pageQuery) {
        return jsqUserService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出加速器用户列表
     */
    @SaCheckPermission("jsq:user:export")
    @Log(title = "加速器用户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(JsqUserBo bo, HttpServletResponse response) {
        List<JsqUserVo> list = jsqUserService.queryList(bo);
        ExcelUtil.exportExcel(list, "加速器用户", JsqUserVo.class, response);
    }

    /**
     * 获取加速器用户详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("jsq:user:query")
    @GetMapping("/{id}")
    public R<JsqUserVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(jsqUserService.queryById(id));
    }

    /**
     * 新增加速器用户
     */
    @SaCheckPermission("jsq:user:add")
    @Log(title = "加速器用户", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody JsqUserBo bo) {
        return toAjax(jsqUserService.insertByBo(bo));
    }

    /**
     * 修改加速器用户
     */
    @SaCheckPermission("jsq:user:edit")
    @Log(title = "加速器用户", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody JsqUserBo bo) {
        return toAjax(jsqUserService.updateByBo(bo));
    }

    /**
     * 删除加速器用户
     *
     * @param ids 主键串
     */
    @SaCheckPermission("jsq:user:remove")
    @Log(title = "加速器用户", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(jsqUserService.deleteWithValidByIds(List.of(ids), true));
    }
}

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
 * 用户
 *
 * @author Lys
 * @date 2024-07-30
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/jsq/user")
public class JsqUserController extends BaseController {

    private final IJsqUserService userService;

    /**
     * 查询用户列表
     */
    @SaCheckPermission("jsq:user:list")
    @GetMapping("/list")
    public TableDataInfo<JsqUserVo> list(JsqUserBo bo, PageQuery pageQuery) {
        return userService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出用户列表
     */
    @SaCheckPermission("jsq:user:export")
    @Log(title = "用户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(JsqUserBo bo, HttpServletResponse response) {
        List<JsqUserVo> list = userService.queryList(bo);
        ExcelUtil.exportExcel(list, "用户", JsqUserVo.class, response);
    }

    /**
     * 获取用户详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("jsq:user:query")
    @GetMapping("/{id}")
    public R<JsqUserVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(userService.queryById(id));
    }

    /**
     * 新增用户
     */
    @SaCheckPermission("jsq:user:add")
    @Log(title = "用户", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody JsqUserBo bo) {
        return toAjax(userService.insertByBo(bo));
    }

    /**
     * 修改用户
     */
    @SaCheckPermission("jsq:user:edit")
    @Log(title = "用户", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody JsqUserBo bo) {
        return toAjax(userService.updateByBo(bo));
    }

    /**
     * 删除用户
     *
     * @param ids 主键串
     */
    @SaCheckPermission("jsq:user:remove")
    @Log(title = "用户", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(userService.deleteWithValidByIds(List.of(ids), true));
    }
}

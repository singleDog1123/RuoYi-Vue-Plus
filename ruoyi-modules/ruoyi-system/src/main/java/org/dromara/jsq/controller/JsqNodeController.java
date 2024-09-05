package org.dromara.jsq.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.dromara.common.core.validate.OnOffGroup;
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
import org.dromara.jsq.domain.vo.JsqNodeVo;
import org.dromara.jsq.domain.bo.JsqNodeBo;
import org.dromara.jsq.service.IJsqNodeService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 节点信息
 *
 * @author Lys
 * @date 2024-08-16
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/jsq/node")
public class JsqNodeController extends BaseController {

    private final IJsqNodeService jsqNodeService;

    /**
     * 查询节点信息列表
     */
    @SaCheckPermission("jsq:node:list")
    @GetMapping("/list")
    public TableDataInfo<JsqNodeVo> list(JsqNodeBo bo, PageQuery pageQuery) {
        return jsqNodeService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出节点信息列表
     */
    @SaCheckPermission("jsq:node:export")
    @Log(title = "节点信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(JsqNodeBo bo, HttpServletResponse response) {
        List<JsqNodeVo> list = jsqNodeService.queryList(bo);
        ExcelUtil.exportExcel(list, "节点信息", JsqNodeVo.class, response);
    }

    /**
     * 获取节点信息详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("jsq:node:query")
    @GetMapping("/{id}")
    public R<JsqNodeVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(jsqNodeService.queryById(id));
    }

    /**
     * 新增节点信息
     */
    @SaCheckPermission("jsq:node:add")
    @Log(title = "节点信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody JsqNodeBo bo) {
        return toAjax(jsqNodeService.insertByBo(bo));
    }

    /**
     * 修改节点信息
     */
    @SaCheckPermission("jsq:node:edit")
    @Log(title = "节点信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody JsqNodeBo bo) {
        return toAjax(jsqNodeService.updateByBo(bo));
    }

    /**
     * 删除节点信息
     *
     * @param ids 主键串
     */
    @SaCheckPermission("jsq:node:remove")
    @Log(title = "节点信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(jsqNodeService.deleteWithValidByIds(List.of(ids), true));
    }

    /**
     * 显示或隐藏节点
     */
    @SaCheckPermission("jsq:node:edit")
    @Log(title = "节点信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit(interval = 2000)
    @PutMapping("/editOnOff")
    public R<Boolean> editOnOff(@Validated(OnOffGroup.class) @RequestBody JsqNodeBo bo) {
        return R.ok(jsqNodeService.editOnOff(bo));
    }
}

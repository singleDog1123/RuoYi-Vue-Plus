package org.dromara.jsq.domain.vo;

import java.util.Date;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.common.translation.annotation.Translation;
import org.dromara.common.translation.constant.TransConstant;
import org.dromara.jsq.domain.JsqUser;

import java.io.Serial;
import java.io.Serializable;



/**
 * 加速器用户视图对象 jsq_user
 *
 * @author lys
 * @date 2024-09-05
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = JsqUser.class)
public class JsqUserVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ExcelProperty(value = "用户ID")
    private Long id;

    /**
     * 邀请人ID
     */
    @ExcelProperty(value = "邀请人ID")
    private Long inviteUserId;

    /**
     * 租户编号
     */
    @ExcelProperty(value = "租户编号")
    private String tenantId;

    /**
     * 用户账号
     */
    @ExcelProperty(value = "用户账号")
    private String account;

    /**
     * 邮箱
     */
    @ExcelProperty(value = "邮箱")
    private String email;

    /**
     * 手机号
     */
    @ExcelProperty(value = "手机号")
    private String phoneNumber;

    /**
     * 已使用总流量
     */
    @ExcelProperty(value = "已使用流量")
    @Translation(type = TransConstant.BYTE_TO_G, mapper = "trafficUse")
    private Long trafficUse;

    /**
     * 最大流量
     */
    @ExcelProperty(value = "最大流量")
    @Translation(type = TransConstant.BYTE_TO_G, mapper = "trafficMax")
    private Long trafficMax;

    /**
     * 状态：0正常、1禁用
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_common_status")
    private Integer status;

    /**
     * 过期时间
     */
    @ExcelProperty(value = "过期时间")
    private Date expireDate;

    /**
     * 最后连接时间
     */
    @ExcelProperty(value = "最后连接时间")
    private Date lastConnectionDate;

    /**
     * 支付标记，0未支付，1已支付
     */
    @ExcelProperty(value = "支付标记", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "pay_status")
    private Integer payStatus;


}

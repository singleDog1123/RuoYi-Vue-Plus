package org.dromara.jsq.domain.vo;

import java.util.Date;

import org.dromara.jsq.domain.JsqUser;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 用户视图对象 ym_user
 *
 * @author Lys
 * @date 2024-07-30
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
    @ExcelProperty(value = "")
    private Long id;

    /**
     * 用户账号
     */
    @ExcelProperty(value = "用户账号")
    private String account;

    /**
     * 账号密码md5
     */
    @ExcelProperty(value = "账号密码md5")
    private String password;

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
     * 状态：0正常、1禁用
     */
    @ExcelProperty(value = "状态：0正常、1禁用", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "forbidden_status")
    private Integer status;

    /**
     * 过期时间
     */
    @ExcelProperty(value = "过期时间")
    private Date expireDate;

    /**
     * 最后在线时间
     */
    @ExcelProperty(value = "最后在线时间")
    private Date lastConnectionDate;

    /**
     * 支付标记，0未支付，1已支付
     */
    @ExcelProperty(value = "支付标记，0未支付，1已支付", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "pay_status")
    private Integer payStatus;

    /**
     * 注册时间
     */
    @ExcelProperty(value = "注册时间")
    private Date createTime;


}

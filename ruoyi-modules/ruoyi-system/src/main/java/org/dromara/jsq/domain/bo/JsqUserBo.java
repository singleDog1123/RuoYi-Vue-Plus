package org.dromara.jsq.domain.bo;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;
import java.util.Date;
import org.dromara.jsq.domain.JsqUser;

/**
 * 加速器用户业务对象 jsq_user
 *
 * @author lys
 * @date 2024-09-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = JsqUser.class, reverseConvertGenerate = false)
public class JsqUserBo extends BaseEntity {

    /**
     *
     */
    @NotNull(message = "不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 邀请人ID
     */
    @NotNull(message = "邀请人ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long inviteUserId;

    /**
     * 用户账号
     */
    @NotBlank(message = "用户账号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String account;

    /**
     * 账号密码md5
     */
    @NotBlank(message = "账号密码md5不能为空", groups = { AddGroup.class, EditGroup.class })
    private String password;

    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不能为空", groups = { AddGroup.class, EditGroup.class })
    private String email;

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String phoneNumber;

    /**
     * 状态：0正常、1禁用
     */
    @NotNull(message = "状态：0正常、1禁用不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer status;

    /**
     * 过期时间
     */
    @NotNull(message = "过期时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date expireDate;

    /**
     * 最后连接时间
     */
    @NotNull(message = "最后连接时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date lastConnectionDate;

    /**
     * 支付标记，0未支付，1已支付
     */
    @NotNull(message = "支付标记，0未支付，1已支付不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer payStatus;

    /**
     * 已使用下行流量
     */
    @NotNull(message = "已使用下行流量不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long trafficDown;

    /**
     * 已使用上行流量
     */
    @NotNull(message = "已使用上行流量不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long trafficUp;

    /**
     * 最大流量
     */
    @NotNull(message = "最大流量不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long trafficMax;

    /**
     * 限速设置
     */
    @NotNull(message = "限速设置不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long speedLimit;

    /**
     * XrayR密码
     */
    @NotBlank(message = "XrayR密码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String token;


}

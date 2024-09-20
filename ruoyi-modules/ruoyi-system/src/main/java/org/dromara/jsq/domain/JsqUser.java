package org.dromara.jsq.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;

import java.io.Serial;

/**
 * 加速器用户对象 jsq_user
 *
 * @author lys
 * @date 2024-09-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("jsq_user")
public class JsqUser extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 邀请人ID
     */
    private Long inviteUserId;

    /**
     * 用户账号
     */
    private String account;

    /**
     * 账号密码md5
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phoneNumber;

    /**
     * 状态：0正常、1禁用
     */
    private Integer status;

    /**
     * 过期时间
     */
    private Date expireDate;

    /**
     * 最后连接时间
     */
    private Date lastConnectionDate;

    /**
     * 支付标记，0未支付，1已支付
     */
    private Integer payStatus;

    /**
     * 已使用下行流量
     */
    private Long trafficDown;

    /**
     * 已使用上行流量
     */
    private Long trafficUp;

    /**
     * 最大流量
     */
    private Long trafficMax;

    /**
     * 限速设置
     */
    private Long speedLimit;

    /**
     * XrayR密码
     */
    private String token;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableLogic
    private String delFlag;


}

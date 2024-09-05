package org.dromara.jsq.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;

import java.io.Serial;

/**
 * 用户对象 ym_user
 *
 * @author Lys
 * @date 2024-07-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("jsq_user")
public class JsqUser extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(value = "id")
    private Long id;

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
     * 最后在线时间
     */
    private Date lastConnectionDate;

    /**
     * 支付标记，0未支付，1已支付
     */
    private Integer payStatus;

    /**
     * XrayR密码
     */
    private String ssPassword;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableLogic
    private String delFlag;


}

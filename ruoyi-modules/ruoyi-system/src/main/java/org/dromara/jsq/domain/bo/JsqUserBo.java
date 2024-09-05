package org.dromara.jsq.domain.bo;

import org.dromara.common.core.validate.OnOffGroup;
import org.dromara.jsq.domain.JsqUser;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;
import java.util.Date;

/**
 * 用户业务对象 jsq_user
 *
 * @author Lys
 * @date 2024-07-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = JsqUser.class, reverseConvertGenerate = false)
public class JsqUserBo extends BaseEntity {

    /**
     *
     */
    @NotNull(message = "不能为空", groups = { EditGroup.class,OnOffGroup.class })
    private Long id;

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
    @NotNull(message = "状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer status;

    /**
     * 过期时间
     */
    @NotNull(message = "过期时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date expireDate;

    /**
     * 最后在线时间
     */
    @NotNull(message = "最后在线时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date lastConnectionDate;

    /**
     * 支付标记，0未支付，1已支付
     */
    @NotNull(message = "支付状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer payStatus;


    /**
     * 显示或隐藏
     */
    @NotNull(message = "显示状态不能为空", groups = { OnOffGroup.class })
    private Integer showFlag;
}

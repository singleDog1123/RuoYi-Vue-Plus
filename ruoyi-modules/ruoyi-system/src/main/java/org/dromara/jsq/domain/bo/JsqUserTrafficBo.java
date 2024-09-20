package org.dromara.jsq.domain.bo;

import org.dromara.jsq.domain.JsqUserTraffic;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 用户流量使用记录业务对象 jsq_user_traffic
 *
 * @author lys
 * @date 2024-09-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = JsqUserTraffic.class, reverseConvertGenerate = false)
public class JsqUserTrafficBo extends BaseEntity {

    /**
     *
     */
    @NotNull(message = "不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long userId;

    /**
     * 已使用上行流量
     */
    @NotNull(message = "已使用上行流量不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long trafficUp;

    /**
     * 已使用下行流量
     */
    @NotNull(message = "已使用下行流量不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long trafficDown;


}

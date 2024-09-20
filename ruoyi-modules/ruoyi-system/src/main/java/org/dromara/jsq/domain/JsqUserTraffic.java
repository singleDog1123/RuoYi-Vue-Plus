package org.dromara.jsq.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 用户流量使用记录对象 jsq_user_traffic
 *
 * @author lys
 * @date 2024-09-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("jsq_user_traffic")
public class JsqUserTraffic extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 已使用上行流量
     */
    private Long trafficUp;

    /**
     * 已使用下行流量
     */
    private Long trafficDown;


}

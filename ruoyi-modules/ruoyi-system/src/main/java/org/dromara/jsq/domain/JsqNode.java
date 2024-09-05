package org.dromara.jsq.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;

import java.io.Serial;

/**
 * 节点信息对象 jsq_node
 *
 * @author Lys
 * @date 2024-08-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("jsq_node")
public class JsqNode extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 协议类型
     */
    private String agreement;

    /**
     * 节点名称
     */
    private String name;

    /**
     * 流量倍率
     */
    private BigDecimal ratio;

    /**
     * 节点地址
     */
    private String address;

    /**
     * 连接端口
     */
    private Integer connectionPort;

    /**
     * 服务端口
     */
    private Integer servicePort;

    /**
     * 加密算法
     */
    private String encryptionAlgorithm;

    /**
     * 父节点
     */
    private Long parentId;

    /**
     * 混淆类型
     */
    private String confuse;

    /**
     * 混淆路径
     */
    private String confusePath;

    /**
     * 混淆主机
     */
    private String confuseHost;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableLogic
    private String delFlag;

    /**
     * 显示或隐藏
     */
    private Integer showFlag;
}

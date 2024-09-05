package org.dromara.jsq.domain.bo;

import org.dromara.common.core.validate.OnOffGroup;
import org.dromara.jsq.domain.JsqNode;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

/**
 * 节点信息业务对象 jsq_node
 *
 * @author Lys
 * @date 2024-08-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = JsqNode.class, reverseConvertGenerate = false)
public class JsqNodeBo extends BaseEntity {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = { EditGroup.class, OnOffGroup.class })
    private Long id;

    /**
     * 节点名称
     */
    @NotBlank(message = "节点名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;


    /**
     * 协议类型
     */
    @NotBlank(message = "协议类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String agreement;

    /**
     * 流量倍率
     */
    @NotNull(message = "流量倍率不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal ratio;

    /**
     * 节点地址
     */
    @NotBlank(message = "节点地址不能为空", groups = { AddGroup.class, EditGroup.class })
    private String address;

    /**
     * 连接端口
     */
    @NotNull(message = "连接端口不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer connectionPort;

    /**
     * 服务端口
     */
    @NotNull(message = "服务端口不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer servicePort;

    /**
     * 加密算法
     */
    @NotBlank(message = "加密算法不能为空", groups = { AddGroup.class, EditGroup.class })
    private String encryptionAlgorithm;

    /**
     * 混淆
     */
    @NotBlank(message = "混淆不能为空", groups = { AddGroup.class, EditGroup.class })
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
     * 父节点
     */
    @NotNull(message = "父节点不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long parentId;


    /**
     * 显示或隐藏
     */
    @NotNull(message = "显示状态不能为空", groups = { OnOffGroup.class })
    private Integer showFlag;
}

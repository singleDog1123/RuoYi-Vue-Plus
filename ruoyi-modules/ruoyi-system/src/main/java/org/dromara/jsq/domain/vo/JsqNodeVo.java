package org.dromara.jsq.domain.vo;

import java.math.BigDecimal;
import org.dromara.jsq.domain.JsqNode;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;



/**
 * 节点信息视图对象 jsq_node
 *
 * @author Lys
 * @date 2024-08-16
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = JsqNode.class)
public class JsqNodeVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long id;

    /**
     * 节点名称
     */
    @ExcelProperty(value = "节点名称")
    private String name;

    /**
     * 协议类型
     */
    @ExcelProperty(value = "协议类型")
    private String agreement;

    /**
     * 流量倍率
     */
    @ExcelProperty(value = "流量倍率")
    private BigDecimal ratio;

    /**
     * 节点地址
     */
    @ExcelProperty(value = "节点地址")
    private String address;

    /**
     * 连接端口
     */
    @ExcelProperty(value = "连接端口")
    private Long connectionPort;

    /**
     * 服务端口
     */
    @ExcelProperty(value = "服务端口")
    private Long servicePort;

    /**
     * 加密算法
     */
    @ExcelProperty(value = "加密算法")
    private String encryptionAlgorithm;

    /**
     * 混淆
     */
    @ExcelProperty(value = "混淆")
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
    private Long parentId;

    /**
     * 显示或隐藏
     */
    private Integer showFlag;

    /**
     * 连接状态
     */
    private Boolean healthStatus;
}

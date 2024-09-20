package org.dromara.jsq.domain.vo;

import org.dromara.jsq.domain.JsqUserTraffic;
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
 * 用户流量使用记录视图对象 jsq_user_traffic
 *
 * @author lys
 * @date 2024-09-19
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = JsqUserTraffic.class)
public class JsqUserTrafficVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * 用户ID
     */
    @ExcelProperty(value = "用户ID")
    private Long userId;

    /**
     * 已使用上行流量
     */
    @ExcelProperty(value = "已使用上行流量")
    private Long trafficUp;

    /**
     * 已使用下行流量
     */
    @ExcelProperty(value = "已使用下行流量")
    private Long trafficDown;


}

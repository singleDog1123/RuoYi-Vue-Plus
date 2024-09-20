package org.dromara.common.translation.core.impl;

import lombok.AllArgsConstructor;
import org.dromara.common.translation.annotation.TranslationType;
import org.dromara.common.translation.constant.TransConstant;
import org.dromara.common.translation.core.TranslationInterface;

import java.math.BigDecimal;
import java.math.RoundingMode;


@AllArgsConstructor
@TranslationType(type = TransConstant.BYTE_TO_G)
public class ByteToGTranslationImpl implements TranslationInterface<BigDecimal> {
    // 常量，1GB = 1024^3 字节
    private static final long BYTES_IN_GB = 1024L * 1024L * 1024L;

    /**
     * 将字节转换为G
     * @param key   需要被翻译的键(不为空)
     * @param other 其他参数
     * @return 返回键对应的值
     */
    @Override
    public BigDecimal translation(Object key, String other) {
        if (key instanceof Long bytes) {
            BigDecimal bytesDecimal = new BigDecimal(bytes);
            return bytesDecimal.divide(new BigDecimal(BYTES_IN_GB), 2, RoundingMode.UP);
        }
        return null;
    }
}

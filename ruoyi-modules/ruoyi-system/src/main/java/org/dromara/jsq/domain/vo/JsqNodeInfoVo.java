package org.dromara.jsq.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * 为了遵循v2board接口文档，此处属性命名无法更改
 * <p>
 *     注释多为机翻
 * </p>
 */
@Data
@Builder
public class JsqNodeInfoVo {
    private Integer server_port;
    private String cipher;
    private String obfs;
    private ObfsSetting obfs_settings;
    private BaseConfig base_config;

    @Data
    @AllArgsConstructor
    public static class BaseConfig {
        private Integer push_interval;
        private Integer pull_interval;
    }

    @Data
    @AllArgsConstructor
    public static class ObfsSetting {
        private String path;
        private String host;
    }
}

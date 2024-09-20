package org.dromara.jsq.domain.vo;

import lombok.Data;

/**
 * XrayR返回值规范
 */
@Data
public class UserTrafficVo {
    private String status;
    private String message;
    private boolean data;
    private String error;
}

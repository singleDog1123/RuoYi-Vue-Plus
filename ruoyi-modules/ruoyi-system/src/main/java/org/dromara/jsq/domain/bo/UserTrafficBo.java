package org.dromara.jsq.domain.bo;

import lombok.Data;

@Data
public class UserTrafficBo {
    private Long id;
    private Long userId;
    private Long upload;
    private Long download;
    private String nodeType;
    private Long nodeId;
    private String token;
}

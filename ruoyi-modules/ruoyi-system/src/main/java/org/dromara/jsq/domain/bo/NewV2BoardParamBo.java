package org.dromara.jsq.domain.bo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NewV2BoardParamBo {
    @NotEmpty(message = "节点类型不能为空")
    private String node_type;

    @NotNull(message = "节点ID不能为空")
    private Long node_id;

    @NotEmpty(message = "授权Token不能为空")
    private String token;
}

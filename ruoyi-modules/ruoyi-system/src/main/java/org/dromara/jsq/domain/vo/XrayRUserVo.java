package org.dromara.jsq.domain.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.dromara.common.json.handler.LongNumberSerializer;

import java.util.List;

@Data
public class XrayRUserVo {
    private List<UserVo> users;

    @Data
    public static class UserVo {
        @JsonSerialize(using = LongNumberSerializer.class)
        private Long id;
        private String uuid;
        private Long speed_limit;
    }
}

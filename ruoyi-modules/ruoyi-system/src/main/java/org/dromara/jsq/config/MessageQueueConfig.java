package org.dromara.jsq.config;

import jakarta.annotation.PostConstruct;
import org.dromara.common.core.constant.CacheConstants;
import org.dromara.common.redis.utils.RedisUtils;
import org.dromara.jsq.domain.bo.UserTrafficBo;
import org.dromara.jsq.service.IXrayRServiceStrategy;
import org.springframework.context.annotation.Configuration;

/**
 * 消息队列配置
 */
@Configuration
public class MessageQueueConfig {

    /**
     * 消费用户流量
     */
    @PostConstruct
    private void consumeUserTraffic() {
        RedisUtils.subscribe(CacheConstants.USER_TRAFFIC_KEY, UserTrafficBo.class,
            msg -> IXrayRServiceStrategy.getService(msg.getNodeType()).handUserTraffic(msg));
    }
}

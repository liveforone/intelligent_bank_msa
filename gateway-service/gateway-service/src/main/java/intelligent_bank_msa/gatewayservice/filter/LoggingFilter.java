package intelligent_bank_msa.gatewayservice.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class LoggingFilter extends AbstractGatewayFilterFactory<LoggingFilter.Config> {

    public LoggingFilter() {
        super(Config.class);
    }

    @Data
    public static class Config {
        //configuration 존재하면 넣기
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return new OrderedGatewayFilter((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("Logging filter base message: {}", config.getBaseMessage());

            if (config.isPreLogger()) {
                log.info("Logging Pre filter: request id -> {}", request.getId());
            }

            //custom post filter
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                        if (config.isPostLogger()) {
                            log.info("Logging Post filter: response code -> {}", response.getStatusCode());
                        }
                    })
            );
        }, Ordered.HIGHEST_PRECEDENCE);
    }
}

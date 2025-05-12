package br.com.picpay.application.config;

import br.com.picpay.application.infrastructure.client.FeignAuthorizerClient;
import br.com.picpay.ports.AuthorizerClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "br.com.picpay.application.infrastructure.client")
public class FeignClientConfig {

    @Bean
    public AuthorizerClient authorizerClient(FeignAuthorizerClient feignAuthorizerClient) {
        return feignAuthorizerClient;
    }
}

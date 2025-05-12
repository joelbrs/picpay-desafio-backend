package br.com.picpay.application.infrastructure.client.config;

import br.com.picpay.application.infrastructure.client.decoder.UnauthorizedClientErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthorizerClientConfig {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new UnauthorizedClientErrorDecoder();
    }
}

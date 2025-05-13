package br.com.picpay.application.infrastructure.client;

import br.com.picpay.application.infrastructure.client.config.ClientConfig;
import br.com.picpay.exception.ExternalClientException;
import br.com.picpay.ports.AuthorizerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
    name = "authorizer",
    url = "${client.authorizer.url}",
    path = "${client.authorizer.path}",
    configuration = ClientConfig.class
)
public interface FeignAuthorizerClient extends AuthorizerClient {

    @GetMapping("/authorize")
    void authorize() throws ExternalClientException;
}

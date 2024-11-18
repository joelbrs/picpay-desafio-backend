package br.com.joelf.picpay.infraestructure.repositories.clients.authorizer;

import br.com.joelf.picpay.infraestructure.repositories.clients.authorizer.model.AuthorizationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "authorizer",
        url = "${authorizer.url}",
        path = "${authorizer.path}"
)
public interface AuthorizerClient {

    @GetMapping("/authorize")
    AuthorizationResponse authorize();
}

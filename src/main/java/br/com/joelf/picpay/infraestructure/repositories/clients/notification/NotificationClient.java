package br.com.joelf.picpay.infraestructure.repositories.clients.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "notification",
        url = "${notification.url}",
        path = "${notification.path}"
)
public interface NotificationClient {

    @PostMapping("/notify")
    void toNotify();
}

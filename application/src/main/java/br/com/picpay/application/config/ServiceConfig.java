package br.com.picpay.application.config;

import br.com.picpay.factories.TransferServiceFactory;
import br.com.picpay.ports.AccountRepository;
import br.com.picpay.ports.AuthorizerClient;
import br.com.picpay.ports.TransferMessaging;
import br.com.picpay.ports.TransferRepository;
import br.com.picpay.service.TransferService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public TransferService transferService(
        AccountRepository accountRepository,
        TransferRepository transferRepository,
        TransferMessaging transferMessaging,
        AuthorizerClient authorizerClient
    ) {
        return TransferServiceFactory.create(accountRepository, transferRepository, transferMessaging, authorizerClient);
    }
}

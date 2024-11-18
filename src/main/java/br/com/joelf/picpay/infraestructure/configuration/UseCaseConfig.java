package br.com.joelf.picpay.infraestructure.configuration;

import br.com.joelf.picpay.application.dataprovider.AccountDataProvider;
import br.com.joelf.picpay.application.dataprovider.PublishTransferDataProvider;
import br.com.joelf.picpay.application.usecases.MakeTransferUseCaseImpl;
import br.com.joelf.picpay.application.usecases.ProcessTransferUseCaseImpl;
import br.com.joelf.picpay.application.usecases.ValidatePayeeUseCaseImpl;
import br.com.joelf.picpay.application.usecases.ValidatePayerBalanceUseCaseImpl;
import br.com.joelf.picpay.domain.usecases.*;
import br.com.joelf.picpay.infraestructure.repositories.clients.authorizer.AuthorizerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public MakeTransferUseCase makeTransferUseCase(
            ValidatePayerBalanceUseCase validatePayerBalanceUseCase,
            ValidatePayeeUseCase validatePayeeUseCase,
            PublishTransferDataProvider publishTransferDataProvider
    ) {
        return new MakeTransferUseCaseImpl(
                validatePayerBalanceUseCase,
                validatePayeeUseCase,
                publishTransferDataProvider
        );
    }

    @Bean
    public ValidatePayeeUseCase validatePayeeUseCase(
            AccountDataProvider accountDataProvider
    ) {
        return new ValidatePayeeUseCaseImpl(
                accountDataProvider
        );
    }

    @Bean
    public ValidatePayerBalanceUseCase validatePayerBalanceUseCase(
            AccountDataProvider accountDataProvider
    ) {
        return new ValidatePayerBalanceUseCaseImpl(
                accountDataProvider
        );
    }

    @Bean
    public ProcessTransferUseCase processTransferUseCase(
            SendNotificationUseCase sendNotificationUseCase,
            AuthorizerClient authorizerClient,
            AccountDataProvider accountDataProvider
    ) {
        return new ProcessTransferUseCaseImpl(
                sendNotificationUseCase,
                authorizerClient,
                accountDataProvider
        );
    }
}

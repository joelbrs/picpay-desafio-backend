package br.com.joelf.picpay.infraestructure.configuration;

import br.com.joelf.picpay.application.dataprovider.AccountDataProvider;
import br.com.joelf.picpay.application.dataprovider.PublishNotificationDataProvider;
import br.com.joelf.picpay.application.dataprovider.PublishTransferDataProvider;
import br.com.joelf.picpay.application.dataprovider.UserDataProvider;
import br.com.joelf.picpay.application.usecases.*;
import br.com.joelf.picpay.domain.usecases.*;
import br.com.joelf.picpay.infraestructure.repositories.clients.authorizer.AuthorizerClient;
import br.com.joelf.picpay.infraestructure.repositories.clients.notification.NotificationClient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    @Bean
    public SendNotificationUseCase sendNotificationUseCase(
            @Value("${amqp.max-retries}") Integer maxRetries,
            PublishNotificationDataProvider publishNotificationDataProvider,
            NotificationClient notificationClient
    ) {
        return new SendNotificationUseCaseImpl(
                maxRetries,
                publishNotificationDataProvider,
                notificationClient
        );
    }

    @Bean
    public SignUpUseCase signUpUseCase(
            ModelMapper modelMapper,
            UserDataProvider userDataProvider,
            PasswordEncoder passwordEncoder,
            AccountDataProvider accountDataProvider
    ) {
        return new SignUpUseCaseImpl(
                modelMapper,
                userDataProvider,
                accountDataProvider,
                passwordEncoder
        );
    }
}

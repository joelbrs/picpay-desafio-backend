package br.com.joelf.picpay.infraestructure.configuration;

import br.com.joelf.picpay.application.dataprovider.AccountDataProvider;
import br.com.joelf.picpay.application.dataprovider.PublishNotificationDataProvider;
import br.com.joelf.picpay.application.dataprovider.PublishTransferDataProvider;
import br.com.joelf.picpay.application.dataprovider.UserDataProvider;
import br.com.joelf.picpay.infraestructure.dataproviders.AccountDataProviderImpl;
import br.com.joelf.picpay.infraestructure.dataproviders.PublishNotificationDataProviderImpl;
import br.com.joelf.picpay.infraestructure.dataproviders.PublishTransferDataProviderImpl;
import br.com.joelf.picpay.infraestructure.dataproviders.UserDataProviderImpl;
import br.com.joelf.picpay.infraestructure.repositories.postgres.AccountRepository;
import br.com.joelf.picpay.infraestructure.repositories.postgres.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataProviderConfig {

    @Bean
    public PublishTransferDataProvider publishTransferDataProvider(
            @Qualifier("transferQueue") Queue queue,
            RabbitTemplate rabbitTemplate
    ) {
        return new PublishTransferDataProviderImpl(
                queue, rabbitTemplate
        );
    }

    @Bean
    public AccountDataProvider accountDataProvider(
            ModelMapper modelMapper,
            AccountRepository repository
    ) {
        return new AccountDataProviderImpl(
                modelMapper, repository
        );
    }

    @Bean
    public PublishNotificationDataProvider publishNotificationDataProvider(
            @Qualifier("notificationQueue") Queue queue,
            RabbitTemplate rabbitTemplate
    ) {
        return new PublishNotificationDataProviderImpl(
                queue, rabbitTemplate
        );
    }

    @Bean
    public UserDataProvider userDataProvider(
            ModelMapper modelMapper,
            UserRepository userRepository
    ) {
        return new UserDataProviderImpl(
                modelMapper, userRepository
        );
    }
}

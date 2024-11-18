package br.com.joelf.picpay.infraestructure.configuration;

import br.com.joelf.picpay.application.dataprovider.PublishTransferDataProvider;
import br.com.joelf.picpay.infraestructure.dataproviders.PublishTransferDataProviderImpl;
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
}

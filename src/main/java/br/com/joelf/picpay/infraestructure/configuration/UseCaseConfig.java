package br.com.joelf.picpay.infraestructure.configuration;

import br.com.joelf.picpay.application.dataprovider.AccountDataProvider;
import br.com.joelf.picpay.application.dataprovider.PublishTransferDataProvider;
import br.com.joelf.picpay.application.usecases.MakeTransferUseCaseImpl;
import br.com.joelf.picpay.application.usecases.ValidatePayeeUseCaseImpl;
import br.com.joelf.picpay.application.usecases.ValidatePayerBalanceUseCaseImpl;
import br.com.joelf.picpay.domain.usecases.MakeTransferUseCase;
import br.com.joelf.picpay.domain.usecases.ValidatePayeeUseCase;
import br.com.joelf.picpay.domain.usecases.ValidatePayerBalanceUseCase;
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
}

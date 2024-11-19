package br.com.joelf.picpay.infraestructure.dataproviders;

import br.com.joelf.picpay.application.dataprovider.AccountDataProvider;
import br.com.joelf.picpay.domain.entities.Account;
import br.com.joelf.picpay.infraestructure.repositories.postgres.AccountRepository;
import br.com.joelf.picpay.infraestructure.repositories.postgres.domain.PgAccount;
import br.com.joelf.picpay.infraestructure.repositories.postgres.domain.PgUser;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
public class AccountDataProviderImpl implements AccountDataProvider {

    private final ModelMapper modelMapper;
    private final AccountRepository repository;

    @Override
    public boolean hasBalance(UUID accountId, BigDecimal value) {
        return repository.hasBalance(accountId, value);
    }

    @Override
    public Account findById(UUID accountId) {
        PgAccount account = repository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));
        return modelMapper.map(account, Account.class);
    }

    @Override
    public void updateBalance(UUID userId, BigDecimal value) {
        repository.updateBalance(userId, value);
    }

    @Override
    public Account create(Account account) {
        PgAccount pgAccount = modelMapper.map(account, PgAccount.class);
        pgAccount.setUser(modelMapper.map(account.getUser(), PgUser.class));
        pgAccount.setBalance(BigDecimal.ZERO);

        return modelMapper.map(repository.save(pgAccount), Account.class);
    }
}

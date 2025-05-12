package br.com.picpay.ports;

import br.com.picpay.domain.enums.AccountType;

public interface AccountRepository {
    AccountType findAccountType(Long id);
}

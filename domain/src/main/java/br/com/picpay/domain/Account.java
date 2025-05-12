package br.com.picpay.domain;

import br.com.picpay.domain.enums.AccountType;
import lombok.Data;

@Data
public class Account {
    private Long id;
    private Long userId;
    private AccountType accountType;
}

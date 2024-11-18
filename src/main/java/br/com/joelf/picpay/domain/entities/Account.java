package br.com.joelf.picpay.domain.entities;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class Account {
    private UUID id;
    private User user;
    private BigDecimal balance;
}

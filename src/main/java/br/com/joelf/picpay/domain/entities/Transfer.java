package br.com.joelf.picpay.domain.entities;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class Transfer {
    private UUID payer;
    private UUID payee;
    private BigDecimal value;
}

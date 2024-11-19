package br.com.joelf.picpay.domain.entities;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private UUID id;
    private User user;
    private BigDecimal balance;
}

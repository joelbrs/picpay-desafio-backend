package br.com.joelf.picpay.domain.usecases;

import java.math.BigDecimal;
import java.util.UUID;

public interface ValidatePayerBalance {
    boolean execute(UUID payerId, BigDecimal value);
}

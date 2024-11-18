package br.com.joelf.picpay.application.dataprovider;

import java.math.BigDecimal;
import java.util.UUID;

public interface AccountDataProvider {
    boolean hasBalance(UUID userId, BigDecimal value);
}

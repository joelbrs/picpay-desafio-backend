package br.com.picpay.application.presentation.dtos.transfer;

import br.com.picpay.domain.Transfer;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateTransferDto(
    @NotNull
    Long payer,

    @NotNull
    Long payee,

    @Positive
    BigDecimal amount,

    @NotNull
    UUID idempotencyId
) {
    public Transfer toDomain() {
        return Transfer.builder()
            .payer(payer)
            .payee(payee)
            .amount(amount)
            .idempotencyId(idempotencyId)
            .build();
    }
}

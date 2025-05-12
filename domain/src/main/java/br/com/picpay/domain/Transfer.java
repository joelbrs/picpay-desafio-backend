package br.com.picpay.domain;

import br.com.picpay.domain.enums.TransferStatus;

import java.math.BigDecimal;
import java.util.UUID;

public class Transfer {
    private Long id;
    private Long payer;
    private Long payee;
    private BigDecimal amount;
    private UUID idempotencyId;
    private TransferStatus status;
}

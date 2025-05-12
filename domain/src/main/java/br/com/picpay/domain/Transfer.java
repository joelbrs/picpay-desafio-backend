package br.com.picpay.domain;

import br.com.picpay.domain.enums.TransferStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transfer {
    private Long id;
    private Long payer;
    private Long payee;
    private BigDecimal amount;
    private UUID idempotencyId;
    private TransferStatus status;
}

package br.com.picpay.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TransferStatus {
    PROCESSING("EM_PROCESSAMENTO"), COMPLETED("PROCESSADA_SUCESSO"), ERROR("ERRO_PROCESSAMENTO");

    private final String value;
}

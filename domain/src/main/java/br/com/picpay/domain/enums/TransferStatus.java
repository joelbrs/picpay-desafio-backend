package br.com.picpay.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TransferStatus {
    PROCESSING("PROCESSING"), COMPLETED("COMPLETED"), ERROR("ERROR");

    private final String value;
}

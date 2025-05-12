package br.com.picpay.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AccountType {
    SHOPKEEPER("LOJISTA"), COMMON("COMUM");

    private final String value;
}

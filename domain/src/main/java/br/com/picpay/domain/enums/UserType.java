package br.com.picpay.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserType {
    SHOPKEEPER("LOJISTA"), COMMON("COMUM");

    private final String value;
}

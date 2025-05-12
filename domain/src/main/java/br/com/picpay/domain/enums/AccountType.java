package br.com.picpay.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AccountType {
    SHOPKEEPER("LOJISTA"), COMMON("COMUM");

    private final String value;

    public static AccountType fromValue(String value) {
        for (AccountType type : AccountType.values()) {
            if (type.getValue().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid AccountType value: " + value);
    }
}

package br.com.picpay.domain.enums;

public enum UserType {
    SHOPKEEPER("LOJISTA"), COMMON("COMUM");

    private final String value;

    UserType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

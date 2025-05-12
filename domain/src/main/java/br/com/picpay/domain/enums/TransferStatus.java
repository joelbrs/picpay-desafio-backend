package br.com.picpay.domain.enums;

public enum TransferStatus {
    PROCESSING("PROCESSING"), COMPLETED("COMPLETED"), ERROR("ERROR");

    private final String value;

    TransferStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

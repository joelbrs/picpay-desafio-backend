package br.com.joelf.picpay.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserType {
    COMUM("COMUM"),
    LOJISTA("LOJISTA");

    private final String code;
}

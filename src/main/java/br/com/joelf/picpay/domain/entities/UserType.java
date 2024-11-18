package br.com.joelf.picpay.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@AllArgsConstructor
public enum UserType implements GrantedAuthority {
    COMUM("COMUM"),
    LOJISTA("LOJISTA");

    private final String code;

    public String getAuthority() {
        return code;
    }
}

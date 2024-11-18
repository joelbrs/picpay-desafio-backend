package br.com.joelf.picpay.domain.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TokenDto {
    private String token;
    private Long expirationTime;
}

package br.com.joelf.picpay.domain.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SignInDto {
    @NotBlank
    private String cpfCnpj;

    @NotBlank
    @Min(8)
    private String password;
}

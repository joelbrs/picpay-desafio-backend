package br.com.joelf.picpay.domain.dtos;

import br.com.joelf.picpay.domain.entities.UserType;
import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class SignUpDto {

    @NotBlank
    @Size(min = 11, max = 14)
    private String cpfCnpj;

    @NotBlank
    private String name;

    @Email
    private String email;

    @NotBlank
    @Min(8)
    private String password;

    @NotBlank
    @Min(8)
    private String passwordConfirmation;

    @NotNull
    private UserType type;

    public boolean validate() {
        return password.equals(passwordConfirmation);
    }
}

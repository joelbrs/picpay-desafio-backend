package br.com.joelf.picpay.domain.dtos;

import br.com.joelf.picpay.domain.entities.UserType;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserDto {
    private UUID id;
    private String cpfCnpj;
    private String name;
    private UserType type;
}

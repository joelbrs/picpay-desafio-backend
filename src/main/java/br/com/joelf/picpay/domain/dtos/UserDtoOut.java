package br.com.joelf.picpay.domain.dtos;

import br.com.joelf.picpay.domain.entities.UserType;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UserDtoOut {
    private UUID id;
    private String cpfCnpj;
    private String name;
    private UserType type;
}

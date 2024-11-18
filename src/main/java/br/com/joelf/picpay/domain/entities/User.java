package br.com.joelf.picpay.domain.entities;

import lombok.Getter;

import java.util.UUID;

@Getter
public class User {
    private UUID id;
    private String name;
    private String email;
    private String cpfCnpj;
    private String password;
    private UserType type;
    private Account account;
}

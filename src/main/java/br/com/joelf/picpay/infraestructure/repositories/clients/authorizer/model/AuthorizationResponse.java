package br.com.joelf.picpay.infraestructure.repositories.clients.authorizer.model;

import lombok.Getter;

@Getter
public class AuthorizationResponse {
    private String status;
    private AuthorizationData data;
}

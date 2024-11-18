package br.com.joelf.picpay.domain.usecases;

import br.com.joelf.picpay.domain.dtos.SignInDto;
import jakarta.servlet.http.Cookie;

public interface SignInUseCase {
    Cookie execute(SignInDto signIn);
}

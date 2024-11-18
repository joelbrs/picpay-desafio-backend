package br.com.joelf.picpay.domain.usecases;

import br.com.joelf.picpay.domain.dtos.SignUpDto;
import br.com.joelf.picpay.domain.dtos.UserDto;

public interface SignUpUseCase {
    UserDto execute(SignUpDto signUp);
}

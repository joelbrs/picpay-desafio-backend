package br.com.joelf.picpay.domain.usecases;

import br.com.joelf.picpay.domain.dtos.SignUpDto;
import br.com.joelf.picpay.domain.dtos.UserDtoOut;

public interface SignUpUseCase {
    UserDtoOut execute(SignUpDto signUp);
}

package br.com.joelf.picpay.application.dataprovider;

import br.com.joelf.picpay.domain.entities.User;

public interface UserDataProvider {
    User signUp(User user);
    User findByCpfCnpj(String cpfCnpj);
}

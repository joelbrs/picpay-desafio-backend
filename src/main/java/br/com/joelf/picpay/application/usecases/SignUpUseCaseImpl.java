package br.com.joelf.picpay.application.usecases;

import br.com.joelf.picpay.application.dataprovider.AccountDataProvider;
import br.com.joelf.picpay.application.dataprovider.UserDataProvider;
import br.com.joelf.picpay.domain.dtos.SignUpDto;
import br.com.joelf.picpay.domain.dtos.UserDtoOut;
import br.com.joelf.picpay.domain.entities.Account;
import br.com.joelf.picpay.domain.entities.User;
import br.com.joelf.picpay.domain.usecases.SignUpUseCase;
import br.com.joelf.picpay.domain.usecases.exceptions.SignUpUseCaseException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
public class SignUpUseCaseImpl implements SignUpUseCase {

    private final ModelMapper modelMapper;
    private final UserDataProvider userDataProvider;
    private final AccountDataProvider accountDataProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public UserDtoOut execute(SignUpDto signUp) {
        if (!signUp.validate()) {
            throw new SignUpUseCaseException("Invalid data");
        }

        User user = modelMapper.map(signUp, User.class);
        user.setPassword(passwordEncoder.encode(signUp.getPassword()));

        UserDtoOut result =
                modelMapper.map(userDataProvider.signUp(user), UserDtoOut.class);

        Account account = Account.builder()
                .user(user)
                .build();

        accountDataProvider.create(account);
        return result;
    }
}

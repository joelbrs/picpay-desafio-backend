package br.com.joelf.picpay.infraestructure.dataproviders;

import br.com.joelf.picpay.application.dataprovider.UserDataProvider;
import br.com.joelf.picpay.domain.entities.User;
import br.com.joelf.picpay.infraestructure.repositories.postgres.UserRepository;
import br.com.joelf.picpay.infraestructure.repositories.postgres.domain.PgUser;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

@AllArgsConstructor
public class UserDataProviderImpl implements UserDataProvider {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Override
    public User signUp(User user) {
        PgUser pgUser = modelMapper.map(user, PgUser.class);
        return modelMapper.map(userRepository.save(pgUser), User.class);
    }

    @Override
    public User findByCpfCnpj(String cpfCnpj) {
        PgUser pgUser = userRepository.findByCpfCnpj(cpfCnpj).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        return modelMapper.map(pgUser, User.class);
    }
}

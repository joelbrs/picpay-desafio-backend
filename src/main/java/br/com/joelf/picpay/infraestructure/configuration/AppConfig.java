package br.com.joelf.picpay.infraestructure.configuration;

import br.com.joelf.picpay.domain.entities.User;
import br.com.joelf.picpay.infraestructure.repositories.postgres.UserRepository;
import br.com.joelf.picpay.infraestructure.repositories.postgres.domain.PgUser;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@AllArgsConstructor
@Configuration
public class AppConfig {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Bean
    UserDetailsService userDetailsService() {
        return username -> {
            PgUser user = userRepository.findByCpfCnpj(username).orElseThrow(
                    () -> new UsernameNotFoundException("User not found")
            );
            return modelMapper.map(user, User.class);
        };
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
}

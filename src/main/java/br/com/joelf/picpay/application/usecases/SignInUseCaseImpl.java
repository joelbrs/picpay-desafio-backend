package br.com.joelf.picpay.application.usecases;

import br.com.joelf.picpay.application.dataprovider.UserDataProvider;
import br.com.joelf.picpay.domain.dtos.SignInDto;
import br.com.joelf.picpay.domain.dtos.TokenDto;
import br.com.joelf.picpay.domain.entities.User;
import br.com.joelf.picpay.domain.usecases.SignInUseCase;
import br.com.joelf.picpay.infraestructure.authentication.JwtService;
import jakarta.servlet.http.Cookie;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@AllArgsConstructor
public class SignInUseCaseImpl implements SignInUseCase {

    private String cookieName;
    private final AuthenticationManager authenticationManager;
    private final UserDataProvider userDataProvider;
    private final JwtService jwtService;

    @Override
    public Cookie execute(SignInDto signIn) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signIn.getCpfCnpj(), signIn.getPassword())
        );
        User userFound = userDataProvider.findByCpfCnpj(signIn.getCpfCnpj());
        TokenDto token = TokenDto.builder()
                .token(jwtService.generateToken(userFound))
                .expirationTime(jwtService.getExpirationTime())
                .build();

        Cookie cookie = new Cookie(cookieName, token.getToken());
        cookie.setHttpOnly(Boolean.TRUE);
        cookie.setPath("/");
        cookie.setMaxAge(token.getExpirationTime().intValue());
        return cookie;
    }
}

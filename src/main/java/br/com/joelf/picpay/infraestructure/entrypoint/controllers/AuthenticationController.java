package br.com.joelf.picpay.infraestructure.entrypoint.controllers;

import br.com.joelf.picpay.domain.dtos.SignInDto;
import br.com.joelf.picpay.domain.dtos.SignUpDto;
import br.com.joelf.picpay.domain.dtos.UserDto;
import br.com.joelf.picpay.domain.usecases.SignInUseCase;
import br.com.joelf.picpay.domain.usecases.SignUpUseCase;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final SignInUseCase signInUserUseCase;
    private final SignUpUseCase signUpUserUseCase;

    @PostMapping("/sign-in")
    public ResponseEntity<Void> signIn(@RequestBody @Valid SignInDto user, HttpServletResponse response) {
        response.addCookie(signInUserUseCase.execute(user));
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/sign-up")
    public ResponseEntity<UserDto> signUp(@RequestBody @Valid SignUpDto user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(signUpUserUseCase.execute(user));
    }
}

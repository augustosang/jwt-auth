package com.augusto.jwtauth.controllers;

import com.augusto.jwtauth.domain.user.AuthenticationDto;
import com.augusto.jwtauth.domain.user.LoginResponseDto;
import com.augusto.jwtauth.domain.user.User;
import com.augusto.jwtauth.domain.user.UserRegisterDto;
import com.augusto.jwtauth.infra.security.TokenService;
import com.augusto.jwtauth.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  TokenService tokenService;

  @PostMapping("/login")
  public ResponseEntity login(@RequestBody @Valid AuthenticationDto data) {
    var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
    var auth = this.authenticationManager.authenticate(usernamePassword);

    var token = tokenService.generateToken((User) auth.getPrincipal());

    return ResponseEntity.ok(new LoginResponseDto(token));
  }

  @PostMapping("/register")
  public ResponseEntity register(@RequestBody @Valid UserRegisterDto data) {
    if(this.userRepository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

    String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
    User newUser = new User(data.login(), encryptedPassword, data.role());

    this.userRepository.save(newUser);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}

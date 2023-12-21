package com.augusto.jwtauth.domain.user;

public record UserRegisterDto(String login, String password, UserRole role) {
}

package org.example.labo.Service;

import jakarta.validation.Valid;
import org.example.labo.Domain.DTO.CreateUserDTO;
import org.example.labo.Domain.DTO.KeycloakTokenResponse;

public interface iAuthService {

    KeycloakTokenResponse register(@Valid CreateUserDTO user) throws Exception;
    KeycloakTokenResponse login(String username, String password);
}
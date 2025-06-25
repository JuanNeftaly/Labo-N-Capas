package org.example.labo.Service.ServiceImpl;

import feign.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.labo.Client.Keycloak.iKeycloakAdminClient;
import org.example.labo.Client.Keycloak.iKeycloakAuthClient;
import org.example.labo.Config.Keycloak.KeycloakProperties;
import org.example.labo.Domain.DTO.CreateUserDTO;
import org.example.labo.Domain.DTO.KeycloakTokenResponse;
import org.example.labo.Service.iAuthService;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

import static org.example.labo.Utils.Mappers.GeneralMappers.createUserDtoToMap;
import static org.example.labo.Utils.Mappers.GeneralMappers.loginToFormData;
import static org.example.labo.Utils.UserIdFromKeycloak.getUserIdFromKeycloakResponse;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements iAuthService {

    private final iKeycloakAdminClient keycloakAdminClient;
    private final iKeycloakAuthClient keycloakAuthClient;
    private final KeycloakProperties keycloakProperties;

    @Override
    public KeycloakTokenResponse register(@Valid CreateUserDTO user) throws Exception {
        Response response = keycloakAdminClient.createUser(createUserDtoToMap(user));
        if (response.status() != 201) throw new Exception("Failed to create user: " + new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8));
        String userId = getUserIdFromKeycloakResponse(response);
        return login(user.getUserName(), user.getPassword());
    }

    @Override
    public KeycloakTokenResponse login(String username, String password) {
        return keycloakAuthClient.getToken(loginToFormData(username, password, keycloakProperties.getClientId(), keycloakProperties.getClientSecret()));
    }
}

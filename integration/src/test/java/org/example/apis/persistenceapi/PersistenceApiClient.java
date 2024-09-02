package org.example.apis.persistenceapi;

import static org.example.apis.AuthConfig.*;
import static org.example.utils.Logger.info;

import org.example.apis.AuthConfig;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

public class PersistenceApiClient {

    public ResponseEntity<String> patchFirm(String firmId, String body) {
        info("🚀 Patching firm '{}' with body:\n{}", firmId, body);

        return generateWebClient()
                .patch()
                .uri(uriBuilder -> uriBuilder.pathSegment("firms", firmId).build())
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(body))
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError() || status.is5xxServerError(),
                        AuthConfig::handleErrorResponse)
                .toEntity(String.class)
                .doOnError(WebClientResponseException.class, ex -> {
                    System.out.println("Error status code: " + ex.getStatusCode());
                    System.out.println("Error response body: " + ex.getResponseBodyAsString());
                })
                .block();
    }

    private WebClient generateWebClient() {
        return WebClient.builder()
                .baseUrl("https://dev-api.intact.fca.org.uk/v1/master-data/")
                .defaultHeader(
                        "Authorization",
                        "Bearer "
                                + generateAccessToken(AuthConfig.getPersistenceClientId(), AuthConfig.getPersistenceClientSecret())
                                        .accessToken())
                .build();
    }
}

package org.example.apis.intact;

import static org.example.apis.AuthConfig.*;
import static org.example.utils.Logger.info;

import org.example.apis.AuthConfig;
import org.example.models.Firm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

public class IntactClient {

    public ResponseEntity<Firm> getFirm(String firmId) {
        info("🔍 Getting firm '{}'", firmId);

        return generateWebClient()
                .get()
                .uri(uriBuilder ->
                        uriBuilder.pathSegment("sobjects", "Account", firmId).build())
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError() || status.is5xxServerError(),
                        AuthConfig::handleErrorResponse) // Error handling
                .toEntity(Firm.class)
                .doOnError(WebClientResponseException.class, ex -> {
                    System.out.println("Error status code: " + ex.getStatusCode());
                    System.out.println("Error response body: " + ex.getResponseBodyAsString());
                })
                .block();
    }

    public ResponseEntity<Firm> createFirm(Firm firm) {
        ResponseEntity<Firm> block = generateWebClient()
                .post()
                .uri(uriBuilder -> uriBuilder.pathSegment("sobjects", "Account").build())
                .body(BodyInserters.fromValue(firm))
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError() || status.is5xxServerError(),
                        AuthConfig::handleErrorResponse) // Error handling
                .toEntity(Firm.class)
                .doOnError(WebClientResponseException.class, ex -> {
                    System.out.println("Error status code: " + ex.getStatusCode());
                    System.out.println("Error response body: " + ex.getResponseBodyAsString());
                })
                .block();
        info("⭐ Created firm '{}'", block.getBody().id());
        return block;
    }

    private WebClient generateWebClient() {
        return WebClient.builder()
                .baseUrl("https://dev-api.intact.fca.org.uk/v1/gw/forms")
                .defaultHeader(
                        "Authorization",
                        "Bearer "
                                + generateAccessToken(AuthConfig.getIntactClientId(), AuthConfig.getIntactClientSecret())
                                        .accessToken())
                .build();
    }
}

package org.example.apis;

import org.example.utils.Token;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AuthConfig {

    public static final String OAUTH_GRANT_TYPE = "grant_type";

    public static final String OAUTH_CLIENT_ID = "client_id";

    public static final String OAUTH_CLIENT_SECRET = "client_secret";

    public static Token generateAccessToken(String clientId, String clientSecret) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add(OAUTH_GRANT_TYPE, "client_credentials");
        formData.add(OAUTH_CLIENT_ID, clientId);
        formData.add(OAUTH_CLIENT_SECRET, clientSecret);

        WebClient client = WebClient.builder()
                .baseUrl("https://dev-api.intact.fca.org.uk/oauth")
                .build();

        return client.post()
                .body(BodyInserters.fromFormData(formData))
                .retrieve()
                .bodyToMono(Token.class)
                .block();
    }

    public static Mono<Throwable> handleErrorResponse(ClientResponse clientResponse) {
        return clientResponse.bodyToMono(String.class).flatMap(errorBody -> {
            System.out.println(
                    "Error status code: " + clientResponse.statusCode().value());
            System.out.println("Error response body: " + errorBody);
            return Mono.error(new RuntimeException("Error response: " + errorBody));
        });
    }

    private static String getProperty(String property) {
        Properties properties = new Properties();
        try (InputStream input = AuthConfig.class.getClassLoader().getResourceAsStream("application-secrets.properties")) {
            if (input == null) {
                throw new IllegalArgumentException("Sorry, unable to find application-secrets.properties");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties.getProperty(property);
    }

    public static String getIntactClientId() {
        return getProperty("intact.clientId");
    }

    public static String getIntactClientSecret() {
        return getProperty("intact.clientSecret");
    }

    public static String getPersistenceClientId() {
        return getProperty("persistence.clientId");
    }

    public static String getPersistenceClientSecret() {
        return getProperty("persistence.clientSecret");
    }
}

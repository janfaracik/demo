package org.example.utils;

import com.fasterxml.jackson.annotation.JsonAlias;

public record Token(
        @JsonAlias("refresh_token_expires_in") String refreshTokenExpiresIn,
        @JsonAlias("api_product_list") String apiProductList,
        @JsonAlias("api_product_list_json") String[] apiProductListJson,
        @JsonAlias("organization_name") String organizationName,
        @JsonAlias("developer.email") String developerEmail,
        @JsonAlias("token_type") String tokenType,
        @JsonAlias("issued_at") String issuedAt,
        @JsonAlias("client_id") String clientId,
        @JsonAlias("access_token") String accessToken,
        @JsonAlias("application_name") String applicationName,
        @JsonAlias("scope") String scope,
        @JsonAlias("expires_in") String expiresIn,
        @JsonAlias("refresh_count") String refreshCount,
        @JsonAlias("status") String status) {}

package org.example.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record FirmPatch(
        @JsonAlias("RegisteredFirmName") String registeredFirmName,
        @JsonAlias("Website") String website,
        @JsonAlias("FinancialYearEnd") String financialYearEnd) {}

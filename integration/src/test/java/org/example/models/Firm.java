package org.example.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record Firm(@JsonAlias("Id") String id, @JsonAlias("Name") String name, @JsonAlias("Website") String website) {}

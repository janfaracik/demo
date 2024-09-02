package org.example;

import static org.assertj.core.api.Assertions.assertThat;

import org.example.apis.intact.IntactClient;
import org.example.apis.persistenceapi.PersistenceApiClient;
import org.example.models.Firm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class FirmTest {

    private final IntactClient intactClient = new IntactClient();

    private final PersistenceApiClient persistenceApiClient = new PersistenceApiClient();

    @Test
    @DisplayName("Setting registered firm name to a valid value sets the firm's name")
    void settingName() {
        var firm = intactClient
                .createFirm(Firm.builder().name("Tour Manager Inc").build())
                .getBody();
        String json =
                """
                {
                   "RegisteredName": "Hornets Co"
                }
                """;

        var response = persistenceApiClient.patchFirm(firm.id(), json);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(intactClient.getFirm(firm.id()).getBody().name()).isEqualTo("Hornets Co");
    }

    @Test
    @DisplayName("Setting website to a valid value sets the website")
    void settingWebsite() {
        var firm = intactClient
                .createFirm(Firm.builder().name("Tour Manager Inc").build())
                .getBody();
        String json =
                """
                {
                   "Website": "https://americanmary.com"
                }
                """;

        var response = persistenceApiClient.patchFirm(firm.id(), json);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(intactClient.getFirm(firm.id()).getBody().website()).isEqualTo("https://americanmary.com");
    }

    @Test
    @DisplayName("Setting website to an empty string removes the website")
    void settingWebsite_emptyString() {
        var firm = intactClient
                .createFirm(Firm.builder()
                        .name("Tour Manager Inc")
                        .website("https://americanmary.com")
                        .build())
                .getBody();
        String json = """
                {
                   "Website": null
                }
                """;

        var response = persistenceApiClient.patchFirm(firm.id(), json);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(intactClient.getFirm(firm.id()).getBody().website()).isNull();
    }
}

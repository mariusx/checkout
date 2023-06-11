package biz.marius.ecom.checkout.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CheckoutControllerIntegrationTest {
  private static final String CHECKOUT_URI = "/checkout";
  private static final int UNSUPPORTED_MEDIA_TYPE = 415;
  private static final String ORDER_LINES_WATCHES = "[\"001\", \"002\", \"003\"]";
  private static final String EXPECTED_CHECKOUT_RESPONSE = "{\"price\":230}";
  @Autowired
  private WebTestClient webClient;

  @Disabled("TODO: Enable when discount implemented") //TODO: Enable when discount implemented
  @Test
  void should_return_200_for_checkout() {
    webClient.post()
        .uri(CHECKOUT_URI)
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(ORDER_LINES_WATCHES)
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(String.class).isEqualTo(EXPECTED_CHECKOUT_RESPONSE);
  }

  @Test
  void should_return_400_when_no_request_body_for_checkout() {
    webClient.post()
        .uri(CHECKOUT_URI)
        .contentType(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus()
        .isBadRequest();
  }

  @Test
  void should_return_415_unsupported_media_type_for4_checkout() {
    webClient.post()
        .uri(CHECKOUT_URI)
        .exchange()
        .expectStatus()
        .isEqualTo(HttpStatusCode.valueOf(UNSUPPORTED_MEDIA_TYPE));
  }
}
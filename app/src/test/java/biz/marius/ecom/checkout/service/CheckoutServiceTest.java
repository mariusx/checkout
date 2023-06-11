package biz.marius.ecom.checkout.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CheckoutServiceTest {

  private CheckoutService service;

  @BeforeEach
  void setUp() {
    service = new CheckoutService();
  }

  @Test
  void should_calculate_price_when_ids_are_empty() {
    assertThat(service.calculatePrice(List.of())).isZero();
  }
}
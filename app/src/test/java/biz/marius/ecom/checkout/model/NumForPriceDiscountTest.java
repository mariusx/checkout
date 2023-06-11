package biz.marius.ecom.checkout.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class NumForPriceDiscountTest {
  Discount threeFor200 = new NumForPriceDiscount(3, 200d);
  Discount twoFor120 = new NumForPriceDiscount(2, 120d);

  @Test
  void should_discount_price_for_items_with_price_three_for_200() {
    assertAll(
        () -> assertThreeFor200(0, 100, 0L),
        () -> assertThreeFor200(1, 100, 100L),
        () -> assertThreeFor200(2, 100, 200L),
        () -> assertThreeFor200(3, 100, 200L),
        () -> assertThreeFor200(4, 100, 300L),
        () -> assertThreeFor200(5, 100, 400L),
        () -> assertThreeFor200(6, 100, 400L),
        () -> assertThreeFor200(12, 100, 800L)
    );
  }

  @Test
  void should_discount_price_for_items_with_price_two_for_120() {
    assertAll(
        () -> assertTwoFor120(0, 80, 0L),
        () -> assertTwoFor120(1, 80, 80L),
        () -> assertTwoFor120(2, 80, 120L),
        () -> assertTwoFor120(3, 80, 200L),
        () -> assertTwoFor120(4, 80, 240L),
        () -> assertTwoFor120(5, 80, 320L),
        () -> assertTwoFor120(6, 80, 360L),
        () -> assertTwoFor120(10, 80, 600L)
    );
  }

  private void assertThreeFor200(int quantity, double price, long expected) {
    assertThat(threeFor200.calculatePrice(quantity, price)).isEqualTo(expected);
  }

  private void assertTwoFor120(int quantity, double price, long expected) {
    assertThat(twoFor120.calculatePrice(quantity, price)).isEqualTo(expected);
  }
}
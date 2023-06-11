package biz.marius.ecom.checkout.service;

import biz.marius.ecom.checkout.model.Discount;
import biz.marius.ecom.checkout.model.NumForPriceDiscount;
import biz.marius.ecom.checkout.model.Watch;
import biz.marius.ecom.checkout.repository.DiscountRepository;
import biz.marius.ecom.checkout.repository.WatchRepository;
import biz.marius.ecom.checkout.test.WatchTestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static biz.marius.ecom.checkout.test.WatchTestData.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CheckoutServiceTest {

  private CheckoutService service;
  private WatchRepository watchRepository;
  private DiscountRepository discountRepository;
private static final Discount threeFor200 = new NumForPriceDiscount(3, 200d);
private static final Discount twoFor120 = new NumForPriceDiscount(2, 120d);

  @BeforeEach
  void setUp() {
    watchRepository = mock(WatchRepository.class);
    discountRepository = mock(DiscountRepository.class);
    service = new CheckoutService(watchRepository, discountRepository);
  }

  @Test
  void should_calculate_price_when_ids_are_empty() {
    assertThat(service.calculatePrice(List.of())).isZero();
  }

  @Test
  void should_calculate_price_when_id_exists() {
    var watch = w001();
    var watchMap = Map.of(watch.getId(), List.of(watch));
    var watchIds = List.of(watch.getId());

    when(watchRepository.findByIdIn(any())).thenReturn(watchMap);

    assertThat(service.calculatePrice(watchIds)).isEqualTo(Math.round(watch.getPrice()));

    verify(watchRepository).findByIdIn(anyList());
  }

  @ParameterizedTest
  @MethodSource
  void should_calculate_price_when_many_ids_exists(Map<String, List<Watch>> watchMap, long expectedPrice, Discount discount) {
    var watchIds = watchMap.keySet().stream().toList();

    when(watchRepository.findByIdIn(any())).thenReturn(watchMap);
    when(discountRepository.findById(any())).thenReturn(Optional.ofNullable(discount));

    assertThat(service.calculatePrice(watchIds)).isEqualTo(expectedPrice);

    verify(watchRepository).findByIdIn(anyList());
  }

  private static Stream<Arguments> should_calculate_price_when_many_ids_exists() {
    return Stream.of(
        Arguments.of(WatchTestData.mapOf(WATCH_ID_001, 2), 200, null),
        Arguments.of(WatchTestData.mapOf(WATCH_ID_001, 2), 200, threeFor200),
        Arguments.of(WatchTestData.mapOf(WATCH_ID_001, 3), 200, threeFor200),
        Arguments.of(WatchTestData.mapOf(WATCH_ID_002, 3), 200, twoFor120),
        Arguments.of(WatchTestData.mapOf(WATCH_ID_004, 10), 300, null)
    );
  }
}
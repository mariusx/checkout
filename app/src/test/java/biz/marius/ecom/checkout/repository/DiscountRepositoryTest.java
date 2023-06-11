package biz.marius.ecom.checkout.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static biz.marius.ecom.checkout.test.WatchTestData.WATCH_ID_001;
import static org.assertj.core.api.Assertions.assertThat;

class DiscountRepositoryTest {
  DiscountRepository repository;


  @BeforeEach
  void setUp() {
    repository = new DiscountRepository();
  }

  @Test
  void should_find_by_id() {
    assertThat(repository.findById(WATCH_ID_001)).isPresent();
  }

  @Test
  void should_not_find_non_existing() {
    assertThat(repository.findById("X")).isNotPresent();
  }
}
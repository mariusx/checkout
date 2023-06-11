package biz.marius.ecom.checkout.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static biz.marius.ecom.checkout.test.WatchTestData.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class WatchRepositoryTest {

  WatchRepository repository;

  @BeforeEach
  void setUp() {
    repository = new WatchRepository();
  }

  @Test
  void should_find_by_ids() {
    var found = repository.findByIdIn(List.of(WATCH_ID_001, WATCH_ID_002, WATCH_ID_001, WATCH_ID_002, WATCH_ID_004));
    assertAll(
        () -> assertThat(found).containsKeys(WATCH_ID_001, WATCH_ID_002, WATCH_ID_004),
        () -> assertThat(found).extractingByKey(WATCH_ID_001).asList().hasSize(2),
        () -> assertThat(found).extractingByKey(WATCH_ID_002).asList().hasSize(2),
        () -> assertThat(found).extractingByKey(WATCH_ID_004).asList().hasSize(1)
    );
  }
}
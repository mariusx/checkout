package biz.marius.ecom.checkout.test;

import biz.marius.ecom.checkout.model.Watch;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public final class WatchTestData {

  public static final String WATCH_ID_001 = "001";

  private WatchTestData() {
    // NOOP
  }

  public static Map<String, List<Watch>> mapOf(String id, int quantity) {
    return Map.of(id, IntStream.range(1, quantity + 1)
        .mapToObj(i -> switch (id) {
          case WATCH_ID_001 -> w001();
          default -> new Watch(id, 0);
        }).toList());
  }

  public static Watch w001() {
    return new Watch(WATCH_ID_001, 100);
  }
}
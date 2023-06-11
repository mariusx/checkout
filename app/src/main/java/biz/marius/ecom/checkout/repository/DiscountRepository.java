package biz.marius.ecom.checkout.repository;

import biz.marius.ecom.checkout.model.Discount;
import biz.marius.ecom.checkout.model.NumForPriceDiscount;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;


@Repository
public class DiscountRepository {
  private final Map<String, Discount> discounts = new ConcurrentHashMap<>();

  public DiscountRepository() {
    discounts.put("001", new NumForPriceDiscount(3, 200));
    discounts.put("002", new NumForPriceDiscount(2, 120));
  }

  public Optional<Discount> findById(String id) {
    return discounts.containsKey(id) ? Optional.of(discounts.get(id)) : Optional.empty();
  }
}

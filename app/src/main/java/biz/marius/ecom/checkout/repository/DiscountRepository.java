package biz.marius.ecom.checkout.repository;

import biz.marius.ecom.checkout.model.Discount;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class DiscountRepository {
  public Optional<Discount> findById(String id) {
    Discount discount = null;
    return Optional.ofNullable(discount);
  }
}

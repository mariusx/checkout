package biz.marius.ecom.checkout.service;

import biz.marius.ecom.checkout.model.Discount;
import biz.marius.ecom.checkout.model.Watch;
import biz.marius.ecom.checkout.repository.DiscountRepository;
import biz.marius.ecom.checkout.repository.WatchRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Service
@Slf4j
public class CheckoutService {
  private final WatchRepository watchRepository;
  private final DiscountRepository discountRepository;

  public CheckoutService(WatchRepository watchRepository, DiscountRepository discountRepository) {
    this.watchRepository = watchRepository;
    this.discountRepository = discountRepository;
  }

  public long calculatePrice(List<String> watchIds) {
    long totalPrice = 0;
    if (nonNull(watchIds) && !watchIds.isEmpty()) {
      var grouped = watchesGrouped(watchIds);

      for (Map.Entry<String, List<Watch>> entry : grouped.entrySet()) {
        var id = entry.getKey();
        var listOfWatches = entry.getValue();

        var price = listOfWatches.get(0).getPrice();
        var quantity = listOfWatches.size();
        var discount = discountForId(id);

        if (discount.isPresent()) {
          totalPrice += discount.get().calculatePrice(quantity, price);
        } else {
          totalPrice += quantity * price;
        }

      }
    }
    return totalPrice;
  }

  private Optional<Discount> discountForId(String id) {
    return discountRepository.findById(id);
  }

  private Map<String, List<Watch>> watchesGrouped(List<String> ids) {
    var distinctWatches = ids.stream().distinct().toList();
    return watchRepository.findByIdIn(distinctWatches);
  }
}
package biz.marius.ecom.checkout.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CheckoutService {
  public long calculatePrice(List<String> watchIds) {
    return 230L;
  }
}
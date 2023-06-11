package biz.marius.ecom.checkout.controller;

import biz.marius.ecom.checkout.model.CheckoutResponse;
import biz.marius.ecom.checkout.service.CheckoutService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
public class CheckoutController {

  private final CheckoutService checkoutService;

  public CheckoutController(CheckoutService checkoutService) {
    this.checkoutService = checkoutService;
  }

  /**
   * POST /checkout : Checkout and return total price
   *
   * @param ids Watch ids
   * @return Total price of watches
   */
  @PostMapping(value = "/checkout", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<CheckoutResponse> checkout(@RequestBody String[] ids) {
    log.debug("Checkout ids: {}", Arrays.toString(ids));
    var price = checkoutService.calculatePrice(List.of(ids));
    return ResponseEntity.ok(new CheckoutResponse(price));
  }

}

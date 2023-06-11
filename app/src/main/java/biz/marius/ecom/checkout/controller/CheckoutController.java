package biz.marius.ecom.checkout.controller;

import biz.marius.ecom.checkout.model.CheckoutResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@Slf4j
public class CheckoutController {

  /**
   * POST /checkout : Checkout and return total price
   *
   * @param ids Watch ids
   * @return Total price of watches
   */
  @PostMapping(value = "/checkout", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<CheckoutResponse> checkout(@RequestBody String[] ids) {
    log.debug("Checkout ids: {}", Arrays.toString(ids));
    return ResponseEntity.ok(new CheckoutResponse(230));
  }

}

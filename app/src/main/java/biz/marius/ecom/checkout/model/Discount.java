package biz.marius.ecom.checkout.model;

/**
 * Discount
 */
public interface Discount {
  /**
   * Calculate discounted price
   * @param quantity Quantity of items
   * @param price Price per item
   * @return Discounted price
   */
  long calculatePrice(int quantity, double price);
}

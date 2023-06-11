package biz.marius.ecom.checkout.model;

/**
 * Discount to model "n" for "price"
 */
public class NumForPriceDiscount implements Discount {
  private final int numberOfItems;
  private final double discountPrice;

  public NumForPriceDiscount(int numberOfItems, double discountPrice) {
    this.numberOfItems = numberOfItems;
    this.discountPrice = discountPrice;
  }

  @Override
  public long calculatePrice(int quantity, double price) {
    var numberOfDiscounted = quantity / numberOfItems;
    var total = (numberOfDiscounted * discountPrice) + ((quantity % numberOfItems) * price);

    return Math.round(total);
  }
}

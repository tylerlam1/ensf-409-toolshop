package server.models;

/**
 * Contains settings for orders made by the shop.
 */
public interface Quantities {

  /**
   * Make a new order when the item stock is under this threshold.
   */
  static final int LOW_STOCK_THRESHOLD = 40;

  /**
   * When ordering, order enough items so that the new stock of the item is this
   * number.
   */
  static final int ORDER_TO_AMOUNT = 50;
}
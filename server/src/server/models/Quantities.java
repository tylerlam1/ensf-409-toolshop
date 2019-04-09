package server.models;

/**
 * Contains settings for orders made by the shop.
 * 
 * @author Navjot Brar, Jofred Cayabyab and Tyler Lam
 * @version 1.0.0
 * @since March 31, 2019
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
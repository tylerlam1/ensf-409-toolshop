package utils;

/**
 * Contains String constants to denote types of data being sent.
 * 
 * @author Navjot Brar, Jofred Cayabyab and Tyler Lam
 * @version 1.0.0
 * @since April 1, 2019
 */
public interface DataCodes {
  // Comments denote the procedure after the datacode is sent.

  /* Client codes */

  /**
   * Send nothing after, receive ArrayList<Item> back
   */
  public static final String GET_TOOLS = "GET_TOOLS";

  /**
   * Send String (name), receive Item object back (or index in ArrayList)
   */
  public static final String SEARCH_TOOL_NAME = "SEARCH_TOOL_NAME";

  /**
   * Send String (ID), receive Item object back (or index in ArrayList)
   */
  public static final String SEARCH_TOOL_ID = "SEARCH_TOOL_ID";

  /**
   * Send description, quantity, price, supplierId, receive ArrayList<Item> back
   * Potentially just send Item object or SEND_SUCCESS back? The client can simply
   * add it to its own ArrayList on the client-side
   */
  public static final String CREATE_ITEM = "CREATE_ITEM";

  /**
   * Send Item object, receive ArrayList<Item> or SEND_ERROR back
   */
  public static final String DELETE_ITEM = "DELETE_ITEM";

  /**
   * Send nothing after, restock on server then receive ArrayList<Item> back
   */
  public static final String ORDER_ITEM = "ORDER_ITEM";
  /**
   * Send Item object, send quantity to decrease, receive ArrayList<Item> back
   */
  public static final String DECREASE_ITEM = "DECREASE_ITEM";

  /**
   * Send nothing after, receive SEND_SUCCESS back
   */
  public static final String STOP_SERVER = "STOP_SERVER";

  /* Server Codes */
  /**
   * Informs client of unexpected behaviour on server side.
   */
  public static final String SEND_ERROR = "SEND_ERROR";
  /**
   * Informs client of expected behaviour on server side.
   */
  public static final String SEND_SUCCESS = "SEND_SUCCESS";

  /* Login */

  /**
   * Send a UserInformation object, receive UserInformation back or SEND_ERROR if
   * authentication failed.
   */
  public static final String SEND_USERDATA = "SEND_USERDATA";
}
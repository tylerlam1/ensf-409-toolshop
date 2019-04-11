package utils;

/**
 * Contains String constants to denote types of data being sent.
 * 
 * @author Navjot Brar, Jofred Cayabyab and Tyler Lam
 * @version 1.0.0
 * @since April 1, 2019
 */
public interface DataCodes {
  // SEND denotes what is sent after the datacode is sent.
  // RECEIVE denotes what the client should expect to receive.

  /* Client codes */

  /**
   * Code for getting the list of tools from the server.
   * 
   * SEND: nothing. RECEIVE: ArrayList<Item> of tools.
   */
  public static final String GET_TOOLS = "GET_TOOLS";

  /**
   * Code for searching the server for a name.
   * 
   * SEND: name (String). RECEIVE: Item object, or SEND_ERROR if not found.
   */
  public static final String SEARCH_TOOL_NAME = "SEARCH_TOOL_NAME";

  /**
   * Code for searching the server for an ID.
   * 
   * SEND: id (String). RECEIVE: Item object, or SEND_ERROR if not found.
   */
  public static final String SEARCH_TOOL_ID = "SEARCH_TOOL_ID";

  /**
   * Code for creating a new item.
   * 
   * SEND: description (String), quantity (String), price (String), supplierId
   * (String). RECEIVE: ArrayList<Item> of tools.
   */
  public static final String CREATE_ITEM = "CREATE_ITEM";

  /**
   * Code for deleting an item.
   * 
   * SEND: Item object to delete RECEIVE: ArrayList<Item> of tools.
   */
  public static final String DELETE_ITEM = "DELETE_ITEM";

  /**
   * Code for auto-restocking.
   * 
   * SEND: nothing RECEIVE: ArrayList<Item> of tools.
   */
  public static final String ORDER_ITEMS = "ORDER_ITEMS";

  /**
   * Code for simulating the purchase of an item.
   * 
   * SEND: Item object to purchase, quantity to decrease (String) RECEIVE:
   * ArrayList<Item> of tools.
   */
  public static final String DECREASE_ITEM = "DECREASE_ITEM";

  /**
   * Code to tell server to stop running.
   * 
   * SEND: nothing RECEIVE: SEND_SUCCESS
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
   * Code to handle user authentication.
   * 
   * SEND: UserInformation object RECEIVE: UserInformation object, or SEND_ERROR
   * if failed.
   */
  public static final String SEND_USERDATA = "SEND_USERDATA";
  
  /**
   * Code to handle order information retrieval.
   * 
   * SEND: nothing after RECEIVE: String that contains order info
   */
  public static final String GET_ORDERS = "GET_ORDERS";
}
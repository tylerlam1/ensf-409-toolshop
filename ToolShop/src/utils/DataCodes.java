package utils;

/**
 * Contains String constants to denote types of data being sent.
 * 
 * @author Navjot Brar, Jofred Cayabyab and Tyler Lam
 * @version 1.0.0
 * @since April 1, 2019
 */
public interface DataCodes {
  public static final String SEND_USERDATA = "SEND_USERDATA";
  public static final String GET_TOOLS = "GET_TOOLS";
  public static final String SEARCH_TOOL_NAME = "SEARCH_TOOL_NAME";
  public static final String SEARCH_TOOL_ID = "SEARCH_TOOL_ID";
  public static final String CREATE_ITEM = "CREATE_ITEM";
  public static final String DELETE_ITEM = "DELETE_ITEM";
  public static final String ORDER_ITEM = "ORDER_ITEM";
  public static final String DECREASE_ITEM = "DECREASE_ITEM";
  public static final String QUIT = "QUIT";
}
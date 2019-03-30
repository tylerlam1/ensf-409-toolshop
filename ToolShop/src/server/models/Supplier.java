package server.models;

import java.io.Serializable;

/**
 * Contains the data from the text file for the Supplier, which supplies the
 * items to the shop.
 */
public class Supplier implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * The id of the supplier
   */
  private int id;

  /**
   * The name of the supplier
   */
  private String name;

  /**
   * The address that the supplier is located at
   */
  private String address;

  /** 
   * The primary contact of the supplier
   */
  private String contact;

  /**
   * Generates a new Supplier object and initializes its member variables. This
   * information is normally obtained from the text file.
   * 
   * @param id The id of the supplier
   * @param name The name of the supplier
   * @param address The address that the supplier is located at
   * @param contact The primary contact of the supplier
   */
  public Supplier(int id, String name, String address, String contact) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.contact = contact;
  }

  /**
   * Gets the unique id of the supplier.
   * 
   * @return the id
   */
  public int getId() {
    return id;
  }

  /**
   * Gets the name of the supplier.
   * 
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the address of the supplier.
   * 
   * @return the address
   */
  public String getAddress() {
    return address;
  }

  /**
   * Gets the primary contact of the supplier.
   * 
   * @return the contact
   */
  public String getContact() {
    return contact;
  }
}
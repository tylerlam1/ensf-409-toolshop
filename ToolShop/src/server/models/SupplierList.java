package server.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A wrapper for the ArrayList that contains all the suppliers that provide
 * items to the shop.
 * 
 * @author Navjot Brar, Jofred Cayabyab and Tyler Lam
 * @version 1.0.0
 * @since March 31, 2019
 */
public class SupplierList {

  /**
   * The List object that contains all of the suppliers read from suppliers.txt
   */
  private ArrayList<Supplier> list;

  /**
   * @return the list of suppliers
   */
  public ArrayList<Supplier> getList() {
    return list;
  }

  /**
   * Populates the list of suppliers from a given text file.
   * 
   * @param supplierFileName the name of the text file containing information
   *                         about the suppliers
   * @throws FileNotFoundException
   * @throws IOException
   */
  public SupplierList(String supplierFileName) throws FileNotFoundException, IOException {
    list = new ArrayList<Supplier>();

    File file = new File(supplierFileName);
    BufferedReader br = new BufferedReader(new FileReader(file));

    String inputStr;
    while ((inputStr = br.readLine()) != null) {
      addSupplierFromText(inputStr);
    }

    br.close();
  }

  /**
   * Uses text data to construct a Supplier object, then add it to the list.
   * 
   * @param textLine the line from the text file to construct the Supplier with.
   */
  private void addSupplierFromText(String textLine) {
    String[] dataValues = textLine.split(";");

    int id = Integer.parseInt(dataValues[0]);
    String name = dataValues[1];
    String address = dataValues[2];
    String contact = dataValues[3];

    Supplier newSupplier = new Supplier(id, name, address, contact);

    list.add(newSupplier);
  }

  /**
   * Gets a Supplier based on its id.
   * 
   * @param supplierId the id of the supplier
   * @return The Supplier object with the specified id.
   */
  public Supplier getSupplierById(int supplierId) {
    for (Supplier supplier : list) {
      if (supplier.getId() == supplierId) {
        return supplier;
      }
    }
    return null;
  }
}
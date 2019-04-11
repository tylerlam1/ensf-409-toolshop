package utils;

import java.io.Serializable;

/**
 * Stores the relevant loginID and password for a particular user
 * 
 * @author Navjot Brar, Jofred Cayabyab and Tyler Lam
 * @version 1.0.0
 * @since March 31, 2019
 */
public class UserInformation implements Serializable {

  private static final long serialVersionUID = 5L;

  /**
   * the login ID
   */
  private String loginId;

  /**
   * the login password
   */
  private String loginPassword;

  /**
   * determines if the user is owner or customer
   */
  private boolean isOwner;

  /**
   * Returns the ID of the user
   * 
   * @return the login Id
   */
  public String getId() {
    return loginId;
  }

  /**
   * Returns the login Password
   * 
   * @return the loginPassword
   */
  public String getPassword() {
    return loginPassword;
  }

  public void setIsOwner(boolean isOwner) {
    this.isOwner = isOwner;
  }

  /**
   * sets the ID of the user
   * 
   * @param loginId the loginId to set
   */
  public void setId(String loginId) {
    this.loginId = loginId;
  }

  /**
   * Sets the password of the user
   * 
   * @param loginPassword the loginPassword to set
   */
  public void setPassword(String loginPassword) {
    this.loginPassword = loginPassword;
  }
}
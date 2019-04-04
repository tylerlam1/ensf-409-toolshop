package client.models;

/**
 * Stores the relevant loginID and password for a particular user
 * 
 * @author Navjot Brar, Jofred Cayabyab and Tyler Lam
 * @version 1.0.0
 * @since March 31, 2019
 */
public class UserInformation {

  private String loginId;
  private String loginPassword;

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
package server.models;

/**
 * UserInformation
 */
public class UserInformation {

  private String loginId;
  private String loginPassword;

  /**
   * @return the login Id
   */
  public String getId() {
    return loginId;
  }

  /**
   * @return the loginPassword
   */
  public String getPassword() {
    return loginPassword;
  }

  /**
   * @param loginId the loginId to set
   */
  public void setId(String loginId) {
    this.loginId = loginId;
  }

  /**
   * @param loginPassword the loginPassword to set
   */
  public void setPassword(String loginPassword) {
    this.loginPassword = loginPassword;
  }
}
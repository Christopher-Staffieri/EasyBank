package easybank.Controllers;

import easybank.Model.User;
import easybank.Model.checkUser;
import easybank.Model.loginHandler;
import easybank.Model.userIF;

/**
 * Handles login request made by user.
 * 
 * @author CJ Staffieri
 * @version 1.0
 * @since 1.0
 */

public class loginController {
  // checkUser cU = new checkUser();
  // loginHandler lH = new loginHandler();
  // userIF user = new User();

  /**
   * This method checkes the data from the login request made by the user.
   * 
   * @param userName     The username entered by the user
   * @param password     The password entered by the user
   * @param accountType  The account type chosen by the user.
   * @param odProtection The option the user chose if given the option for
   *                     overdraft protection
   * @return String represents the message to be displayed to the user and if the
   *         user should be logged in or not.
   * @see checkUser
   * @see loginView
   */

  public String checkUser(String userName, char[] password, String accountType, boolean odProtection) {
    checkUser cU = new checkUser();
    String pass = String.valueOf(password);
    boolean userValid = cU.nameCheck(userName);
    boolean passValid = cU.passCheck(pass);

    boolean checkDupe = cU.checkForDupe(userName);

    if (checkDupe) {
      // System.out.println("User already exists");
      return "dupe";
    }else if (!userValid){
      // System.out.println("User some thing dsif exists");
      return "user";
    }else if (!passValid){
      // System.out.println("pass");
      return "pass";
    }else{
      // System.out.println("valid");
      cU.saveUser(userName, pass, accountType, odProtection);
      return "valid";
    }

    
    
  }

  /**
   * Handles logging the user in with the given data.
   * 
   * @param userName    The username entered by the user
   * @param password    The password entered by the user
   * @param accountType The account type chosen by the user.
   * @return boolean Represents if the details entered by the user are valid or
   *         not.
   * @see User
   */

  public boolean loginControl(String userName, char[] password, String accountType) {
    loginHandler lH = new loginHandler();
    userIF user = new User();
    String pass = String.valueOf(password);
    boolean result = lH.login(userName, pass, accountType);

    if (result) {
      // System.out.println("loggedf");
      user.setIsLoggedIn(true);
      return true;
    } else {
      user.setIsLoggedIn(false);
      return false;

    }
  }

  /**
   * Calling model to see if the user is logged in.
   * 
   * @return boolean Represents if the user is logged in or not.
   * @see User
   */
  public boolean getIsLoggedIn() {
    userIF user = new User();
    return user.getIsLoggedIn();
  }

}

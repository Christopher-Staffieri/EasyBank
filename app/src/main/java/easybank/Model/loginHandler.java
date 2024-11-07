package easybank.Model;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles all login requests made by the controllers.
 * 
 * @author CJ Staffieri
 * @version 1.0
 * @since 1.0
 */

public class loginHandler {

  /**
   * Handles login request made by the user
   * 
   * 
   * @return boolean Represents if the data entered by the user was validated
   */

  public boolean login(String userName, String password, String accountType) {
    try {
      ObjectInput ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("app/src/main/java/easybank/DB/accounts.txt")));
      boolean control = true;
      while (control) {
        HashMap<String, HashMap> userInfo = (HashMap<String, HashMap>) ois.readObject();
        if (userInfo != null) {
          String value = String.join("", userInfo.keySet());
          if (value.equals(userName)) {
            Map test = (Map) userInfo.get(userName).get(accountType);
            String value2 = String.join("", test.keySet());
            Map test2 = (Map) test.get(value2);
            String passToCheck = (String) test2.get("password");
            if (passToCheck.equals(password)) {
              userIF currentUser = new User();
              currentUser.setUserMap(userInfo);
              return true;
            } else {
              return false;
            }

          }
        }
      }

    } catch (Exception e) {

    }
    return false;
  }
}
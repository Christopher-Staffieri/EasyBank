package easybank.Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Handles all data requests made by the controllers.
 * 
 * @author CJ Staffieri
 * @version 1.0
 * @since 1.0
 */

public class dataHandler {

  /**
   * Retreives all checking accounts linked to the user in the databae.
   * 
   * 
   * @return String[] Represents an array of all checking account numbers linked
   *         to the users account.
   */

  public String[] getCheckingAccounts() {
    userIF user = new User();
    HashMap<String, HashMap> userData = user.getUserMap();
    boolean cont = true;
    int count = 0;
    while (cont) {
      try {
        String userName = String.join("", userData.keySet());
        Map accountType = (Map) userData.get(userName).get("Checking");
        Set<String> test = accountType.keySet();
        String[] accounts = new String[test.size()];
        for (String s : test) {
          accounts[count] = s;
        }
        return accounts;
      } catch (Throwable e) {

        cont = false;
      }
    }

    return null;
  }

  /**
   * Retreives all savings accounts linked to the user in the databae.
   * 
   * 
   * @return String[] Represents an array of all savings account numbers linked to
   *         the users account.
   */

  public String[] getSavingsAccounts() {
    userIF user = new User();
    HashMap<String, HashMap> userData = user.getUserMap();
    boolean cont = true;
    int count = 0;
    while (cont) {
      try {
        String userName = String.join("", userData.keySet());
        Map accountType = (Map) userData.get(userName).get("Savings");
        Set<String> test = accountType.keySet();
        String[] accounts = new String[test.size()];
        for (String s : test) {
          accounts[count] = s;
          count++;
        }
        return accounts;
      } catch (Exception e) {
        cont = false;
      }

    }

    return null;
  }

  /**
   * Saves the new sub account to the users main account and then to the database.
   * 
   * @param accountType  The account type chosen by the user.
   * @param odProtection The option the user chose if given the option for
   *                     overdraft protection
   * 
   */
  public void saveExtraAccount(String accountType, boolean odProtection) {
    userIF user = new User();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
    LocalDateTime now = LocalDateTime.now();
    
    HashMap<String, HashMap> userData = user.getUserMap();
    int count = 0;
    boolean cont = true;
    while (cont) {
      String userName = String.join("", userData.keySet());
      Map account = (Map) userData.get(userName).get(accountType);
      if (account == null) {
        userData.get(userName).put(accountType, new HashMap<String, HashMap>());
        long seed = System.currentTimeMillis();
        Random rng = new Random(seed);
        long number = rng.nextLong() % 100000;
        String id = String.valueOf(number);
        continue;
      }
      if (account != null) {
        long seed = System.currentTimeMillis();
        Random rng = new Random(seed);
        long number = rng.nextLong() % 100000;
        String id = String.valueOf(number);
        account.put(id, new HashMap<String, Object>());
        String[] existingAccount = findAcount();
        String existingAccountNumber = existingAccount[0];
        String existingAccountType = existingAccount[1];

        Map existingAccountMap = (Map) userData.get(userName).get(existingAccountType);

        Map existingUserData = (Map) existingAccountMap.get(existingAccountNumber);

        Map newAccountData = (Map) account.get(id);

        String pass = (String) existingUserData.get("password");
        newAccountData.put("password", pass);
        newAccountData.put("balance", 0.0);
        newAccountData.put("date_created", dtf.format(now));
        if (existingAccountType == "Savings") {
          newAccountData.put("Overdraft_Protection", odProtection);
        }
        break;
      }

    }
  }

  /**
   * Find the users existing account
   * 
   * @return String[] Represents an array of the users existing account numbers
   *         and their types.
   * 
   */
  public String[] findAcount() {
    userIF user = new User();
    HashMap<String, HashMap> userData = user.getUserMap();
    boolean cont = true;
    String userName = String.join("", userData.keySet());
    while (cont) {
      try {
        Map checkingAccounts = (Map) userData.get(userName).get("Checking");
        Set<String> test = checkingAccounts.keySet();
        if (test.size() > 0) {
          for (String s : test) {
            String mds = s + "|" + "Checking";
            String[] hm = mds.split("\\|");
            return hm;
          }

        }
      } catch (Exception e) {
        try {
          Map savingsAccount = (Map) userData.get(userName).get("Savings");
          Set<String> test = savingsAccount.keySet();
          if (test.size() > 0) {
            for (String s : test) {
              String mds = s + "|" + "Savings";
              String[] hm = mds.split("\\|");
              return hm;
            }
          }
        } catch (Exception e2) {

        }
      }
    }
    return null;
  }

}
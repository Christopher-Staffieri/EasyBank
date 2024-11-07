package easybank.Model;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class bankStatsHandler {

  /**
   * Gets all bank accounts in the database.
   * 
   * @return An int reprsenting the amount of accounts in the bank database.
   */
  public int getAmountOfAccounts() {
    int count = 0;
    try {
      ObjectInput ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("app/src/main/java/easybank/DB/accounts.txt")));
      boolean con = true;
      while (con) {
        HashMap<String, HashMap> userInfo = (HashMap<String, HashMap>) ois.readObject();
        if (userInfo != null) {
          count++;
        }
      }
    } catch (Exception e) {

    }
    return count;

  }

  /**
   * Gets all sub-accounts in the banks database (ex: accounts under savings and
   * checking are sub accounts).
   * 
   * @return An int reprsenting the amount of aub accounts in the bank database.
   */
  public int getAmountOfUsers() {
    int count = 0;
    try {
      ObjectInput ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("app/src/main/java/easybank/DB/accounts.txt")));
      boolean con = true;
      while (con) {
        HashMap<String, HashMap> userInfo = (HashMap<String, HashMap>) ois.readObject();
        if (userInfo != null) {
          String userName = String.join("", userInfo.keySet());
          Map checkingAccounts = (Map) userInfo.get(userName).get("Checking");
          Map savingsAccounts = (Map) userInfo.get(userName).get("Savings");

          if (checkingAccounts != null) {
            count += checkingAccounts.keySet().size();
          }

          if (savingsAccounts != null) {
            count += savingsAccounts.keySet().size();
          }

        }
      }

    } catch (Exception e) {

    }
    return count;

  }

  /**
   * Handles retrivng the banks total balance.
   * 
   * @return double Represents if the amount of money in the bank.
   */

  public double getBankBalance() {
    double total = 0;
    try {
      ObjectInput ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("app/src/main/java/easybank/DB/accounts.txt")));
      boolean con = true;
      while (con) {
        HashMap<String, HashMap> userInfo = (HashMap<String, HashMap>) ois.readObject();
        if (userInfo != null) {
          String userName = String.join("", userInfo.keySet());
          Map checkingAccounts = (Map) userInfo.get(userName).get("Checking");
          Map savingsAccounts = (Map) userInfo.get(userName).get("Savings");

          if (checkingAccounts != null) {
            Set<String> keys = checkingAccounts.keySet();

            for (Object key : checkingAccounts.keySet()) {
              String id = (String) key;
              Map balances = (Map) checkingAccounts.get(id);
              Double balance = (Double) balances.get("balance");
              total += balance;

            }
            ;
          }

          if (savingsAccounts != null) {
            Set<String> keys = savingsAccounts.keySet();

            for (Object key : savingsAccounts.keySet()) {
              String id = (String) key;
              Map balances = (Map) savingsAccounts.get(id);
              Double balance = (Double) balances.get("balance");
              total += balance;

            }
            ;
          }

        }
      }

    } catch (Exception e) {

    }
    return total;
  }

  /**
   * Handles retrivng all avvounts in the banks database.
   * 
   * @return ArrayList<HashMap<String, HashMap>> Represents a list of nested
   *         hashmaps containing all users in the banks database with their data.
   */

  public ArrayList<HashMap<String, HashMap>> viewAllAccounts() {
    ArrayList<HashMap<String, HashMap>> accounts = new ArrayList<HashMap<String, HashMap>>();
    try {
      ObjectInput ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("app/src/main/java/easybank/DB/accounts.txt")));
      boolean con = true;
      while (con) {

        HashMap<String, HashMap> userInfo = (HashMap<String, HashMap>) ois.readObject();
        if (userInfo != null) {
          accounts.add(userInfo);

        }
      }

    } catch (Throwable e) {

    }
    return accounts;

  }

  /**
   * Handles retrivng all withdrawls in the banks database.
   * 
   * @return ArrayList<HashMap<String, HashMap>> Represents a list of nested
   *         hashmaps containing all withdraw;s in the banks database with their
   *         data.
   */
  public ArrayList<HashMap<String, HashMap>> getAllWithdrawls() {
    ArrayList<HashMap<String, HashMap>> allWithdrawls = new ArrayList<HashMap<String, HashMap>>();
    try {
      ObjectInput ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("app/src/main/java/easybank/DB/withdraws.txt")));
      boolean con = true;

      while (con) {

        HashMap<String, HashMap> withdrawls = (HashMap<String, HashMap>) ois.readObject();
        if (withdrawls != null) {

          allWithdrawls.add(withdrawls);
        }
      }
    } catch (Throwable e) {

    }
    return allWithdrawls;

  }

  /**
   * Handles retrivng all deposits in the banks database.
   * 
   * @return ArrayList<HashMap<String, HashMap>> Represents a list of nested
   *         hashmaps containing all deposits in the banks database with their
   *         data.
   */
  public ArrayList<HashMap<String, HashMap>> getAllDeposits() {
    ArrayList<HashMap<String, HashMap>> allDeposits = new ArrayList<HashMap<String, HashMap>>();
    try {
      ObjectInput ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("app/src/main/java/easybank/DB/deposits.txt")));
      boolean con = true;

      while (con) {

        HashMap<String, HashMap> deposits = (HashMap<String, HashMap>) ois.readObject();
        if (deposits != null) {

          allDeposits.add(deposits);
        }
      }
    } catch (Throwable e) {

    }
    return allDeposits;

  }

}
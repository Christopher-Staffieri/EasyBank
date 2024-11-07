package easybank.Controllers;

import java.util.ArrayList;
import java.util.HashMap;

import easybank.Model.bankStatsHandler;

/**
 * Retrives all bank data from the nodel (bankStatsHandler).
 * 
 * @author CJ Staffieri
 * @version 1.0
 * @since 1.0
 */

public class bankInfoController {
  bankStatsHandler bSH = new bankStatsHandler();

  /**
   * Method to get the total amount of users in the banks database.
   * 
   * @param Unused.
   * @return Int value representing the amount of users in the banks database.
   * @see bankStatsHandler
   */
  public int getAmountOfAccounts() {
    return bSH.getAmountOfAccounts();
  }

  /**
   * Method to get the total amount of sub account in the banks database.
   * 
   * @param Unused.
   * @return Int value representing the amount of sub-users in the banks database.
   * @see bankStatsHandler
   */
  public int getAmountOfSubAccounts() {
    return bSH.getAmountOfUsers();
  }

  /**
   * Method to get the total balance of the bank.
   * 
   * @param Unused.
   * @return Int value representing the balance of the bank.
   * @see bankStatsHandler
   */
  public double getTotalBalance() {
    return bSH.getBankBalance();
  }

  /**
   * Method to get all users in the banks database.
   * 
   * @param Unused.
   * @return ArrayList<HashMap<String, HashMap>> which stores all users data from
   *         the banks database into an arraylist
   * @see bankStatsHandler
   */
  public ArrayList<HashMap<String, HashMap>> getAllAccounts() {
    // System.out.println(bSH.viewAllAccounts());
    return bSH.viewAllAccounts();
  }

  /**
   * Method to get all withdrawls in the banks database.
   * 
   * @param Unused.
   * @return ArrayList<HashMap<String, HashMap>> stores all withdrawls from the
   *         banks database into an arraylist
   * @see bankStatsHandler
   */
  public ArrayList<HashMap<String, HashMap>> getAllWithdrawls() {
    return bSH.getAllWithdrawls();

  }

  /**
   * Method to get all withdrawls in the banks database.
   * 
   * @param Unused.
   * @return ArrayList<HashMap<String, HashMap>> stores all deposits from the
   *         banks database into an arraylist
   * @see bankStatsHandler
   */
  public ArrayList<HashMap<String, HashMap>> getAllDeposits() {
    return bSH.getAllDeposits();

  }

}
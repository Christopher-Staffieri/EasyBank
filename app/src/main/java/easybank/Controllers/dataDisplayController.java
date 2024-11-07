package easybank.Controllers;

import easybank.Model.dataHandler;

/**
 * Retrives all bank data from the nodel (bankStatsHandler).
 * 
 * @author CJ Staffieri
 * @version 1.0
 * @since 1.0
 */

public class dataDisplayController {
  dataHandler dH = new dataHandler();

  /**
   * This methood retrives all checking accounts from the current logged in users
   * data.
   * 
   * @return String[] returns an array of strings containing all checking accounts
   *         from the current logged in users data.
   * @see dataHandler
   */
  public String[] getCheckingAccounts() {
    String[] checkingAccounts = dH.getCheckingAccounts();
    return checkingAccounts;
  }

  /**
   * This methood retrives all savings accounts from the current logged in users
   * data.
   * 
   * @return String[] returns an array of strings containing all savings accounts
   *         from the current logged in users data.
   * @see dataHandler
   */
  public String[] getSavingsAccounts() {
    String[] savingsAccounts = dH.getSavingsAccounts();
    return savingsAccounts;
  }

}
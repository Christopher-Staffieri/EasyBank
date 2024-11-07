package easybank.Controllers;

import java.util.ArrayList;
import java.util.HashMap;

import easybank.Model.transactionHandler;

/**
 * Handles setting changes made by the user.
 * 
 * @author CJ Staffieri
 * @version 1.0
 * @since 1.0
 */

public class transactionsController {
  transactionHandler tH = new transactionHandler();

  /**
   * Retrives all deposits made by the user
   * 
   * @return ArrayList<HashMap<String, HashMap>> returns an array of nested
   *         hashmaps contianing all deposits made by the user
   * @see transactionHandler
   */

  public ArrayList<HashMap<String, HashMap>> getUserDeposits() {
    return tH.getUserDepositsTransactions();
  }

  /**
   * Retrives all withdrawls made by the user
   * 
   * @return ArrayList<HashMap<String, HashMap>> returns an array of nested
   *         hashmaps contianing all withdrawls made by the user
   * @see transactionHandler
   */

  public ArrayList<HashMap<String, HashMap>> getUserWithDrawls() {
    return tH.getUserWithdrawlTransactions();
  }

}
package easybank.Controllers;

import easybank.Model.checkUser;
import easybank.Model.transactionHandler;

/**
 * Handles setting changes made by the user.
 * 
 * @author CJ Staffieri
 * @version 1.0
 * @since 1.0
 */

public class transferController {
  String accountType = "";
  String account = "";
  // checkUser cU = new checkUser();
  // transactionHandler tH = new transactionHandler();
  
  /**
   * Handles deposits made by the user.
   * 
   * @param amount The amount the user is depositing.
   * @see transactionHandler
   * @see checkUser
   */
  public void deposit(int amount) {
    checkUser cU = new checkUser();
    transactionHandler tH = new transactionHandler();
    if (amount > 0 && amount < 100000) {
      tH.handleDeposit(amount, account, accountType);
      cU.saveUpdatedUser();
    } 

  }

  /**
   * Handles withdrawls made by the user.
   * 
   * @param amount The amount the user is withdrawling.
   * @see transactionHandler
   * @see checkUser
   */
  public void withDrawl(int amount) {
    checkUser cU = new checkUser();
    transactionHandler tH = new transactionHandler();
    if (amount > 0 && amount < 100000) {
      tH.handleWithdrawl(amount, account, accountType);
      // System.out.println("Got to here");
      cU.saveUpdatedUser();
    }
  }

  public void setData(String account, String accountType) {
    this.accountType = accountType;
    this.account = account;
  }

  public String getAccountType() {
    return accountType;
  }

  public String getAccount() {
    return account;
  }

}
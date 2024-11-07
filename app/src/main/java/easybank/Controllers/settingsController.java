package easybank.Controllers;

import easybank.Model.checkUser;
import easybank.Model.dataHandler;
import easybank.Model.transactionHandler;

/**
 * Handles setting changes made by the user.
 * 
 * @author CJ Staffieri
 * @version 1.0
 * @since 1.0
 */

public class settingsController {
  checkUser cU = new checkUser();

  /**
   * Handles creating another account for the user.
   * 
   * @param accountType The account type chosen by the user.
   * @see checkUser
   * @see dataHandler
   */
  public void createExtraAccount(String accountType, boolean odProtection) {
    dataHandler dH = new dataHandler();
    dH.saveExtraAccount(accountType, odProtection);
    cU.saveUpdatedUser();
  }

  /**
   * Handles editing an exisiting account for the user.
   * 
   * @param account      The account the user chose to edit
   * @param accountType  The account type chosen by the user.
   * @param odProtection The option the user chose if given the option for
   *                     overdraft protection
   * @see checkUser
   * @see dataHandler
   */
  public void editAccount(String account, String accountType, boolean odProtection) {
    transactionHandler tH = new transactionHandler();
    tH.saveAccountEdit(account, accountType, odProtection);
    cU.saveUpdatedUser();

  }

}
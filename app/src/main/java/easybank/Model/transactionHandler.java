package easybank.Model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Handles all transaction requests made by the controllers.
 * 
 * @author CJ Staffieri
 * @version 1.0
 * @since 1.0
 */

public class transactionHandler {

  /**
   * Handles deposit requests made by the user
   * 
   * @param amount      The amount the user is depositing
   * @param account     The account the user is depositing to
   * @param accountType The account type the user is depositing to (Savings or
   *                    checking)
   */

  public void handleDeposit(int amount, String account, String accountType) {

    userIF user = new User();
    boolean cont = true;
    HashMap<String, HashMap> userData = user.getUserMap();
    String userName = String.join("", userData.keySet());
    Map test = (Map) userData.get(userName).get(accountType);
    Set<String> keys = test.keySet();
    while (cont) {
      for (String key : keys) {
        if (key.equals(account)) {
          Map test2 = (Map) test.get(key);
          double balance = (double) test2.get("balance");
          balance += amount;
          test2.put("balance", balance);
          saveDeposit(key, accountType, amount, balance);
          cont = false;

        }

      }
    }
  }

  /**
   * Handles withdrawl requests made by the user
   * 
   * @param amount      The amount the user is depositing
   * @param account     The account the user is depositing to
   * @param accountType The account type the user is depositing to (Savings or
   *                    checking)
   */

  public void handleWithdrawl(int amount, String account, String accountType) {
    userIF user = new User();
    boolean cont = true;
    boolean odProtection = false;
    HashMap<String, HashMap> userData = user.getUserMap();
    String userName = String.join("", userData.keySet());
    Map test = (Map) userData.get(userName).get(accountType);
    Set<String> keys = test.keySet();
    while (cont) {
      for (String key : keys) {

        if (key.equals(account)) {
          Map test2 = (Map) test.get(key);
          double balance = (double) test2.get("balance");
          if (test2.get("Overdraft_Protection") != null) {
            odProtection = (boolean) test2.get("Overdraft_Protection");
          }

          if (balance - amount >= 0) {
            balance -= amount;
            test2.put("balance", balance);
            saveWithdrawl(key, accountType, amount, balance, false, true, 0);

          } else if (balance - amount < 0 && odProtection == true) {
            if (amount > 100) {
              int fee = 35;
              balance -= amount + fee;
              test2.put("balance", balance);
              saveWithdrawl(key, accountType, amount, balance, true, false, fee);

            } else {
              balance -= amount;
              test2.put("balance", balance);
              saveWithdrawl(key, accountType, amount, balance, true, true, 0);

            }

          } else if (balance - amount < 0 && odProtection == false) {
            if (amount < 100) {
              int fee = 35;
              balance -= amount;
              test2.put("balance", balance);
              saveWithdrawl(key, accountType, amount, balance, true, false, fee);

            } else if (amount > 100) {
              int fee = 50;
              balance -= amount + fee;
              test2.put("balance", balance);
              saveWithdrawl(key, accountType, amount, balance, true, false, fee);

            }
          }

          cont = false;
        }
      }

    }

  }


  /**
   * Saves valid deposit requests made by the user to the banks log file.
   * 
   * @param amountNumber The amount # of the account the user is depositing to
   * @param amount       The account the user is depositing to
   * @param balance      The account the user has after the transaction
   * @param accountType  The account type the user is depositing to (Savings or
   *                     checking)
   */

  public void saveDeposit(String accountNumber, String accountType, int amount, double balance) {
    HashMap transactionMap = new HashMap<String, HashMap<String, Object>>();
    userIF user = new User();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
    LocalDateTime now = LocalDateTime.now();
    HashMap<String, HashMap> userData = user.getUserMap();
    String userName = String.join("", userData.keySet());
    transactionMap.put(userName, new HashMap<String, Object>());
    Map transDetails = (Map) transactionMap.get(userName);
    transDetails.put("date", dtf.format(now));
    transDetails.put("accountNumber", accountNumber);
    transDetails.put("accountType", accountType);
    transDetails.put("amount", amount);
    transDetails.put("balance", balance);
    transDetails.put("bankRouting", "021200339");
    transDetails.put("bankName", "Maze Bank");
    transDetails.put("payor", accountNumber);
    transDetails.put("payee", "Bank of Maze");
    try {
      BufferedReader br = new BufferedReader(new FileReader("app/src/main/java/easybank/DB/deposits.txt"));
      if (br.readLine() == null) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
            new BufferedOutputStream(new FileOutputStream("app/src/main/java/easybank/DB/deposits.txt")))) {
          objectOutputStream.writeObject(transactionMap);
          objectOutputStream.close();
        } catch (IOException e) {
        }
      } else {
        try (AppendingObjectOutputStream objectOutputStream = new AppendingObjectOutputStream(
            new BufferedOutputStream(new FileOutputStream("app/src/main/java/easybank/DB/deposits.txt", true)))) {
          objectOutputStream.writeObject(transactionMap);
          objectOutputStream.close();
        } catch (IOException cause) {
        }

      }
    } catch (IOException e) {

    }
  }

  /**
   * Saves valid withdrawl requests made by the user to the banks log file.
   * 
   * @param amountNumber            The amount # of the account the user is
   *                                withdrawling to
   * @param amount                  The account the user is withdrawling to
   * @param balance                 The account the user has after the transaction
   * @param accountType             The account type the user is withdrawling to
   *                                (Savings or checking)
   * @param overDrafted             Did the user overdraft while making the
   *                                transaction.
   * @param usedOverdraftProtection Did the user use overdraft protection
   * @param overDraftFee            the fee paid for causing an overdraft.
   */

  public void saveWithdrawl(String accountNumber, String accountType, int amount, double balance, boolean overDrafted,
      boolean usedOdProtection, int overDraftFee) {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
    LocalDateTime now = LocalDateTime.now();
    
    HashMap transactionMap = new HashMap<String, HashMap<String, Object>>();
    userIF user = new User();
    HashMap<String, HashMap> userData = user.getUserMap();
    String userName = String.join("", userData.keySet());
    transactionMap.put(userName, new HashMap<String, Object>());
    Map transDetails = (Map) transactionMap.get(userName);
    transDetails.put("date", dtf.format(now));
    transDetails.put("accountNumber", accountNumber);
    transDetails.put("accountType", accountType);
    transDetails.put("amount", amount);
    transDetails.put("balance", balance);
    transDetails.put("bankRouting", "021200339");
    transDetails.put("bankName", "Maze Bank");
    transDetails.put("payor", "Bank of Maze");
    transDetails.put("payee", accountNumber);
    transDetails.put("caused-Overdraft", overDrafted);
    if (usedOdProtection) {
      transDetails.put("odProtectionUsed", true);
    } else {
      transDetails.put("odProtectionUsed", false);
    }
    transDetails.put("overDraftFee", overDraftFee);
    try {
      BufferedReader br = new BufferedReader(new FileReader("app/src/main/java/easybank/DB/withdraws.txt"));
      if (br.readLine() == null) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
            new BufferedOutputStream(new FileOutputStream("app/src/main/java/easybank/DB/withdraws.txt")))) {
          objectOutputStream.writeObject(transactionMap);
          objectOutputStream.close();
        } catch (IOException e) {
        }
      } else {
        try (AppendingObjectOutputStream objectOutputStream = new AppendingObjectOutputStream(
            new BufferedOutputStream(new FileOutputStream("app/src/main/java/easybank/DB/withdraws.txt", true)))) {
          objectOutputStream.writeObject(transactionMap);
          objectOutputStream.close();
        } catch (IOException cause) {
          cause.printStackTrace();
        }

      }
    } catch (IOException e) {
    }

  }

  /**
   * Saves any settinga changed on the users account by the user.
   * 
   * @param accountType  The account type the user is withdrawling to (Savings or
   *                     checking)
   * @param account      The account the user is editing
   * @param odProtection Users choice for overdraft protection
   */
  public void saveAccountEdit(String account, String accountType, boolean odProtection) {
    userIF user = new User();
    boolean cont = true;
    HashMap<String, HashMap> userData = user.getUserMap();
    String userName = String.join("", userData.keySet());
    Map test = (Map) userData.get(userName).get(accountType);
    Set<String> keys = test.keySet();
    while (cont) {
      for (String key : keys) {
        if (key.equals(account)) {
          Map test2 = (Map) test.get(key);
          test2.put("Overdraft_Protection", odProtection);
          cont = false;
        }
      }
    }

  }

  /**
   * Saves any settinga changed on the users account by the user.
   * 
   * @return ArrayList<HashMap<String, HashMap>> Represents an arraylist with nested hasmaps contaning all user withdrawl transactions made.
   */
  
  public ArrayList<HashMap<String, HashMap>> getUserWithdrawlTransactions() {
    userIF user = new User();
    HashMap<String, HashMap> userData = user.getUserMap();
    boolean cont = true;
    int count = 0;
    String userName = String.join("", userData.keySet());
    ArrayList<HashMap<String, HashMap>> transactions = new ArrayList<HashMap<String, HashMap>>();

    try (ObjectInput ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("app/src/main/java/easybank/DB/withdraws.txt")));) {
      while (cont) {
        HashMap<String, HashMap> withdrawlInfo = (HashMap<String, HashMap>) ois.readObject();
        if (withdrawlInfo != null) {
          String value = String.join("", withdrawlInfo.keySet());
          // System.out.println(value);
          if (value.equals(userName)) {
            // System.out.println("Added to array");
            transactions.add(withdrawlInfo);
          }
        }
      }
      ois.close();
    } catch (Throwable e) {
    }
    return transactions;

  }

  public ArrayList<HashMap<String, HashMap>> getUserDepositsTransactions() {
    userIF user = new User();
    HashMap<String, HashMap> userData = user.getUserMap();
    boolean cont = true;
    int count = 0;
    String userName = String.join("", userData.keySet());
    ArrayList<HashMap<String, HashMap>> transactions = new ArrayList<HashMap<String, HashMap>>();

    try (ObjectInput ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("app/src/main/java/easybank/DB/deposits.txt")));) {
      while (cont) {
        HashMap<String, HashMap> depositsInfo = (HashMap<String, HashMap>) ois.readObject();
        if (depositsInfo != null) {
          String value = String.join("", depositsInfo.keySet());
          // System.out.println(value);
          if (value.equals(userName)) {
            // System.out.println("Added to array");
            transactions.add(depositsInfo);
          }
        }
      }
      ois.close();
    } catch (Throwable e) {
    }
    // System.out.println(transactions.size());
    return transactions;

  }

}

package easybank.Model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class checkUser {

  /**
   * Validates if the username entered can be used.
   * 
   * @param userName The username entered by the user
   * @return boolean Represents if the user name entered by the user is valid or
   *         not
   */

  public boolean nameCheck(String userName) {
    if (userName.length() > 30 || userName.length() < 5) {
      return false;
    }

    Pattern p = Pattern.compile("[^a-zA-Z0-9._-]", Pattern.CASE_INSENSITIVE);
    Matcher m = p.matcher(userName);
    if (m.find()) {
      return false;
    } else {
      return true;
    }
  }

  /**
   * Validates if the password entered can be used.
   * 
   * @param password The password entered by the user
   * @return boolean Represents if the password entered by the user is valid or
   *         not
   */
  public boolean passCheck(String password) {
    String passwordStripped = password.strip();
    if (passwordStripped.length() < 8 || passwordStripped.length() > 30) {
      return false;
    }
    Pattern p = Pattern.compile("[^a-zA-Z0-9\\p{Punct}]", Pattern.CASE_INSENSITIVE);
    Matcher m = p.matcher(passwordStripped);
    if (m.find()) {
      return false;
    } else {
      return true;
    }

  }

  /**
   * Saves user and their data to the banks database file.
   * 
   * @param account      The account the user chose to edit
   * @param accountType  The account type chosen by the user.
   * @param odProtection The option the user chose if given the option for
   *                     overdraft protection
   */

  public void saveUser(String userName, String password, String accountType, boolean odProtection) {
    
    HashMap<String, HashMap> accId = new HashMap<String, HashMap>();

    HashMap<String, HashMap> userMap = new HashMap<>();

    HashMap<String, HashMap> accMap = new HashMap<String, HashMap>();

    HashMap<String, Object> checkingAccount = new HashMap<String, Object>();
    HashMap<String, Object> savingAccount = new HashMap<String, Object>();
    
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
    LocalDateTime now = LocalDateTime.now();

    if (accountType.equals("Checking")) {
      checkingAccount.put("balance", 0.0);
      checkingAccount.put("Overdraft_Protection", odProtection);
      checkingAccount.put("password", password);
      checkingAccount.put("date_created", dtf.format(now));
      // put date 
      long seed = System.currentTimeMillis();
      Random rng = new Random(seed);
      long number = rng.nextLong() % 100000;
      String id = String.valueOf(number);
      accId.put(id, checkingAccount);
      accMap.put("Checking", accId);
      accMap.put("Savings", null);

    } else {
      savingAccount.put("password", password);
      savingAccount.put("balance", 0.0);
      savingAccount.put("intrest", 0.47);
      savingAccount.put("date_created", dtf.format(now));
      long seed = System.currentTimeMillis();
      Random rng = new Random(seed);
      long number = rng.nextLong() % 100000;
      String id = String.valueOf(number);
      accId.put(id, savingAccount);
      accMap.put("Checking", null);
      accMap.put("Savings", accId);
    }

    userMap.put(userName, accMap);

    try {
      BufferedReader br = new BufferedReader(new FileReader("app/src/main/java/easybank/DB/accounts.txt"));
      if (br.readLine() == null) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
            new BufferedOutputStream(new FileOutputStream("app/src/main/java/easybank/DB/accounts.txt")))) {
          objectOutputStream.writeObject(userMap);
          objectOutputStream.close();
        } catch (Throwable e) {
          e.printStackTrace();
        }
      } else {
        try (AppendingObjectOutputStream objectOutputStream = new AppendingObjectOutputStream(
            new BufferedOutputStream(new FileOutputStream("app/src/main/java/easybank/DB/accounts.txt", true)))) {
          objectOutputStream.writeObject(userMap);
          objectOutputStream.close();
        } catch (Throwable cause) {
          cause.printStackTrace();
        }
      }

    } catch (Throwable e) {
      e.printStackTrace();
    }

  }

  /**
   * Saves updated user data to the banks database file.
   * 
   * @see user
   * @see userIF
   */

  public void saveUpdatedUser() {
    userIF user = new User();
    HashMap<String, HashMap> userMap = user.getUserMap();
    String value = String.join("", userMap.keySet());
    boolean fileStart = true;
    boolean fileAppend = false;
    ArrayList<HashMap<String, HashMap>> tempList = new ArrayList<HashMap<String, HashMap>>();
    try {
      FileInputStream fis = new FileInputStream("app/src/main/java/easybank/DB/accounts.txt");
      ObjectInput ois = new ObjectInputStream(new BufferedInputStream(fis));
      boolean control = true;
      while (control) {
        HashMap<String, HashMap> test = (HashMap<String, HashMap>) ois.readObject();
        String value2 = String.join("", test.keySet());
        if (test != null) {
          if (value.equals(value2)) {
            tempList.add(userMap);
          } else {
            tempList.add(test);

          }
        } else {
          ois.close();
          control = false;
        }

      }

    } catch (Exception e) {
    }

    try {
      FileWriter fwOb = new FileWriter("app/src/main/java/easybank/DB/accounts.txt", false);
      PrintWriter pwOb = new PrintWriter(fwOb, false);
      pwOb.flush();
      pwOb.close();
      fwOb.close();
    } catch (Throwable e) {

    }

    while (fileStart) {
      try (ObjectOutput oos = new ObjectOutputStream(
          new BufferedOutputStream(new FileOutputStream("app/src/main/java/easybank/DB/accounts.txt", true)))) {
        for (HashMap<String, HashMap> map : tempList) {
          oos.writeObject(map);
          int index = tempList.indexOf(map);
          tempList.remove(index);
          fileStart = false;
          fileAppend = true;
        }

      } catch (IOException e) {

      }

    }

    try (AppendingObjectOutputStream objectOutputStream = new AppendingObjectOutputStream(
        new BufferedOutputStream(new FileOutputStream("app/src/main/java/easybank/DB/accounts.txt", true)))) {
      for (int i = 0; i < tempList.size(); i++) {
        HashMap<String, HashMap> map = tempList.get(i);
        objectOutputStream.writeObject(map);
        int index = tempList.indexOf(map);
        tempList.remove(index);
        i--;
      }

    } catch (IOException err) {

    }

  }

  /**
   * Read all users and their data from the banks database.
   * 
   * @see user
   * @see userIF
   */

  public void readUsers() {
    try {
      FileInputStream fis = new FileInputStream("app/src/main/java/easybank/DB/accounts.txt");
      ObjectInput ois = new ObjectInputStream(new BufferedInputStream(fis));
      boolean control = true;
      while (control) {
        HashMap<String, HashMap> test = (HashMap<String, HashMap>) ois.readObject();
        if (test != null) {
        } else {
          ois.close();
          control = false;
        }

      }

    } catch (Exception e) {

    }
  }

  /**
   * Handle check to make sure the usernsme entered by the user is not already
   * taken.
   * 
   */

  public boolean checkForDupe(String userName) {
    try {
      FileInputStream fis = new FileInputStream("app/src/main/java/easybank/DB/accounts.txt");
      ObjectInput ois = new ObjectInputStream(new BufferedInputStream(fis));
      boolean control = true;
      while (control) {
        HashMap<String, HashMap> test = (HashMap<String, HashMap>) ois.readObject();

        if (test != null) {
          String value = String.join("", test.keySet());
          if (value.equals(userName)) {
            return true;
          }
        } else {
          control = false;
        }

      }
    } catch (Exception e) {

    }
    return false;
  }

}
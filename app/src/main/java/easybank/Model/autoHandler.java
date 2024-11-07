package easybank.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class autoHandler{

  public void monthlyFee(){
    bankStatsHandler bSH = new bankStatsHandler();
    ArrayList<HashMap<String, HashMap>> accounts = bSH.viewAllAccounts();
    for (HashMap<String, HashMap> account : accounts){
      String userName = String.join("", account.keySet());
      
      Map checkingAccounts = (Map) account.get(userName).get("Checking");
      Map savingsAccounts = (Map) account.get(userName).get("Savings");
      // System.out.println("Test");
      // System.out.println(checkingAccounts);
      // System.out.println(savingsAccounts);
    }
  }
  
}
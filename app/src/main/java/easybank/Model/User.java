package easybank.Model;

import java.util.HashMap;

public class User implements userIF{

  private static HashMap<String, HashMap> userInfo;
  private static boolean isLoggedIn;

  @Override
  public HashMap<String, HashMap> getUserMap(){
    return userInfo;
  }
  @Override
  public boolean getIsLoggedIn(){
    return isLoggedIn;
  }

  @Override
  public void setUserMap(HashMap<String, HashMap> userMap){
    this.userInfo = userMap;
  }
  
  @Override
  public void setIsLoggedIn(boolean isLoggedIn){
    this.isLoggedIn = isLoggedIn;
  }

}
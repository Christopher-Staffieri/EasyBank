package easybank.Model;

import java.util.HashMap;

public interface userIF {
  public HashMap<String,HashMap> getUserMap();
  public boolean getIsLoggedIn();
  public void setIsLoggedIn(boolean isLoggedIn);
  public void setUserMap(HashMap<String,HashMap> userMap);
}
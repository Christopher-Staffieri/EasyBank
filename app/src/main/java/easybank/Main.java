package easybank;

import javax.swing.SwingUtilities;
import easybank.Views.MainFrame;


public class Main{
  public static void main(String[] args){
    SwingUtilities.invokeLater(new Runnable(){
      public void run(){
        MainFrame main = new MainFrame();
      }
    });;
  }
}


/**
 * Modern banking application.
 * 
 * @author CJ Staffieri
 * @version 1.0
 * @since 1.0
 */
package easybank.Views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import easybank.Controllers.bankInfoController;
import easybank.Controllers.loginController;

public class allAccountsView extends JPanel{
  bankInfoController bIC = new bankInfoController();
  loginController lC = new loginController();
  JPanel current = new JPanel(new GridBagLayout());
  GridBagConstraints c = new GridBagConstraints();
  JButton backButton = new JButton("Back");
  JOptionPane notification = new JOptionPane();
  LineBorder mainBorder = new LineBorder(new Color(80, 82, 80));
  JButton activeButton = null;
  
  public allAccountsView(){
    current.addAncestorListener(new AncestorListener(){
      public void ancestorAdded(AncestorEvent event){
        Box accountBox = Box.createVerticalBox();
        accountBox.setBorder(mainBorder);
        ArrayList<HashMap<String, HashMap>> allAccounts = bIC.getAllAccounts();
      
        try{
           for (int x = 0; x< allAccounts.size(); x++){
             HashMap<String, HashMap> accountInfo = allAccounts.get(x);
             String accName = String.join("",accountInfo.keySet());
             JButton toAdd = new JButton(accName);
             toAdd.setBorder(mainBorder);
             toAdd.addActionListener(new ActionListener(){
               public void actionPerformed(ActionEvent e){
                 JButton button = (JButton)e.getSource();

                 button.setBorder(new LineBorder(new Color(110, 58, 117)));
                 if (activeButton == null){
                   activeButton = button;

                 }else{
                   activeButton.setBorder(mainBorder);
                   activeButton = button;


                 }
                 Map userInfo = (Map) accountInfo.get(accName);
                 if (userInfo.get("Savings") != null && userInfo.get("Checking") != null){
                   Map savingsInfo = (Map) userInfo.get("Savings");
                   Map checkingInfo = (Map) userInfo.get("Checking");
                   System.out.println(savingsInfo);
                   notification.showMessageDialog(current, "All of " + e.getActionCommand() + "'s Checking info" + "\n" + checkingInfo + "All of " + e.getActionCommand() + "'s Savings info" + "\n" + savingsInfo);
                 }else if (userInfo.get("Savings") != null && userInfo.get("Checking") == null){
                   Map savingsInfo = (Map) userInfo.get("Savings");
                   notification.showMessageDialog(current, "All of " + e.getActionCommand() + "'s Savings info" + "\n" + savingsInfo);
                 }else if (userInfo.get("Savings") == null && userInfo.get("Checking") != null){
                   Map checkingInfo = (Map) userInfo.get("Checking");
                   notification.showMessageDialog(current, "All of " + e.getActionCommand() + "'s Checking info" + "\n" + checkingInfo);
                   
                 }
                 
               }
             });
              accountBox.add(toAdd);
           }
        }catch (Throwable e){
          
        }
        
        JScrollPane accountsHolder = new JScrollPane(accountBox);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(5, 5, 5, 5);
        current.add(accountsHolder, c);
        
        current.repaint();
        current.revalidate();
      }

      public void ancestorMoved(AncestorEvent event){
      }

      public void ancestorRemoved(AncestorEvent event){
        
      }

      
      
    });
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 3;
    c.insets = new Insets(10, 5, 5, 5);
    current.add(backButton, c);
    
    setBackground(new Color(29, 30, 33));
    current.setBackground(new Color(29, 30, 33));
    add(current);
  }

  public void backButtonPressed(ActionListener e){
    backButton.addActionListener(e);
  }
}
  
  

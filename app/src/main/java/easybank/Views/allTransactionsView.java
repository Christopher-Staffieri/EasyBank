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

public class allTransactionsView extends JPanel{
  JPanel current = new JPanel(new GridBagLayout());
  GridBagConstraints c = new GridBagConstraints();
  JOptionPane notification = new JOptionPane();
  
  bankInfoController bIC = new bankInfoController();
  loginController lC = new loginController();
  
  LineBorder mainBorder = new LineBorder(new Color(80, 82, 80));
  
  JButton backButton = new JButton("Back");
  JButton activeButton = null;
  
  public allTransactionsView(){

    current.addAncestorListener(new AncestorListener(){
      public void ancestorAdded(AncestorEvent event){
        Box depositsBox = Box.createVerticalBox();
        Box withdrawlsBox = Box.createVerticalBox();
        ArrayList<HashMap<String, HashMap>> allDeposits = bIC.getAllDeposits();
        ArrayList<HashMap<String, HashMap>> allWithdrawls = bIC.getAllWithdrawls();
        try{
          for (int x = 0; x< allDeposits.size(); x++){
            HashMap<String, HashMap> depoInfo = allDeposits.get(x);
            String accName = String.join("",depoInfo.keySet());
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
                Map userInfo = (Map) depoInfo.get(e.getActionCommand());
                
                notification.showMessageDialog(current, "Payee: " + userInfo.get("payee") + "\nPayor: " + userInfo.get("payor") + "\nAmount Paid: " + userInfo.get("amount") + "\nAccount Type: " + userInfo.get("accountType") + "\nBank Name: " + userInfo.get("bankName") + "\nBank Routing #: " + userInfo.get("bankRouting"), "Transaction Details", JOptionPane.INFORMATION_MESSAGE);
                
              }
            });
            depositsBox.add(toAdd);
            System.out.println(depoInfo);

          }
        }catch (Throwable e){
          
        }

        JScrollPane depositsHolder = new JScrollPane(depositsBox);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.gridy = 2;
        c.insets = new Insets(5, 5, 5, 5);
        current.add(depositsHolder, c);

        current.repaint();
        current.revalidate();
        
      }

      public void ancestorMoved(AncestorEvent event){
        
      }

      public void ancestorRemoved(AncestorEvent event){
        
      }

      
    });

    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 3;
    c.gridy = 3;
    c.insets = new Insets(5, 5, 5, 5);
    current.add(backButton, c);
    
   setBackground(new Color(29, 30, 33));
   current.setBackground(new Color(29, 30, 33));
   add(current);
  }

  public void backButtonPressed(ActionListener e){
    backButton.addActionListener(e);
  }
  
}
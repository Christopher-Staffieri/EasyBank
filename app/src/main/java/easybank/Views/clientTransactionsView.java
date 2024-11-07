package easybank.Views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import easybank.Controllers.loginController;
import easybank.Controllers.transactionsController;

public class clientTransactionsView extends JPanel{
  JPanel current = new JPanel(new GridBagLayout());
  GridBagConstraints c = new GridBagConstraints();
  JScrollPane depositsPane = new JScrollPane();
  JScrollPane withdrawlPane = new JScrollPane();
  transactionsController tC = new transactionsController();
  loginController lC = new loginController();
  JOptionPane notification = new JOptionPane();
  ArrayList<HashMap<String, HashMap>> userWithdrawls;
  ArrayList<HashMap<String, HashMap>> userDeposits;
  public clientTransactionsView(){
    current.addAncestorListener(new AncestorListener(){
      public void ancestorAdded(AncestorEvent event){
        if (lC.getIsLoggedIn()){
          userWithdrawls = tC.getUserWithDrawls();
          userDeposits = tC.getUserDeposits();
          Box withdrawlsBox = Box.createVerticalBox();
          for (int i = 0; i< userWithdrawls.size(); i++){
            HashMap<String, HashMap> withdrawlInfo = userWithdrawls.get(i);
            String accName = String.join("",withdrawlInfo.keySet());
            Map transDetails = (Map) withdrawlInfo.get(accName);
            System.out.println(transDetails + "test in view");
            String accNumber = (String) transDetails.get("payee");
            JButton toAdd = new JButton(accNumber);
            toAdd.addActionListener(new ActionListener(){
              public void actionPerformed(ActionEvent e){
                String odProt = "";
                String causedOD = "";
                if ((Boolean) transDetails.get("odProtectionUsed")){
                  odProt = "Yes";
                }else{
                  odProt = "No";
                }

                if ((Boolean) transDetails.get("caused-Overdraft")){
                  causedOD = "Yes";
                }else{
                  causedOD = "No";
                }
                
                notification.showMessageDialog(current, "Account #: " + transDetails.get("payee") + "\nAccount Type: " + transDetails.get("accountType") + "\nPayee: " + accName + "\nPayor: " + transDetails.get("payor") + "\nAmount Transfered: " + transDetails.get("amount") + "\nBalance After Transaction: " + transDetails.get("balance") + "\nUsed OD Protection: " + odProt + "\nCaused Overdraft: " + causedOD + "\nOD Fee: " + transDetails.get("overDraftFee") + "\nBank: " + transDetails.get("bankName") + "\nBank Routing: "+ transDetails.get("bankRouting"), "Transaction Info", JOptionPane.INFORMATION_MESSAGE);
              }
            });
            withdrawlsBox.add(toAdd);
          }
          System.out.println("Got to depo");
          Box depositsBox = Box.createVerticalBox();
          for (int x = 0; x< userDeposits.size(); x++){
            HashMap<String, HashMap> depositsInfo = userDeposits.get(x);
            String accName = String.join("",depositsInfo.keySet());
            Map transDetails = (Map) depositsInfo.get(accName);
            System.out.println(transDetails + "test in view depo");
            String accNumber = (String) transDetails.get("payor");
            JButton toAdd = new JButton(accNumber);
            toAdd.addActionListener(new ActionListener(){
              public void actionPerformed(ActionEvent e){

                notification.showMessageDialog(current, "Account #: " + transDetails.get("payor") + "\nAccount Type: " + transDetails.get("accountType") + "\nPayee: " + transDetails.get("payee") + "\nPayor: " + accName + "\nAmount Transfered: " + transDetails.get("amount") + "\nBalance After Transaction: " + transDetails.get("balance") + "\nBank: " + transDetails.get("bankName") + "\nBank Routing: "+ transDetails.get("bankRouting"), "Transaction Info", JOptionPane.INFORMATION_MESSAGE);
              }
            });
            depositsBox.add(toAdd);
          }
          JScrollPane withdrawlsScrollPane = new JScrollPane(withdrawlsBox);
          withdrawlsScrollPane.setMaximumSize(new Dimension(50, 1000));
          withdrawlsScrollPane.setPreferredSize(new Dimension(0, 150));
            
          
          JScrollPane depositsScrollPane = new JScrollPane(depositsBox);
          depositsScrollPane.setPreferredSize(new Dimension(0, 150));
          
          c.fill = GridBagConstraints.BOTH;
          c.gridx = 3;
          c.gridy = 1;
          c.insets = new Insets(5, 5, 78, 5);
          c.gridheight = 1;
          current.add(new JLabel("Checking Accounts"), c);
          c.fill = GridBagConstraints.HORIZONTAL;
          c.gridx = 3;
          c.gridy = 2;
          c.gridheight = 1;
          c.insets = new Insets(-100, 5, 5, 5);
          current.add(withdrawlsScrollPane, c);

          c.fill = GridBagConstraints.NONE;
          c.gridx = 4;
          c.gridy = 1;
          c.insets = new Insets(5, 5, 78, 5);
          c.gridheight = 1;
          current.add(new JLabel("Savings Accounts"), c);

          c.fill = GridBagConstraints.HORIZONTAL;
          c.gridx = 4;
          c.gridy = 2;
          c.gridheight = 1;
          c.insets = new Insets(-100, 5, 5, 5);
          current.add(depositsScrollPane, c);
          System.out.println("Called get checking in depo view");
          
        }

        
        current.repaint();
        current.revalidate();

       

      }
      
      public void ancestorRemoved(AncestorEvent event) {
        current.repaint();
        current.revalidate();

      }

      public void ancestorMoved(AncestorEvent event) {
        current.repaint();
        current.revalidate();

      }
    });


    current.setBackground(new Color(29, 30, 33));
    setBackground(new Color(29, 30, 33));
    add(current);
  }

  
}
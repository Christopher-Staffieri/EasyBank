package easybank.Views;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import easybank.Controllers.dataDisplayController;
import easybank.Controllers.loginController;
import easybank.Controllers.settingsController;
import easybank.Controllers.transferController;

public class editAccountView extends JPanel{
  JPanel current = new JPanel(new GridBagLayout());
  GridBagConstraints c = new GridBagConstraints();
  dataDisplayController dDC = new dataDisplayController();
  settingsController sC = new settingsController();
  loginController lC = new loginController();
  transferController tC = new transferController();
  JButton activeButton = null;
  LineBorder mainBorder = new LineBorder(new Color(80, 82, 80));
  JRadioButton protection = new JRadioButton("Protection");
  JRadioButton noProtection = new JRadioButton("No Protection");
  JButton submit = new JButton("Submit user");
  String chosenAccount;
  String chosenAccountType;
  boolean choseAccount = false;
  
  public editAccountView(){
    c.insets = new Insets(5, 5, 5, 5);
    current.addAncestorListener(new AncestorListener() {
      public void ancestorAdded(AncestorEvent event) {
        if (lC.getIsLoggedIn()) {
          String[] savingsAccounts = dDC.getSavingsAccounts();
          Box savingsBox = Box.createVerticalBox();
         
          try {
            // System.out.println(savingsAccounts.length + "Len of savng");
            for (int x = 0; x < savingsAccounts.length; x++) {
              // System.out.println(savingsAccounts[x]);
              JButton toAddSavings = new JButton(savingsAccounts[x]);
             
              toAddSavings.setBorder(mainBorder);
              toAddSavings.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                  // System.out.println("Ran action performed");
                  JButton button = (JButton)e.getSource();
                  

                  tC.setData(e.getActionCommand(), "Savings");
                  choseAccount = true;


                  button.setBorder(new LineBorder(new Color(110, 58, 117)));
                  if (activeButton == null){
                    activeButton = button;

                  }else{
                    activeButton.setBorder(mainBorder);
                    activeButton = button;


                  }

                }

              });

                savingsBox.add(toAddSavings);
            }

          } catch (Throwable e) {

          }

          JScrollPane savingsScrollPane = new JScrollPane(savingsBox);

          c.fill = GridBagConstraints.NONE;
          c.gridx = 1;
          c.gridy = 1;
          c.insets = new Insets(5, 150, 60, 5);
          c.gridheight = 2;
          current.add(new JLabel("Savings Accounts"), c);

          c.fill = GridBagConstraints.HORIZONTAL;
          c.gridx = 1;
          c.gridy = 2;
          c.gridheight = 1;
          c.insets = new Insets(40, 150, 20, 5);
          current.add(savingsScrollPane, c);
          // System.out.println("Called get checking in depo view");

          c.fill = GridBagConstraints.BOTH;
          c.gridx = 1;
          c.gridy = 3;
          c.insets = new Insets(5, 5, 5, 20);
          
          current.add(protection, c);
          
          c.fill = GridBagConstraints.BOTH;
          c.gridx = 2;
          c.gridy = 3;
          c.insets = new Insets(5, 20, 5, 5);
          
          current.add(noProtection, c);

          submit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
              if (choseAccount){
                if(protection.isSelected()){
                  // System.out.println("called");
                  sC.editAccount(tC.getAccount(), "Savings" ,true);
                }else if(noProtection.isSelected()){
                  sC.editAccount(tC.getAccount(), "Savings", false);
                }else if (protection.isSelected() && noProtection.isSelected()){
                  // show err notification
                  
                }else if (!protection.isSelected() && !noProtection.isSelected()){
                  // show err notification
                  
                }
              }
            }
          });
          
          c.fill = GridBagConstraints.BOTH;
          c.gridx = 1;
          c.gridy = 4;
          c.insets = new Insets(15, 5, 5, 5);
          current.add(submit, c);
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
  setBackground(new Color(29, 30, 33));
  add(current);
  }
  

  
  
}
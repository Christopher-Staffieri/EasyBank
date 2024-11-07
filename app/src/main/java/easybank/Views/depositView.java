package easybank.Views;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import easybank.Controllers.dataDisplayController;
import easybank.Controllers.loginController;
import easybank.Controllers.transferController;

public class depositView extends JPanel {
  JPanel current = new JPanel(new GridBagLayout());
  GridBagConstraints c = new GridBagConstraints();
  loginController lC = new loginController();
  transferController tC = new transferController();
  dataDisplayController dDC = new dataDisplayController();
  JButton depositButtn = new JButton("Deposit");
  JButton backButton = new JButton("Back");
  JTextField amount = new JTextField(10);
  JButton activeButton = null;
  LineBorder mainBorder = new LineBorder(new Color(80, 82, 80));
  String chosenAccount;
  String chosenAccountType;
  boolean choseAccount;

  public depositView() {
    c.insets = new Insets(5, 5, 5, 5);
    current.addAncestorListener(new AncestorListener() {
      public void ancestorAdded(AncestorEvent event) {
        if (lC.getIsLoggedIn()) {
          Box checkingPanel = Box.createVerticalBox();
          checkingPanel.setBackground(new Color(29, 30, 33));
          String[] checkingAccounts = dDC.getCheckingAccounts();
          String[] savingsAccounts = dDC.getSavingsAccounts();
          try {
            for (int i = 0; i < checkingAccounts.length; i++) {
              JButton toAdd = new JButton(checkingAccounts[i]);
              toAdd.setBorder(mainBorder);
              toAdd.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                  JButton button = (JButton)e.getSource();

                  button.setBorder(new LineBorder(new Color(110, 58, 117)));
                  if (activeButton == null){
                    activeButton = button;

                  }else{
                    activeButton.setBorder(mainBorder);
                    activeButton = button;
                  }
                  tC.setData(e.getActionCommand(), "Checking");
                  choseAccount = true;

                }

              });

              checkingPanel.add(toAdd);
            }
          } catch (Throwable e) {

          }

          Box savingsBox = Box.createVerticalBox();
          try {
            // System.out.println(savingsAccounts.length + "Len of savng");
            for (int x = 0; x < savingsAccounts.length; x++) {
              // System.out.println(savingsAccounts[x]);
              JButton toAddSavings = new JButton(savingsAccounts[x]);
              
              toAddSavings.setBorder(mainBorder);
              toAddSavings.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                  JButton button = (JButton)e.getSource();
          
                  button.setBorder(new LineBorder(new Color(110, 58, 117)));
                  if (activeButton == null){
                    activeButton = button;
                    
                  }else{
                    activeButton.setBorder(mainBorder);
                    activeButton = button;
                    
                    
                  }
                  tC.setData(e.getActionCommand(), "Savings");
                  choseAccount = true;

                }

              });

                savingsBox.add(toAddSavings);
            }

          } catch (Throwable e) {

          }

          JScrollPane savingsScrollPane = new JScrollPane(savingsBox);
          JScrollPane checkingScrollPane = new JScrollPane(checkingPanel);
          c.fill = GridBagConstraints.NONE;
          c.gridx = 3;
          c.gridy = 1;
          c.insets = new Insets(5, 5, 60, 5);
          c.gridheight = 2;
          current.add(new JLabel("Checking Accounts"), c);
          c.fill = GridBagConstraints.HORIZONTAL;
          c.gridx = 3;
          c.gridy = 2;
          c.gridheight = 1;
          c.insets = new Insets(5, 5, 5, 5);
          current.add(checkingScrollPane, c);

          c.fill = GridBagConstraints.NONE;
          c.gridx = 4;
          c.gridy = 1;
          c.insets = new Insets(5, 10, 60, 5);
          c.gridheight = 2;
          current.add(new JLabel("Savings Accounts"), c);

          c.fill = GridBagConstraints.HORIZONTAL;
          c.gridx = 4;
          c.gridy = 2;
          c.gridheight = 1;
          c.insets = new Insets(40, 10, 5, 5);
          current.add(savingsScrollPane, c);
          // System.out.println("Called get checking in depo view");
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

    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 1;
    c.gridy = 3;
    c.insets = new Insets(5, 5, 5, 5);
    current.add(amount, c);
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 1;
    c.gridy = 4;
    c.insets = new Insets(5, 5, 5, 5);
    depositButtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          // System.out.println(chosenAccount + "chosenacc");
          int value = Integer.parseInt(amount.getText());
          // System.out.println(chosenAccountType + "acc typ ein view");
          if (choseAccount) {
            tC.deposit(value);
          }

        } catch (Throwable err) {

        }

      }
    });
    current.add(depositButtn, c);

    c.gridx = 1;
    c.gridy = 5;
    c.fill = GridBagConstraints.HORIZONTAL;
    current.add(backButton, c);

    try {

      BufferedImage img = ImageIO.read(new File("app/src/main/resources/bank_logo.png"));
      ImageIcon icon = new ImageIcon(img);
      JLabel logo = new JLabel(icon);
      c.fill = GridBagConstraints.HORIZONTAL;
      c.insets = new Insets(10, 5, 5, 5);
      c.gridx = 1;
      c.gridy = 2;

      current.add(logo, c);
    } catch (IOException e) {

    }
    ;

    // Show all users checking and savings account
    setBackground(new Color(29, 30, 33));

    add(current);
  }

  public void backButtonPressed(ActionListener e) {
    backButton.addActionListener(e);
  }

}
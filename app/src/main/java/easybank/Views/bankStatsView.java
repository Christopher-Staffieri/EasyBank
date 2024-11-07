package easybank.Views;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import easybank.Controllers.bankInfoController;
import easybank.Controllers.loginController;

public class bankStatsView extends JPanel {
  bankInfoController bIC = new bankInfoController();
  loginController lC = new loginController();
  JPanel background = new JPanel(new GridBagLayout());
  JPanel current = new JPanel(new GridBagLayout());
  JLabel logo = new JLabel(new ImageIcon("app/src/main/resources/bank_logo.png"));
  GridBagConstraints c = new GridBagConstraints();
  JButton viewAccounts = new JButton("View Accounts");
  JButton transactionLogs = new JButton("View Transaction Logs");
  JButton backButton = new JButton("Back");
  JLabel bankName = new JLabel("Maze Bank");
  
  JLabel amountOfUsers = new JLabel("");
  JLabel totalBalance = new JLabel("");
  JLabel totalAccounts = new JLabel("hgjghj");
  JLabel totalTransactions = new JLabel("");

  public bankStatsView() {
    background.addAncestorListener(new AncestorListener() {
      public void ancestorAdded(AncestorEvent event) {

        totalBalance.setText("Banks Total Balance: " + String.valueOf(bIC.getTotalBalance()));
        amountOfUsers.setText("Amount Of Users: " + String.valueOf(bIC.getAmountOfAccounts()));
        totalAccounts.setText("Total # of sub accounts: " + String.valueOf(bIC.getAmountOfSubAccounts()));

      }

      public void ancestorMoved(AncestorEvent event) {

      }

      public void ancestorRemoved(AncestorEvent event) {

      }

    });
    bankName.setForeground(Color.WHITE);
    amountOfUsers.setForeground(Color.WHITE);
    totalBalance.setForeground(Color.WHITE);
    totalAccounts.setForeground(Color.WHITE);
    c.fill = GridBagConstraints.BOTH;
    c.gridx = 0;
    c.gridy = 0;
    background.add(logo, c);
    logo.setLayout(new GridBagLayout());

    
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = -1;
    c.gridy = 0;
    c.insets = new Insets(10, 5, 5, 0);
    logo.add(bankName, c);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = -1;
    c.gridy = 1;
    c.gridwidth = 2;
    c.insets = new Insets(10, 5, 5, 5);
    logo.add(amountOfUsers, c);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = -1;
    c.gridy = 2;
    c.insets = new Insets(10, 5, 5, 5);
    logo.add(totalBalance, c);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = -1;
    c.gridy = 3;
    c.insets = new Insets(10, 5, 5, 5);
    logo.add(totalAccounts, c);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 1;
    c.gridy = 0;
    c.insets = new Insets(10, 20, 5, 5);
    current.add(viewAccounts, c);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 1;
    c.gridy = 1;
    c.insets = new Insets(10, 20, 5, 5);
    current.add(transactionLogs, c);

    c.fill = GridBagConstraints.BOTH;
    c.gridx = 1;
    c.gridy = 3;
    c.insets = new Insets(10, 5, 5, 5);
    current.add(backButton);

    setBackground(new Color(29, 30, 33));
    background.setBackground(new Color(29, 30, 33));
    setLayout(new GridBagLayout());
    add(background);
    current.setOpaque(false);
    add(current);
    
  }

  public void viewAccountsPressed(ActionListener e) {
    viewAccounts.addActionListener(e);
  }

  public void transactionLogsPressed(ActionListener e) {
    transactionLogs.addActionListener(e);
  }

  public void backButtonPressed(ActionListener e) {
    backButton.addActionListener(e);
  }

}
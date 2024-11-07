package easybank.Views;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import easybank.Controllers.loginController;

public class MainFrame extends JFrame {
  public static String prevView;
  public MainFrame() {
    JFrame frame = new JFrame("Bank Application");
    loginController lC = new loginController();
    CardLayout cardLayout = new CardLayout();
    JPanel panelCont = new JPanel();
    ImageIcon logo = new ImageIcon("app/src/main/resources/bank_logo.png");

    // Views
    accountView av = new accountView();
    withdrawlView wV = new withdrawlView();
    depositView dV = new depositView();
    clientTransactionsView cTV = new clientTransactionsView();
    createExtraAccountView cEAV = new createExtraAccountView();
    settingsChoiceView sCV = new settingsChoiceView();
    editAccountView eAV = new editAccountView();
    bankStatsView bSV = new bankStatsView();
    createAccountView cAV = new createAccountView();
    loginView lV = new loginView();
    allTransactionsView aTV = new allTransactionsView();
    allAccountsView aAV = new allAccountsView();
    welcomeView welcomePage = new welcomeView();
    
    frame.setIconImage(logo.getImage());
    panelCont.setLayout(cardLayout);
   
    panelCont.add(welcomePage, "welcome-view");
    panelCont.add(cAV, "create-account-view");
    panelCont.add(lV, "login-view");
    panelCont.add(av, "account-view");
    panelCont.add(wV, "withdrawl-view");
    panelCont.add(dV, "deposit-view");
    panelCont.add(cTV, "transactions-view");
    panelCont.add(sCV, "settings-choice-view");
    panelCont.add(cEAV, "create-extra-account-view");
    panelCont.add(eAV, "edit-account-view");
    panelCont.add(bSV, "bank-stats-view");
    panelCont.add(aAV, "all-accounts-view");
    panelCont.add(aTV, "all-transactions-view");
    cardLayout.show(panelCont, "welcome-view");
  
    welcomePage.createPressed(e -> cardLayout.show(panelCont, "create-account-view"));
    welcomePage.loginPressed(e -> cardLayout.show(panelCont, "login-view"));
    welcomePage.viewAccountPressed(e -> cardLayout.show(panelCont, "account-view"));
    welcomePage.viewBankLogsPressed(e -> cardLayout.show(panelCont, "bank-stats-view"));

    av.withdrawlPressed(e -> cardLayout.show(panelCont, "withdrawl-view"));
    av.depositPressed(e -> cardLayout.show(panelCont, "deposit-view"));
    av.viewTransactionsPressed(e -> cardLayout.show(panelCont, "transactions-view"));
    av.accountSettingsPressed(e -> cardLayout.show(panelCont, "settings-choice-view"));
    av.bankLogsButtonPressed(e -> cardLayout.show(panelCont, "bank-stats-view"));
    av.backButtonPressed(e -> cardLayout.show(panelCont, "welcome-view"));
    
    sCV.createNewPressed(e -> cardLayout.show(panelCont, "create-extra-account-view"));
    sCV.backPressed(e -> cardLayout.show(panelCont, "account-view"));
    sCV.editPressed(e -> cardLayout.show(panelCont, "edit-account-view"));
  
    lV.backButtonPressed(e -> cardLayout.show(panelCont, "welcome-view"));

    cAV.backButtonPressed(e -> cardLayout.previous(panelCont));
    
    dV.backButtonPressed(e -> cardLayout.show(panelCont, "account-view"));
    
    wV.backPressed(e -> cardLayout.show(panelCont, "account-view"));
    
    bSV.viewAccountsPressed(e -> cardLayout.show(panelCont, "all-accounts-view"));
    bSV.transactionLogsPressed(e -> cardLayout.show(panelCont, "all-transactions-view"));
    bSV.backButtonPressed(e -> cardLayout.show(panelCont, "welcome-view"));
    
    aAV.backButtonPressed(e -> cardLayout.show(panelCont, "bank-stats-view"));
    
    aTV.backButtonPressed(e -> cardLayout.show(panelCont, "bank-stats-view"));
    
    frame.add(panelCont);
    frame.setPreferredSize(new Dimension(768, 1024));
    frame.setBackground(new Color(29, 30, 33));
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.pack();
    frame.setVisible(true);

  }

  public void setView(String view){
    prevView = view;
  }

}

/**
  * 1-welcomes user and asks to sign in or create an account
     
  * 2a-login to account

  * 2b-create account

  * 3-asks if they want to deposit, withdraw, or see transaction log

  * 4a-deposit money

  * 4b-withdraw money

  * 4c-see transaction log

  * 4d-transferring money between accounts

  * after each action is done it will return user to step 3, and repeat until
                        user logs out
*/
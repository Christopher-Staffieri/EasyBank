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

import easybank.Controllers.autoController;
import easybank.Controllers.loginController;

public class welcomeView extends JPanel {
  loginController lC = new loginController();
  JPanel background = new JPanel(new GridBagLayout());
  JPanel current = new JPanel(new GridBagLayout());
  JLabel logo = new JLabel(new ImageIcon("app/src/main/resources/bank_logo.png"));
  JButton b1 = new JButton("Create Account");
  JButton b2 = new JButton("Login");
  JButton b3 = new JButton("View Account");
  JButton b4 = new JButton("View Bank Logs");
  autoController aC = new autoController();

  GridBagConstraints c = new GridBagConstraints();

  public welcomeView() {
    aC.monthlyFee();

    background.addAncestorListener(new AncestorListener() {
      public void ancestorAdded(AncestorEvent event) {
        boolean loggedIn = lC.getIsLoggedIn();
        if (loggedIn) {
          logo.remove(b1);
          logo.remove(b2);
          
          c.fill = GridBagConstraints.BOTH;
          c.gridx = 0;
          c.gridy = 0;
          logo.add(b3, c);

          
        }
        background.repaint();
        background.revalidate();

      }

      public void ancestorRemoved(AncestorEvent event) {
        // System.out.println(lC.isLoggedIn);
        // System.out.println("2");

      }

      public void ancestorMoved(AncestorEvent event) {
        // System.out.println(lC.isLoggedIn);
        // System.out.println("3");

      }
    });
 
    c.fill = GridBagConstraints.BOTH;
    c.gridx = 0;
    c.gridy = 0;
    background.add(logo, c);

    logo.setLayout(new GridBagLayout());
    c.fill = GridBagConstraints.BOTH;
    c.gridx = 0;
    c.gridy = 0;
    c.insets = new Insets(5, 0, 0, 0);
    logo.add(b1, c);

    c.fill = GridBagConstraints.BOTH;
    c.gridx = 0;
    c.gridy = 1;
    c.insets = new Insets(5, 0, 0, 0);
    logo.add(b2, c);

    c.fill = GridBagConstraints.BOTH;
    c.gridx = 0;
    c.gridy = 2;
    c.insets = new Insets(5, 0, 0, 0);
    logo.add(b4, c);
  
    setBackground(new Color(29, 30, 33));
    background.setBackground(new Color(29, 30, 33));
    setLayout(new GridBagLayout());
    add(background);
   
  }

  public void createPressed(ActionListener Ac) {
    b1.addActionListener(Ac);
  }

  public void loginPressed(ActionListener Ac) {
    b2.addActionListener(Ac);
  }

  public void viewAccountPressed(ActionListener Ac){
    b3.addActionListener(Ac);
  }

  public void viewBankLogsPressed(ActionListener Ac){
    b4.addActionListener(Ac);
  }

}

package easybank.Views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class accountView extends JPanel{
  JPanel current = new JPanel(new GridBagLayout());
  GridBagConstraints c = new GridBagConstraints();
 
  JButton withdrawlButton = new JButton("Withdrawl");
  JButton backButton = new JButton("Back");
  JButton depositButton = new JButton("Deposit");
  JButton viewTransactionsButton = new JButton("View Transactions");
  JButton accountSettingsButton = new JButton("Account Settings");
  JButton bankLogsButton = new JButton("View Bank Logs");
  
  public accountView(){
    try{
      BufferedImage img = ImageIO.read(new File("app/src/main/resources/bank_logo.png"));
      ImageIcon icon = new ImageIcon(img);
      JLabel logo = new JLabel(icon);
      
      JPanel overlayPanel = new JPanel();
      overlayPanel.setLayout(new BoxLayout(overlayPanel, BoxLayout.Y_AXIS));
      overlayPanel.setOpaque(false);


      overlayPanel.add(withdrawlButton);
      overlayPanel.add(Box.createVerticalStrut(5));
    
      overlayPanel.add(depositButton);
      overlayPanel.add(Box.createVerticalStrut(5));
     
      overlayPanel.add(viewTransactionsButton);
      overlayPanel.add(Box.createVerticalStrut(5));
      
      overlayPanel.add(accountSettingsButton);
      overlayPanel.add(Box.createVerticalStrut(5));

      overlayPanel.add(bankLogsButton);
      
      current.add(logo);
      current.add(overlayPanel);
    }catch (IOException e){
      e.printStackTrace();
    };



    
    withdrawlButton.setPreferredSize(new Dimension(252, 50));
    depositButton.setPreferredSize(new Dimension(252, 50));
    viewTransactionsButton.setPreferredSize(new Dimension(252, 50));
    accountSettingsButton.setPreferredSize(new Dimension(252, 50));
    bankLogsButton.setPreferredSize(new Dimension(252, 50));

    
  
    current.setBackground(new Color(29, 30, 33));
    setBackground(new Color(29, 30, 33));
    setLayout(new GridBagLayout());
    add(current);
  }

  public void withdrawlPressed(ActionListener e){
    withdrawlButton.addActionListener(e);
  }

  public void depositPressed(ActionListener e){
    depositButton.addActionListener(e);
  }

  public void viewTransactionsPressed(ActionListener e){
    viewTransactionsButton.addActionListener(e);
  }

  public void accountSettingsPressed(ActionListener e){
    accountSettingsButton.addActionListener(e);
  }

  public void bankLogsButtonPressed(ActionListener e){
    bankLogsButton.addActionListener(e);
  }

  public void backButtonPressed(ActionListener e){
    backButton.addActionListener(e);
  }

  
}
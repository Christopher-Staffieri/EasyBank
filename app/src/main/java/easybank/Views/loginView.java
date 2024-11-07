package easybank.Views;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import easybank.Controllers.loginController;

class loginView extends JPanel{
  boolean result = false;
  loginController lC = new loginController();
  JPanel current = new JPanel(new GridBagLayout());
  GridBagConstraints c = new GridBagConstraints();
  JOptionPane notification = new JOptionPane();
  JTextField userNamePrompt = new JTextField(32);
  JPasswordField passwordPrompt = new JPasswordField(32);
  JButton backButton = new JButton("Back");
  JRadioButton checking = new JRadioButton("Checking");
  JRadioButton savings = new JRadioButton("Savings");
  JButton loginButton = new JButton("Login");
  public loginView(){

    
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 1;
    c.gridy = 2;

    current.add(userNamePrompt, c);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 1;
    c.gridy = 3;

    current.add(passwordPrompt, c);
    
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 1;
    c.gridy = 1;
    current.add(checking, c);
    
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 2;
    c.gridy = 1;
    current.add(savings, c);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 1;
    c.gridy = 5;
    current.add(backButton, c);
    
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 1;
    c.gridy = 4;

    loginButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        String accType = "";
        if (checking.isSelected()){
          accType = "Checking";
        }else if (savings.isSelected()){
          accType = "SAvings";
        }
        boolean result = lC.loginControl(userNamePrompt.getText(), passwordPrompt.getPassword(), accType);
        if(result){
          notification.showMessageDialog(current, "Succesfully logged in!", "Logged in!", JOptionPane.INFORMATION_MESSAGE);
        }else{
          notification.showMessageDialog(current, "Failed to find user with those details did you forgeta character?", "Failed To Log In.", JOptionPane.ERROR_MESSAGE);
        }
      }
      
    });

    current.add(loginButton, c);
    current.setBackground(new Color(29, 30, 33));
    setBackground(new Color(29, 30, 33));
    add(current);
    
    
   }
  
  public void backButtonPressed(ActionListener e) {
    backButton.addActionListener(e);

  }
  

 }

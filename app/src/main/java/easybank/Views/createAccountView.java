package easybank.Views;

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


public class createAccountView extends JPanel {
  private String userName;
  private int count;
  private boolean odProtection;
  JPasswordField passwordPrompt = new JPasswordField(32);
  loginController lC = new loginController();
  JButton submit = new JButton("Submit user");
  JRadioButton checking = new JRadioButton("Checking");
  JRadioButton savings = new JRadioButton("Savings");
  JOptionPane notification = new JOptionPane();
  JPanel current = new JPanel(new GridBagLayout());
  JButton backButton = new JButton("Back");
  GridBagConstraints c = new GridBagConstraints();
  JTextField userNamePrompt = new JTextField(32);

  public createAccountView() {
    
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
    c.gridy = 4;
    current.add(checking);
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 2;
    c.gridy = 4;
    current.add(savings);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 1;
    c.gridy = 5;
    current.add(backButton, c);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 1;
    c.gridy = 1;
    submit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        count = 0;
        if (checking.isSelected() && savings.isSelected()) {
          if (count > 4) {
            notification.showMessageDialog(current, "Ok im choosing for you your terrible at this", "Stop being dumb",
                JOptionPane.ERROR_MESSAGE);
            checking.setEnabled(false);
          }
          notification.showMessageDialog(current, "Please only choose checking or savings", "Stop being dumb",
              JOptionPane.ERROR_MESSAGE);
          checking.setSelected(false);
          count++;
          
        }else if (checking.isSelected()){
          int test = JOptionPane.showConfirmDialog(current, "You have chosen checking would you like od protection", "please choose", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
          if (test == JOptionPane.YES_OPTION){
            // Notify the user they activated overdraft protection 
            odProtection = true;
          }else{
            odProtection = false;
          }
          String validAccount = lC.checkUser(userNamePrompt.getText(), passwordPrompt.getPassword(),"Checking", odProtection);
          if (validAccount.equals("dupe")){
            notification.showMessageDialog(current, "Sorry that user name already exists please try another one!", "Failed Account Creation",JOptionPane.ERROR_MESSAGE);
            userNamePrompt.setText("");
            }else if (validAccount.equals("user")){
              notification.showMessageDialog(current, "\tSorry that user name wont work.\nMake sure its greater than 4 characters and dosnt contain any special characters besides underscores, periods, or dashes!", "Failed Account Creation",JOptionPane.ERROR_MESSAGE);
            userNamePrompt.setText("");
            }else if (validAccount.equals("pass")){
              notification.showMessageDialog(current, "Sorry, that password wont work.\nMake sure your password is greater than 8 characters and includes:\na lower case and upper case letter a number and a special character (spaces are not allowed).", "Failed Account Creation",JOptionPane.ERROR_MESSAGE);
            passwordPrompt.setText("");
            }else{
              notification.showMessageDialog(current, "Succesfully created account!\nGo login!", "Created Account!",JOptionPane.INFORMATION_MESSAGE);
            passwordPrompt.setText("");
            userNamePrompt.setText("");
            checking.setSelected(false);
            }
          
        }else if (savings.isSelected()){
          odProtection = false;
          String validAccount = lC.checkUser(userNamePrompt.getText(), passwordPrompt.getPassword(),"Savings", odProtection);
          if (validAccount.equals("dupe")){
            notification.showMessageDialog(current, "Sorry that user name already exists please try another one!", "Failed Account Creation",JOptionPane.ERROR_MESSAGE);
            userNamePrompt.setText("");
            }else if (validAccount.equals("user")){
              notification.showMessageDialog(current, "\tSorry that user name wont work.\nMake sure its greater than 4 characters and dosnt contain any special characters besides underscores, periods, or dashes!", "Failed Account Creation",JOptionPane.ERROR_MESSAGE);
            userNamePrompt.setText("");
            }else if (validAccount.equals("pass")){
              notification.showMessageDialog(current, "Sorry, that password wont work.\nMake sure your password is greater than 8 characters and includes:\na lower case and upper case letter a number and a special character (spaces are not allowed).", "Failed Account Creation",JOptionPane.ERROR_MESSAGE);
            passwordPrompt.setText("");
            }else{
              notification.showMessageDialog(current, "Succesfully created account!\nGo login!", "Created Account!",JOptionPane.INFORMATION_MESSAGE);
            passwordPrompt.setText("");
            userNamePrompt.setText("");
            savings.setSelected(false);
            }
        }
      }
    });
    current.add(submit, c);

    add(current);
  }

  public void backButtonPressed(ActionListener e) {
    backButton.addActionListener(e);
    
  }

}
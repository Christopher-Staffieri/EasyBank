package easybank.Views;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


import easybank.Controllers.settingsController;

public class createExtraAccountView extends JPanel {
  JPanel current = new JPanel(new GridBagLayout());
  settingsController sC = new settingsController();
  GridBagConstraints c = new GridBagConstraints();
  JRadioButton checking = new JRadioButton("Checking");
  JRadioButton savings = new JRadioButton("Savings");
  JButton submit = new JButton("Submit");
  JOptionPane notification = new JOptionPane();

  public createExtraAccountView() {
    c.fill = GridBagConstraints.HORIZONTAL;
    c.insets = new Insets(10, 5, 0, 0);
    c.gridx = 1;
    c.gridy = 2;
    current.add(savings, c);
    c.fill = GridBagConstraints.NONE;
    c.insets = new Insets(10, 20, 0, 0);
    c.gridx = 2;
    c.gridy = 2;
    current.add(checking, c);

    c.fill = GridBagConstraints.NONE;
    c.insets = new Insets(10, 20, 0, 0);
    c.gridx = 1;
    c.gridy = 3;
    current.add(submit, c);

    submit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (checking.isSelected() && savings.isSelected()) {
          notification.showMessageDialog(current, "You must pick either savings or checking not both",
              "Too many account types", JOptionPane.ERROR_MESSAGE);
        } else if (checking.isSelected()) {
          sC.createExtraAccount("Checking", false);
          notification.showMessageDialog(current, "Succesfully added a new checking account to your main account",
              "Checking Account Created", JOptionPane.INFORMATION_MESSAGE);
        } else if (savings.isSelected()) {
          int response = notification.showConfirmDialog(current, "You have chosen a savings account would you like od prot",
              "please choose", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
          if (response == JOptionPane.YES_OPTION) {
            sC.createExtraAccount("Savings", true);
          } else {
            sC.createExtraAccount("Savings", false);
          }
        }
      }
    });

    setBackground(new Color(29, 30, 33));
    add(current);
  }
}
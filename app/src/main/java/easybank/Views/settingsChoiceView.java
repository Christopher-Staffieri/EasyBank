package easybank.Views;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class settingsChoiceView extends JPanel{
  JPanel current = new JPanel(new GridBagLayout());
  GridBagConstraints c = new GridBagConstraints();
  JButton createNewAccount = new JButton("Create New Account");
  JButton editAccount = new JButton("Edit Existing Accounts");
  JButton backButton = new JButton("Back");
  public settingsChoiceView(){
    
    c.fill = GridBagConstraints.HORIZONTAL;
    c.insets = new Insets(30, 5, 5, 5);
    c.gridx = 1;
    c.gridy = 3;
    current.add(createNewAccount, c);
    c.fill = GridBagConstraints.NONE;
    c.gridx = 1;
    c.gridy = 4;
    c.insets = new Insets(5, 5, 5, 5);
    current.add(editAccount, c);
    
    c.fill = GridBagConstraints.NONE;
    c.gridx = 1;
    c.gridy = 5;
    c.insets = new Insets(5, 5, 5, 5);
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

    };

    setBackground(new Color(29, 30, 33));
    add(current);
  }

  public void createNewPressed(ActionListener e){
    createNewAccount.addActionListener(e);
  }

  public void editPressed(ActionListener e){
    editAccount.addActionListener(e);
  }

  public void backPressed(ActionListener e){
    backButton.addActionListener(e);
  }

  

  
}
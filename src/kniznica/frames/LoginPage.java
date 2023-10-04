package kniznica.frames;
import java.util.HashMap;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class LoginPage extends  JFrame implements ActionListener {

           JFrame frame =new JFrame();
           JButton loginButton = new JButton("login") ;
           JButton resetButton = new JButton("reset");
           JTextField userIdField = new JTextField();
           JPasswordField userPasswordField = new JPasswordField();
           JLabel userIdLabel = new JLabel("user ID :");
           JLabel userPasswordLabel = new JLabel("user password :");
           JLabel massageLabel = new JLabel();
           
           
           HashMap<String,String> loginInfo = new HashMap<String,String>();    
           
           public LoginPage(HashMap<String,String> loginInfoOriginal) {
                 
                  loginInfo = loginInfoOriginal;

                 userIdLabel.setBounds(50,100,75,25);
                 userPasswordLabel.setBounds(50,150,75,25);
                 massageLabel.setBounds(125, 250, 250, 35);
                 massageLabel.setFont(new Font(null,Font.ITALIC,30));

                 userIdField.setBounds(125, 100, 200, 25);
                 userPasswordField.setBounds(125, 150, 200, 25);

                 loginButton.setBounds(125,200,100,25);
                 loginButton.setFocusable(false);
                 loginButton.addActionListener(this);
                 resetButton.setBounds(225,200,100,25);
                 resetButton.setFocusable(false);
                 resetButton.addActionListener(this);

                  frame.add(loginButton);
                  frame.add(resetButton);
                 frame.add(userPasswordField);
                  frame.add(userIdField);
                  frame.add(userIdLabel);
                  frame.add(userPasswordLabel);
                  frame.add(massageLabel);

                  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                  frame.setLocationRelativeTo(null);
                  frame.setSize(620,420);
                  frame.setLayout(null);
                  frame.setVisible(true);
            }

      @Override
      public void actionPerformed(ActionEvent e)  { 
            loginInfo.put("BRO","TIPO");         // možnosť aj tu vytvoriť prihlasovacie udaje   
           if (e.getSource()==resetButton) {
            userIdField.setText("") ;
            userPasswordField.setText("");
           }

           if (e.getSource()==loginButton) {                        
            String userID = userIdField.getText();
            String password = String.valueOf(userPasswordField.getPassword());

            if(loginInfo.containsKey(userID)){

                  if(loginInfo.get(userID).equals(password)){
                        massageLabel.setForeground(Color.green);
                        massageLabel.setText("Login successful .)");

                        SelectPanel select_panel = new SelectPanel();
                        try {
                              select_panel.wait(20000);
                        } catch (Exception selExc) {
                             selExc.printStackTrace();
                        }
                  
                  } else {
                        massageLabel.setForeground(Color.red);
                        massageLabel.setText("wrong password !!");
                  }
            } else {
                        massageLabel.setForeground(Color.red);
                        massageLabel.setText("user name not found !!!");
                  }

           }

      }
}

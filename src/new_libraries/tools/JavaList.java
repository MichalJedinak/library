package new_libraries.tools;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import new_libraries.SqlFunctions;

public class JavaList  extends JFrame{
      JLabel label = new JLabel("Skúška výberu dát s SQL databázy pomocou JList .");
      JScrollPane p = new JScrollPane();
      static JList<String> list ;
      JButton button = new JButton("Vyber udaje z databázy .");
      ArrayList<String> dataLists;
      
      public JavaList(){
            button.setPreferredSize(new Dimension(100, 55));
            button.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                        ukazUdajeZsqlDatabzyDoJlistu();
                        System.out.println("fungujem");                     
                     
                     list.addMouseListener(new MouseListener() {
                                    
                           @Override
                           public void mouseClicked(MouseEvent e) {
                                    String listItems = list.getSelectedValue();
                                    String[] items = listItems.split(" ");
                                    if(e.getClickCount()==1){
                                          if(listItems!=null){
                                                System.out.println(listItems); 
                                                System.out.println(items[2]);
                                                label.setForeground(Color.red);                                      
                                          }
                                    }
                              }
            
                              @Override
                              public void mousePressed(MouseEvent e) {
                              }
            
                              @Override
                              public void mouseReleased(MouseEvent e) {
                              }
            
                              @Override
                              public void mouseEntered(MouseEvent e) {
                              }
            
                              @Override
                              public void mouseExited(MouseEvent e) {
                              }
                        });
                  }
                  
            });
            p.setPreferredSize(new Dimension(400, 250));
            p.setLayout(new ScrollPaneLayout());
            list= new JList<>();
            list.setFont(new Font("Roboto", Font.BOLD, 15));
            list.setForeground(Color.green);
            list.setVisibleRowCount(9);
            
            p.add(list);
            p.setViewportView(list);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setLayout(new MigLayout());
            this.setSize(500, 350);
            this.add(label,"wrap,center");
            this.add(p,"gap left 25, wrap");
            this.add(button,"center");
            this.setVisible(true);
            this.repaint();


      }

      public void ukazUdajeZsqlDatabzyDoJlistu(){
            try {
                  Connection connection = DriverManager.getConnection(SqlFunctions.url, SqlFunctions.username, SqlFunctions.password);
                  Statement statement = connection.createStatement();
                  ResultSet resulset = statement.executeQuery("SELECT * FROM membership_cards;");
                  dataLists= new ArrayList<>();
                  DefaultListModel<String> defaultModel = new DefaultListModel<>(); 
                  while(resulset.next()){
                        int id=resulset.getInt(1);
                        String idString = String.valueOf(id);
                        String meno=    resulset.getString(2);
                        String release_date=String.valueOf(resulset.getDate(3));
                        String validity_to=String.valueOf(resulset.getDate(4));
                        if(resulset.getString("member_name")==null){
                              meno=" ";
                        }
                        if(resulset.getDate("release_date")==null){
                              release_date=" ";
                        }
                        if(resulset.getDate("validity_to")==null){
                              validity_to=" ";
                        }
                      dataLists.add(idString+"  "+meno+"  "+release_date+"  "+validity_to);
                    
                  }
                  list.setModel(defaultModel);
                  
                  for(String string : dataLists){
                        defaultModel.addElement(string);
                  }
                  connection.close();
                  statement.close();
                  resulset.close();
            } catch (Exception e) {
                  JOptionPane.showMessageDialog(null, e, "CHYBA", JOptionPane.ERROR_MESSAGE);;
            }
      }

      public static void main(String[] args) {
            new JavaList();
      }
      
}

package new_libraries.person;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import new_libraries.SqlFunctions;

public class DeletePersonPanel extends JPanel implements ActionListener {

      static JComboBox<String> combo ;
      JLabel label = new JLabel();
      JButton back = new JButton("Back");
      JButton delete = new JButton("Delete");

      JLabel label_1 = new JLabel("  Name");
      static JLabel field_1 = new JLabel();
      JLabel label_2 = new JLabel("  Midle Name");
      static JLabel field_2 = new JLabel();
      JLabel label_3 = new JLabel("  Last Name");
      static JLabel field_3 = new JLabel();
      JLabel label_4 = new JLabel("  Gender");
      static JLabel field_4 = new JLabel();
      JLabel label_5 = new JLabel("  Membership Card");
      static JLabel field_5 = new JLabel();

      ArrayList<JLabel> labelList = new ArrayList<>();
      ArrayList<JLabel> labelList_2 = new ArrayList<>();
      public DeletePersonPanel(){            
            this.setBounds(100, 50, 350, 200);
            //this.setBackground(new Color(255, 218 ,185));
            this.setLayout(new MigLayout());
            combo= new JComboBox<String>();
            setComboBoxData();
            combo.addActionListener(this);
            combo.setFont(new Font("New Time Roman",Font.BOLD,16));
            label.setBackground(Color.white);
            label.setForeground(Color.black);
            label.setFont(new Font("Consolas", Font.ITALIC, 18));
            label.setPreferredSize(new Dimension(300, 80));
            combo.setPreferredSize(new Dimension(200, 40)); 
            back.setPreferredSize(new Dimension(50, 40));
            delete.setPreferredSize(new Dimension(50, 40));
            this.add(label,"wrap");
            this.add(combo,"wrap");

            this.add(label_1);
            this.add(field_1,"span 2,wrap");
            this.add(label_2);
            this.add(field_2,"span 2,wrap");
            this.add(label_3);
            this.add(field_3,"span 2,wrap");
            this.add(label_4);
            this.add(field_4,"span 2,wrap");
            this.add(label_5);
            this.add(field_5,"span 2,wrap");

            this.add(delete,"span 3");
            this.add(back,"wrap");
            ArrayList<JButton> eventList = new ArrayList<>();
            eventList.add(back);eventList.add(delete);
            for(JButton button : eventList){
                  button.addActionListener(this);
            }   
            back.setBackground(Color.lightGray);  
            delete.setBackground(Color.red);  
            labelList.add(label_1); labelList.add(label_2); labelList.add(label_3);labelList.add(label_4);labelList.add(label_5);
            for(JLabel l : labelList){
                  l.setForeground(Color.black);
                  l.setFont(new Font("", Font.BOLD, 16));
            }
            labelList_2.add(field_1);labelList_2.add(field_2);labelList_2.add(field_3);labelList_2.add(field_4);labelList_2.add(field_5);
            for(JLabel ll : labelList_2){
                  ll.setForeground(new Color(220,20,60));
                  ll.setFont(new Font("Roboto", Font.ITALIC, 18));
            }

      }

      //// metoda pre data s databse do comboboxu
      public static void setComboBoxData(){
            try {
                  String query = "SELECT id,name,midle_name,last_name,gender,membership_number FROM persons;";
                  Connection connection = DriverManager.getConnection(SqlFunctions.url, SqlFunctions.username,SqlFunctions.password);
                  java.sql.Statement statement = connection.createStatement();
                  ResultSet resultSet = statement.executeQuery(query);

                  combo = new JComboBox<String>();
                  while (resultSet.next()) {
                        int i = resultSet.getInt(1);
                        String id = String.valueOf(i);
                        String name = resultSet.getString(2);
                        String midle = resultSet.getString(3);
                        String last = resultSet.getString(4);
                        String gender = resultSet.getString(5);
                        int number = resultSet.getInt(6);
                        String card = String.valueOf(number);
                      
                        combo.addItem(id+" "+name+" "+midle+" "+last+" "+gender+" "+card);
                  }
               //   System.out.println(combo.getSelectedItem());
            } catch (Exception e) {
                  e.printStackTrace();System.err.println("dačo sa nám pojebalo nebolo to veru málo");
            }
      }

       public static void refreshDeleteComboBoxData(){
            try {
                  String query = "SELECT id,name,midle_name,last_name,gender,membership_number FROM persons;";
                  Connection connection = DriverManager.getConnection(SqlFunctions.url, SqlFunctions.username,SqlFunctions.password);
                  java.sql.Statement statement = connection.createStatement();
                  ResultSet resultSet = statement.executeQuery(query);

                  DefaultComboBoxModel<String> dcbm = new DefaultComboBoxModel<>();
                  while (resultSet.next()) {
                        int i = resultSet.getInt(1);
                        String id = String.valueOf(i);
                        String name = resultSet.getString(2);
                        String midle = resultSet.getString(3);
                        String last = resultSet.getString(4);
                        String gender = resultSet.getString(5);
                        int number = resultSet.getInt(6);
                        String card = String.valueOf(number);
                        dcbm.addElement(id+" "+name+" "+midle+" "+last+" "+gender+" "+card);
                  }
                  combo.setModel(dcbm);
                  connection.close();
                //  System.out.println(combo.getSelectedItem());
            } catch (Exception e) {
                  e.printStackTrace();System.err.println("Dáta sa neaktualizovali v combo boxe");
            }
      }
      public void deletePersonFromDatabaseTable(){
            try {
                  String selectedValues =(String) combo.getSelectedItem();
                  String [] comboValues = selectedValues.split(" ");
                  String selectedId = comboValues[0];
                  String deletePersonFromDatabase = " DELETE FROM persons WHERE id ="+selectedId;
                  Connection connection = DriverManager.getConnection(SqlFunctions.url, SqlFunctions.username,SqlFunctions.password);
                  java.sql.Statement statement = connection.createStatement();
                  String updateMemberShipCard = "UPDTAE membership_cards SET member_name =?,release_date =?,validity_to=? WHERE id ="+selectedId+";";
                  PreparedStatement prst = connection.prepareStatement(updateMemberShipCard);
                  prst.setNull(1,java.sql.Types.VARCHAR);
                  prst.setNull(2,java.sql.Types.DATE);
                  prst.setNull(3,java.sql.Types.DATE);

                  statement.executeUpdate(deletePersonFromDatabase);
                  JOptionPane.showConfirmDialog(null,"Osoba bola odstranená s SQL databazy","soba bola vymazaná !!",JOptionPane.PLAIN_MESSAGE);

                  statement.close();
            } catch (Exception e) {
                 e.printStackTrace();System.out.println("EROR  NEPODARILO SA ODSTRANIŤ OSOBU !!! ");
            }
      }
      @Override
      public void actionPerformed(ActionEvent e) {
            if(e.getSource()==combo){
                  String s = String.valueOf(combo.getSelectedItem() ); 
                  String [ ] values = s.split(" ");
                  field_1.setText(values[1]);
                  field_2.setText(values[2]);
                  field_3.setText(values[3]);
                  field_4.setText(values[4]);
                  field_5.setText(values[5]);
                  label.setText(s);
            }
            if(e.getSource()==back){
                  label.setText(" ");
                  refreshDeleteComboBoxData();
                  for(JLabel l : labelList){
                        l.setText("");
                  }
            }
            if(e.getSource()==delete){
                  label.setForeground(Color.red);
                  deletePersonFromDatabaseTable();
                  this.repaint();
            }
      }
      
}

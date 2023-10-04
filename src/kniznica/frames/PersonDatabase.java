package kniznica.frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import kniznica.JdbcMySQL;
import kniznica.frames.frame_componets.List_label;
import kniznica.objects.Person;

public class PersonDatabase  extends JFrame implements ActionListener{

      Border Border= new LineBorder(Color.BLACK,5);
      Border title_border= new LineBorder(Color.BLACK,2);
      Border labels_border = new LineBorder(Color.black,2);
      //Boolean true_false= false;
 
      //  panel s lablami  pre informacie o person 
      JPanel info_label_Panel;
      JLabel person_id_label;
      JLabel person_name_label;
      JLabel person_lastName_label;
      JLabel identity_card_label;
      // Panel s Text Field na zápis hodnôt do databazy
      JPanel text_fiel_panel;
      JTextField person_id_field;
      JTextField person_name_field;
      JTextField person_lastName_field;
      JTextField identity_card_field;
      JButton set_field_button;

      // String pre zadanie hodnot s fieldu do lable
      String person_id_string;
      String person_name_string;
      String person_lastName_string;
      String identity_card_string;
      // panel s udajmi vlozenymi do databazy s fieldov
      JPanel persons_database_panel;
      JPanel title_database_Panel;// panel pre nadpis databazy
      JLabel title_lable;// nadpis databazy
      // addd panel na prepis udajov z fields pre ich ulozenie do databazy v sql
      JPanel add_to_database_panel;
      JPanel add_label_panel;///////////////////
      JLabel add_person_id_label;
      JLabel add_person_name_label;
      JLabel add_person_lastName_label;
      JLabel add_identity_card_label;
      JPanel add_button_panel;///////////////////////
      JButton save_person_button;
      JButton deletePerson_button;
      //////////////////////////////////////////////
      JPanel list_database_panel    ;// select from preson cely list
      List_label list_label;

      public PersonDatabase(){
            //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setSize(808,908);
            //this.pack();
            this.setLocation( 800,10);
            this.setLayout(null);  
            this.setVisible(true);   
            ///////////////////////////////////////////////////////////////////////////////////////////
            //  INFO PANEL hlavička framu
            info_label_Panel= new JPanel();
            info_label_Panel.setBounds(0, 0, 800, 50);
            info_label_Panel.setLayout(null);
            info_label_Panel.setBackground(Color.darkGray);
            
            person_id_label = new JLabel();
            person_id_label.setText("person_id");
            person_id_label.setBounds(10,5,70,50);
            person_id_label.setPreferredSize(new Dimension(100,50));
            person_id_label.setForeground(Color.white);

            person_name_label = new JLabel();
            person_name_label.setText("person_name");
            person_name_label.setBounds(140,5,100,50);
            person_name_label.setPreferredSize(new Dimension(100,50));
            person_name_label.setForeground(Color.white);

            person_lastName_label = new JLabel();
            person_lastName_label.setText("person_lastname");
            person_lastName_label.setBounds(250,5,100,50);
            person_lastName_label.setPreferredSize(new Dimension(100,50));
            person_lastName_label.setForeground(Color.white);

            identity_card_label = new JLabel();
            identity_card_label.setText("identity_card");
            identity_card_label.setBounds(390,5,100,50);
            identity_card_label.setPreferredSize(new Dimension(100,50));
            identity_card_label.setForeground(Color.white);
            
            info_label_Panel.add(person_id_label);
            info_label_Panel.add(person_name_label);
            info_label_Panel.add(person_lastName_label) ;
            info_label_Panel.add(identity_card_label);
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
            // text fiel panel / id, name,lastname,carsd fields and setbutton
            text_fiel_panel= new JPanel();
            text_fiel_panel.setBounds(0, 50, 800, 50);
            text_fiel_panel.setBackground(Color.LIGHT_GRAY);
            text_fiel_panel.setLayout(null);

            person_id_field = new JTextField();
            person_id_field.setBounds(5,5,80,40);
            person_id_field.setForeground(new Color(248, 248 ,255));
            person_id_field.setBackground(new Color(105, 105 ,105));


            person_name_field = new JTextField();
            person_name_field.setBounds(130,5,100,40);
            person_name_field.setBackground(Color.PINK);
            person_name_field.setForeground(Color.black);

            person_lastName_field = new JTextField();
            person_lastName_field.setBounds(240,5,130,40);
            person_lastName_field.setBackground(Color.red);
            person_lastName_field.setForeground(Color.WHITE);

            identity_card_field = new JTextField();
            identity_card_field.setBounds(390,5,80,40);
            identity_card_field.setBackground(new Color(0,128,128));
            identity_card_field.setForeground(Color.WHITE);

            text_fiel_panel.add(identity_card_field);
            text_fiel_panel.add(person_lastName_field);
            text_fiel_panel.add(person_name_field);
            text_fiel_panel.add(person_id_field);
            // button pre nastavenie hodnot do databazy z text fieldu 
            set_field_button = new JButton("SET PERSON DATA");
            set_field_button.setBounds(640,5,150,40);
            set_field_button.setBackground(new Color(124, 252,0));
            set_field_button.setForeground(Color.WHITE);
            set_field_button.addActionListener(this);
            text_fiel_panel.add(set_field_button);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            // datatabase panel/  title_panel title_label/ add_to_database_panel  labels(id,name,lastname,card)
            persons_database_panel= new JPanel();
            persons_database_panel.setBounds(0,100, 800, 650);
            persons_database_panel.setBackground(new Color(255,20,147));
            persons_database_panel.setLayout(null);
            
            //title panel / title label
            title_database_Panel = new JPanel();
            title_database_Panel.setBounds(5, 2, 780, 60);
            title_database_Panel.setBackground(new Color(102,205,170));
            title_database_Panel.setForeground(Color.BLACK);
            title_database_Panel.setBorder(title_border);
            
            title_lable= new JLabel();
            title_lable.setText("PERSONS DATABASE");
            title_lable.setBounds(350, 20, 200, 50);
            
            title_database_Panel.add(title_lable) ;
            
            //ad to databsa panel / add_label_panel - labels(id.....)/buttonpanel-buttons
            add_to_database_panel = new JPanel();
            add_to_database_panel.setBounds(5, 65, 780, 60);
            //add_to_database_panel.setBorder(Border);
            add_to_database_panel.setLayout(null);

            add_label_panel = new JPanel();// labels panel for labels and buton panel 
            add_label_panel.setBounds(1, 5, 560, 50);
            add_label_panel.setBorder(labels_border);
            add_label_panel.setForeground(Color.BLACK);

            add_person_id_label = new JLabel();
            add_person_id_label.setText("");
            add_person_id_label.setBounds(5, 5, 80, 40);

            add_person_name_label = new JLabel();
            add_person_name_label.setText("");
            add_person_name_label.setBounds(105, 5, 100, 40);

            add_person_lastName_label = new JLabel();
            add_person_lastName_label.setText("");
            add_person_lastName_label.setBounds(205, 5, 120, 40);

            add_identity_card_label = new JLabel();
            add_identity_card_label.setText("");
            add_identity_card_label.setBounds(325, 5, 80, 40);

            add_to_database_panel.add(add_person_id_label);
            add_to_database_panel.add(add_person_name_label);
            add_to_database_panel.add(add_person_lastName_label);
            add_to_database_panel.add(add_identity_card_label);

            add_button_panel = new JPanel();// button panel for buttons 
            add_button_panel.setBounds(575, 5, 200, 50);
            add_button_panel.setBorder(labels_border);
            add_button_panel.setLayout(new GridLayout(1,2));

            save_person_button = new JButton("SAVE PERSON");//save button 
            save_person_button.setBounds(5, 5, 90, 40);
            save_person_button.setBackground(Color.GREEN);
            save_person_button.addActionListener(this);

            deletePerson_button = new JButton("DELETE PERSON");// delete button
            deletePerson_button.setBounds(5, 5, 90, 40);
            deletePerson_button.setBackground(new Color(255,0,0));
            deletePerson_button.addActionListener(this);


            add_to_database_panel.add(add_label_panel);
            add_to_database_panel.add(add_button_panel);
            add_button_panel.add(save_person_button);
            add_button_panel.add(deletePerson_button);

            list_database_panel = new JPanel();/// list panek pe liest persons label
            list_database_panel.setBounds(200, 150,400,800);            
            //list_database_panel.setS
          

            // list_label = new List_label();
            // list_label.setText("");
            // list_label.setBounds(5,10,880,300);
            // list_label.setBackground(new Color(0,255,255));
            // list_label.setForeground(Color.black);
            // list_label.setFont(new Font("Serif", Font.PLAIN, 20));
            // list_database_panel.add(list_label);
            // list_label.setVisible(true_false);


            persons_database_panel.add(list_database_panel);
            //persons_database_panel.add(add_to_database_panel);
            persons_database_panel.add(title_database_Panel);

            this.add(persons_database_panel);
            this.add(text_fiel_panel);
            this.add(info_label_Panel);
      }

      @Override
      public void actionPerformed(ActionEvent e) {
            // nastavenie textu z text fields do strings
            person_id_string = person_id_field.getText();
            person_name_string= person_name_field.getText();
            person_lastName_string= person_lastName_field.getText();
            identity_card_string= identity_card_field.getText();

            if(e.getSource()==set_field_button){
                  //System.out.println("set_fiel_button_work");

                  persons_database_panel.add(add_to_database_panel);/// vloz panel do panelu

                  add_person_id_label.setText(person_id_string);///prepis text 
                  person_id_field.setText("");

                  add_person_name_label.setText(person_name_string);
                  person_name_field.setText("");

                  add_person_lastName_label.setText(person_lastName_string);
                  person_lastName_field.setText("");

                  add_identity_card_label.setText(identity_card_string);
                  identity_card_field.setText("");

                  // true_false =true;
                  // list_label.setVisible(true_false);
                  
                  try {
             Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kniznica", "root", "show_pussy8squirrel~hairy");
                  java.sql.Statement st= conn.createStatement();
                  java.sql.ResultSet set = st.executeQuery("SELECT * FROM persons");  

                  while (set.next()) { 
                              String column_1 = set.getString(1);  
                              String column_2 = set.getString(2);    
                              String column_3 = set.getString(3);
                              String column_4 = set.getString(4);
                              System.out.println(column_1+" " + column_2 +" "+ column_3 +" "+ column_4);


                        List_label list_label = new List_label();                        
                        list_label.setText("");                      
                        list_label.setBounds(25,10,200,600);                     
                        list_label.setBackground(new Color(0,255,255));
                        list_label.setForeground(Color.black);
                        list_label.setFont(new Font("Serif", Font.PLAIN, 25));
                        list_label.setVisible(true);    
                        list_database_panel.add(list_label);
                        list_label.setLayout(null);
                        list_database_panel.add(list_label);
                    
                        list_label.setText(column_1+"  "+column_2+"  "+column_3+"  "+column_4+"  ;\n"); 
                                                 
                        }
                  
            } catch (Exception eee) {
                  eee.printStackTrace();
            }
                 
                  // JdbcMySQL selectko = new JdbcMySQL();
                  // try {                          
                  //       selectko.select_from_person();                           
                  // } 
                  
                  // catch (ClassNotFoundException e1) {                                   
                  //       e1.printStackTrace();
                  // }
        
                  // list_label.setText("Tu potrebujem dostať text z select from persons kua");
                               

                  this.repaint();
            }

            //Person person = new Person(null);
      
            if (e.getSource() == save_person_button) {
                  //System.err.println("save_button_work");

                  String add_id_string = add_person_id_label.getText();// convert get text to int for id and card integer values
                  int add_id = Integer.parseInt(add_id_string);                                 
                  String add_card_string = add_identity_card_label.getText();
                  int add_card = Integer.parseInt(add_card_string);
                  System.out.println(add_id+"\n"+add_person_id_label.getText()+person_name_string);

                  /////// nova osoba  
                  Person add_person=
                  new Person(add_id,add_person_name_label.getText(),add_person_lastName_label.getText(), add_card);
                  System.out.println(add_person_name_label.getText()+" "+add_person_lastName_label.getText()+"  "+" identity card number is :" +add_card);
                 
                 add_person.setPerson_name(add_person_name_label.getText());
                 add_person.setPerson_lastName(add_person_lastName_label.getText());
                 add_person.setidentity_card(add_card);

                  //create new person from class Person 
                  // create new object Person from class Person
                  // new Person(add_person);  
                  // System.out.println(add_person +" "+add_person.getPerson_name()+ " i´m object person");
                  // String convert_add_person_to_string = String.valueOf(add_person.toString());
                  //convert_add_person_to_string.toString();
                 // System.out.println(convert_add_person_to_string +" i´m convert object person to string");
                  // implements metod personRegister from class Person  try connetc new person to database persons
                  // try {                 
                  // add_person.createPerson( add_person);
                  // } catch (ClassNotFoundException e1) { 
                  //       System.err.println();               
                  //       e1.printStackTrace();
                  // }

                  
            
            JdbcMySQL creatko = new JdbcMySQL();try {
                      creatko.createPerson(add_person); 
                     
            } catch (ClassNotFoundException e1) {                  
                  e1.printStackTrace();
            }
                  this.repaint();                
              
            }

            if(e.getSource()==deletePerson_button){
                  persons_database_panel.remove(add_to_database_panel);

                  String add_id_string = add_person_id_label.getText();// convert get text to int for id and card integer values
                   int add_id = Integer.parseInt(add_id_string);
                  person_name_string = add_person_name_label.getText();
                  person_lastName_string =add_person_lastName_label.getText();                  
                  String add_card_string = add_identity_card_label.getText();
                  int add_card = Integer.parseInt(add_card_string);
                  Person add_person=
                  new Person(add_id,add_person_name_label.getText(),add_person_lastName_label.getText(),add_card);
                  System.out.println(add_id+" "+add_person_name_label.getText()+" "+add_person_lastName_label.getText()+"  "+" identity card number is :" +add_card);
                 add_person.setPersonid(add_id);
                 add_person.setPerson_name(add_person_name_label.getText());
                 add_person.setPerson_lastName(add_person_lastName_label.getText());
                 add_person.setidentity_card(add_card);
                 JdbcMySQL deletko = new JdbcMySQL();try {
                      deletko.deletePerson(add_person); 
            } catch (ClassNotFoundException e1) {                  
                  e1.printStackTrace();            }
                  this.repaint();              
            }
          
          
      }

}
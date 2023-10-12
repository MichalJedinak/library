package new_libraries.tools;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;
import new_libraries.SqlFunctions;

public class JavaTable  extends JFrame{

      /* Potrebujeme použiť JScrolPane  s ScrolPaneLayout()  aby sme v ňom zobrazili tabulku , 
       * ak ju chceme aj skrolovať 
      */
      JLabel label = new JLabel("Skúška výberu dát s SQL databázy pomocou JTable .");
      static JTable table ;
      JScrollPane p = new JScrollPane(table);
      JButton button = new JButton("Vyber udaje z databázy .");
      JLabel label_1 = new JLabel("ID :");
      JTextField field_1 = new JTextField(15);
      JButton bubakton= new JButton("Uprav hodnoty");
      
      public JavaTable(){
            button.setPreferredSize(new Dimension(100, 55));
            button.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                        ukazUdajeZsqlDatabzyDoJTable();
                        System.out.println("fungujem");                    
                     table.addMouseListener(new MouseAdapter() {
                                    
                           @Override
                           public void mouseClicked(MouseEvent e) {
                                    int listItems = table.getSelectedRow();
                                    table.getModel().getValueAt(listItems,2); 
                                    field_1.setText(table.getModel().getValueAt(listItems,0).toString());
                                    this.repaint();                                  
                                  System.out.println(listItems);
                              }
            
                              private void repaint() {
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
           // p.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            table= new JTable();
            table.setFont(new Font("Roboto", Font.BOLD, 15));
            table.setForeground(Color.red);
            table.setVisible(true);
            p.add(table);
            p.setViewportView(table);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setLayout(new MigLayout());
            this.setSize(900, 350);
            this.add(label,"wrap,center");
            this.add(p,"gap left 25 ");
            this.add(label_1,"span 2");
            this.add(field_1,"gap left 25,span 2");
            this.add(bubakton,"wrap");
            this.add(button,"center");
            this.setVisible(true);
            this.repaint();


      }

      public void ukazUdajeZsqlDatabzyDoJTable(){
            try {
                  Connection connection = DriverManager.getConnection(SqlFunctions.url, SqlFunctions.username, SqlFunctions.password);
                  Statement statement = connection.createStatement();
                  ResultSet resulset = statement.executeQuery("SELECT * FROM membership_cards;");
               
                  DefaultTableModel defaultModel = new DefaultTableModel();
                  /*  nastavíme pre defaul model údaje do stĺpcov ( table columns )
                   * pomocou ResulsetMetaData getMetaTada to nam umožní načítať stĺpce s tabulky aj s názvami
                  */
                  java.sql.ResultSetMetaData rsmd = resulset.getMetaData();
                  // potrebujeme zistiť kolko je stĺpcou v tabulke 
                  int cols = rsmd.getColumnCount(); //  do premennej cols si uložíme počet stlpcov
                  System.out.println(cols);
                  // teraz si vytvoríme pole Stringov v ktorom si zobrazíme mená 
                  //pre jednotlivé stĺpce cez for cyklus
                  String[] colName = new String[cols];
                  for(int i = 0; i<cols;i++)
                  colName[i]=rsmd.getColumnName(i+1);// použijeme get column Name s resulset meta data
                  defaultModel.setColumnIdentifiers(colName);//  default modelu nastavíme identifikované stĺpce
                 /*   cez while cyklus si z resulsetu vyberieme hodnoty do pola Stringov 
                  ktoré použijeme na nastavenie riadkov v defout modely */
                  while(resulset.next()){
                        /*Vytvoríme si premenné podľa toho čo berieme z tabulky  int string etc.. */
                        int id=resulset.getInt(1);
                        String idString = String.valueOf(id);
                        String meno=    resulset.getString(2);                      
                        String release_date=String.valueOf(resulset.getDate(3));
                        String validity_to=String.valueOf(resulset.getDate(4));
                        /*Ak by sme mali v tabulke aj prázdne hodnoty ošetríme to cez podmienku
                         * pre jednotlivé resulsety 
                         */
                        if(resulset.getString("member_name")==null){
                              meno=" ";
                        }
                        if(resulset.getDate("release_date")==null){
                              release_date=" ";
                        }
                        if(resulset.getDate("validity_to")==null){
                              validity_to=" ";
                        }
                        String[] dataLists= {idString,meno,release_date,validity_to};// pole Stringov
                        defaultModel.addRow(dataLists);// pridanie row do default modelu
                        
                  }

                  // int maxWidth = 0;
                  // int rowIndex = table.getSelectedRow();
                  // Object value = defaultModel.getValueAt(rowIndex, 0);
                  // int cellWidth = getCellTextWidth(value.toString());
                  // if(cellWidth>maxWidth){
                        //       maxWidth=cellWidth;
                  // }
                  table.setModel(defaultModel);// tabulke nastavíme model
                  for (int columnIndex = 0; columnIndex < defaultModel.getColumnCount(); columnIndex++) {
                        int maxWidth = 0;// reprezentuje max rozmer bunky v tabulke podla počtu char v bunke                         
                        for (int rowIndex = 0; rowIndex < defaultModel.getRowCount(); rowIndex++) {
                            Object value = defaultModel.getValueAt(rowIndex, columnIndex);// reprezentuje údaj v konkrétnej bunke ako objekt
                            int cellWidth = getCellTextWidth(value.toString());// objekt 
                            // Pokud je aktuální šířka buňky větší než dosud maximální, aktualizujte ji
                            if (cellWidth > maxWidth) {
                                maxWidth = cellWidth;
                            }
                        }
                        
                        // Nastavit preferovanou šířku sloupce na maximální šířku
                        table.getColumnModel().getColumn(columnIndex).setPreferredWidth(maxWidth);
                    }
                   /////  ďalší spôsob  úpravy  šírky bunky v tabulke pre konkrétny stĺpec 
                  // table.getColumnModel().getColumn(0).setPreferredWidth(6);
                  // table.getColumnModel().getColumn(1).setPreferredWidth(140);
                  // table.getColumnModel().getColumn(2).setPreferredWidth(50);
                  // table.getColumnModel().getColumn(3).setPreferredWidth(50);


                 /*A uzavrieme spojenie a čo treba */
                  connection.close();
                  statement.close();
                  resulset.close();
            } catch (Exception e) {
                  /* Uppozornenie pri zlyhaní načítania údajov s sql tabulky do JTabulky */
                  JOptionPane.showMessageDialog(null, e, "CHYBA", JOptionPane.ERROR_MESSAGE);;
            }
      }
      ///  int na získanie čísla s dlžky textu v bunke tabulky s objektu  
      private int getCellTextWidth(String text) {           
            return text.length() * 10; // vráti šírku zanku v px
        }

      public static void main(String[] args) {
            new JavaTable();
      }
      
}

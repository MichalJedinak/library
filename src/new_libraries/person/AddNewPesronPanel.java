package new_libraries.person;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
//import javax.swing.event.AncestorListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

// import javax.swing.border.Border;
// import javax.swing.border.LineBorder;
//import kniznica.objects.Gender;
//import kniznica.objects.Person;
import net.miginfocom.swing.MigLayout;
import new_libraries.SqlFunctions;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
//import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Scanner;

public class AddNewPesronPanel extends JFrame implements ActionListener {
      //  6 udajov - meno, str.meno,priezvysko, carta,pohlavie adresa
   public static   boolean usSelImg;
      String name;
      String midleName;
      String lastName;
      JMenuBar bar = new JMenuBar();
      JMenu menu = new JMenu("<html><h1>&#x2630;</h1></html>");
      JMenuItem item_1 = new JMenuItem("Add New Person");
      JMenuItem item_2 = new JMenuItem("Update Person");
      JMenuItem item_3 = new JMenuItem("Delete Person");
JFrame frame = new JFrame("Adding , Editing and Deleting a Person from the database .");

JPanel hlavny= new JPanel();

      JLayeredPane editPanel = new JLayeredPane();
      JPanel imagePanel = new JPanel();
      String[] i = {"man","woman"};
      JComboBox<Object> combo_1 = new JComboBox<>(i); //  pre pohlavie
      JPanel column = new JPanel();
      JPanel column_1 = new JPanel();
      JPanel colum_3 = new JPanel();//  pre comob_3 a ctyField
      JPanel colum_4 = new JPanel(); // pre combo_4 a postCode fieled
      //String [] comboData_2;
      static JComboBox<Object> combo_2 ; // pre členskú kartu
      JComboBox<String> combo_3; // pre mestá
      JComboBox<String> combo_4;//  pre post code
      public static JTextField nameField = new JTextField(16);
      JTextField midleNameField = new JTextField(16);
      JTextField lastNamField = new JTextField(16);
      JTextField genderFiedl = new JTextField(8);
      JTextField streetField = new JTextField(16);
      JTextField cityFiled = new JTextField(16);
      JTextField postCode = new JTextField(8);
      JTextField cardField = new JTextField(8);
      JLabel butLabel = new JLabel();
      JButton button = new JButton(" Create new Person ");
      static JLabel imageLabel;
      static ImageIcon icon =new ImageIcon("src\\new_libraries\\person\\resources\\strasiak.png");
      Image     iconImage = icon.getImage();
      static ImageIcon scaleImgIcon;
      JButton selectImg = new JButton("vyber foto");
      JButton cont = new JButton("Pokračuj");
      JButton back = new JButton("Späť");
      JPanel buttonPanel = new JPanel();
      JPanel customLabel = new JPanel(null);
      JLabel topLabel = new JLabel();
      JLabel bottomLabel = new JLabel();
      UpdatePerson up= new UpdatePerson();
      DeletePersonPanel del = new DeletePersonPanel();
      ArrayList<JTextField>fieldList = new ArrayList<>();
      ArrayList<JTextField> setingTextFileds ;
      ArrayList<JTextField> addAndRemoveFields;
      ArrayList<JComboBox> comboList = new ArrayList<>();
      int widht = 800;
      int height = 620;
      int scaleHeight =800;
      Border imgBorder = new LineBorder(Color.black,1);
      static JTextField area ; ///  prepis byte codu  s transfer blop f
      static JButton b;
      static  String inPutPath;
      JScrollPane pane;  // do funkcie načítania mena s fieldu
      JList<String> l;
      static JLabel byteLabel = new JLabel();
      JLabel label_1;
      JLabel label_3;
      JLabel label_4;
      JLabel label_8;

      public AddNewPesronPanel(){
            menu.setPreferredSize(new Dimension(550, 30));
            
            frame.setSize(widht, height); 
            ImageIcon frameLogoImg = new ImageIcon(getClass().getClassLoader().getResource("new_libraries\\person\\resources\\person.png"));
            // frame.setLayout(null);
            //frameLogoImg.setImageObserver(frame);
            frame.setVisible(true);
            frame.setIconImage(frameLogoImg.getImage());
           
            hlavny.setSize(800, 500); 
            hlavny.setBounds(25,5,800,500)  ;
            hlavny.setLayout(null);      
            editPanel.setPreferredSize(new Dimension(400, 400)); 
            editPanel.setBounds(5, 50, 400, 400);         
            editPanel.setLayout(new MigLayout("gapx 0 , gapy 18"));
            editPanel.setBackground(Color.darkGray);
            editPanel.setOpaque(true);
            imagePanel.setPreferredSize(new Dimension(350, 400));
            imagePanel.setBounds(405, 50, 350, 400);          
            imagePanel.setLayout(null);           
            imageLabel = new JLabel();
            imageLabel.setSize(150, 150);
            imageLabel.setBounds(75,55, 150, 150);
            imageLabel.setBorder(imgBorder);
            Image scaleImg_1 = iconImage.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
            scaleImgIcon = new ImageIcon(scaleImg_1);
            imageLabel.setIcon(scaleImgIcon);
            imagePanel.add(imageLabel);// label
            selectImg.setBounds(75, 205, 150, 35);
            imagePanel.add(selectImg);//button
            ////   pre výber obrázku do profilu 
            selectImg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                  String userHome = System.getProperty("user.home");                 
                  File picturesDirectory = new File(userHome, "Pictures");
                  JFileChooser fileChooser = new JFileChooser();
                  fileChooser.setCurrentDirectory(picturesDirectory);
                  int respons = fileChooser.showSaveDialog(null);
                  if(respons == JFileChooser.APPROVE_OPTION){                        
                        area = new JTextField("");
                        imagePanel.add(area);area.setBounds(30,245,250, 20);
                        b = new JButton("Potvrď výber");
                        b.setBounds(30, 270, 250, 23);
                        imagePanel.add(b);frame.repaint();
                        imageTransferToBlop();
                        b.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                        File file = fileChooser.getSelectedFile();
                        ImageIcon fileIcon = new ImageIcon(file.getPath());
                        // String fileString = fileChooser.getSelectedFile().toString();
                        // String fileExtention = fileString.substring(fileString.lastIndexOf(".") + 1);
                        Image img = fileIcon.getImage();
                        Image scaleImg_1 =img.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_FAST);
                        scaleImgIcon = new ImageIcon(scaleImg_1);
                        imageLabel.setIcon(scaleImgIcon);
                        frame.repaint();
                        usSelImg= true;
                        inPutPath = area.getText(); // alebo to uložime do fieldu a použijeme
                         //inPutPath = file.getPath();     //  inPutPath  je String
                        System.out.println(inPutPath+"  inPutPath s area textu "+ "or inputpath = "+file.getPath());
                        String outPutPath ="src\\new_libraries\\person\\resources\\outPutImg.txt";
                        String savePath ="src\\new_libraries\\person\\resources\\img.jpg";
                        try {
                              imgByteEncode(inPutPath, outPutPath);
                        } catch (Exception e1) {
                              
                              e1.printStackTrace();
                        }
                        try {
                              imgByteDecode(outPutPath, savePath);
                        } catch (Exception e1) {
                              
                              e1.printStackTrace();
                        }
                        imagePanel.remove(area);
                        imagePanel.remove(b);frame.repaint();
                  }
                  
                  });
                        area.setText(fileChooser.getSelectedFile().getAbsolutePath());
                        area.setEditable(false);
                        if(respons == JFileChooser.SAVE_DIALOG){
                              usSelImg=true;
                  System.out.println("ja neviem či vôbec fungujem !!!!§");
                  frame.repaint();
                  }                    
                  }
            }
                  
            });

            cont.setBounds(30,364,105,35);
            back.setBounds(175,364,105,35);
            imagePanel.add(cont);cont.addActionListener(this);
            imagePanel.add(back);back.addActionListener(this);
               
            ///  pomocné na udaje s sqlfunction do fieldu
            pane = new JScrollPane();
            pane.setBackground(Color.white);
            pane.setBounds(130, 32, 250, 65);
            pane.setLayout(new ScrollPaneLayout());
            l = new JList<>();
            l.setFont(new Font("Roboto", Font.PLAIN, 13));
            l.setForeground(Color.blue); 
           // l.setBounds(0, 0, 200,90);
            pane.add(l);
            l.setVisibleRowCount(20);
            pane.setViewportView(l);

            fieldList.add(nameField);fieldList.add(midleNameField);fieldList.add(lastNamField);
            fieldList.add(genderFiedl);fieldList.add(streetField);fieldList.add(cardField);
            fieldList.add(cityFiled);fieldList.add(postCode);
            ////  Nastevenie zmeny farby textu pre polia a labely cez mouseclick event    
            for(JTextField fields : fieldList ) {
                  fields.setEditable(true);
                  fields.setFont(new Font("Incosolata",Font.ITALIC,18)); 

                  fields.addMouseListener(new MouseAdapter(){                
                  @Override
                  public void mouseClicked(MouseEvent e) { 

                  fields.setText(""); 
                  // if(e.getSource()==cityFiled){
                  //       editPanel.add(combo_3,2);revalidate();
                  // }
                   if(e.getSource()==setingTextFileds.get(0)){
                         label_1.setForeground(Color.white);
                   }  
                   if(e.getSource()==setingTextFileds.get(1)) {
                         label_3.setForeground(Color.white);
                   }   
                   if(e.getSource()==setingTextFileds.get(2)) {
                         label_4.setForeground(Color.white);
                   }  
                   if(e.getSource()==setingTextFileds.get(3)) {
                         label_8.setForeground(Color.white);
                   }         
                  
                  if(e.getClickCount()==2)  {
                        for(JTextField f :setingTextFileds){
                              f.setText("povinný údaj");
                                    f.setForeground(Color.lightGray);
                                    if(e.getSource()==setingTextFileds.get(0)){
                                          label_1.setForeground(Color.white);
                                    }
                                    if(e.getSource()==setingTextFileds.get(1)){
                                          label_3.setForeground(Color.white);
                                    }
                                    if(e.getSource()==setingTextFileds.get(2)){
                                          label_4.setForeground(Color.white);
                                    }
                                    if(e.getSource()==setingTextFileds.get(3)){
                                          label_8.setForeground(Color.white);
                                    }
                        }                         
                  }     
            }
                  });  
                  ///  presun na Jlist po down evente a nasledne selektovanie z neho            
                  fields.addKeyListener(new KeyAdapter()  {

                        @Override
                        public void keyPressed(KeyEvent e){
                              if(e.getKeyCode()==KeyEvent.VK_DOWN){
                                    //  System.out.println("down event");
                                    l.requestFocusInWindow();
                              }   
                             if(e.getSource()==lastNamField)  {
                              if(!lastNamField.getText().isEmpty()){
                                    lastNamField.setForeground(Color.darkGray);
                                    label_3.setForeground(Color.green);
                              }
                             }                          
                        }
                  @Override
                  public void keyReleased(KeyEvent e) {                       

                  if(e.getSource()==nameField){                          
                   if(nameField.getText()!=null){   
                        editPanel.add(pane,1);  revalidate();  

                        if(e.getKeyCode()==KeyEvent.VK_ENTER){
                              editPanel.remove(pane);
                              label_1.setForeground(Color.green);
                              nameField.setForeground(Color.DARK_GRAY);

                        }
                         ///////////   vybranie mena na cliknutie myšou z JListu     
                        l.addMouseListener(new MouseAdapter( ){
                              @Override
                              public void mouseClicked(MouseEvent e) { 

                              if (e.getClickCount() == 1) { // Ak chcete reagovať iba na jedno kliknutie
                                    String selectedValue = l.getSelectedValue();
                                    if (selectedValue != null) {
                                          nameField.setText(selectedValue);
                                          label_1.setForeground(Color.green);
                                          editPanel.remove(pane);
                                          nameField.setForeground(Color.darkGray);  }
                                    }
                                    
                              }
                        });// addmouselist... mouseadapt..for Jlist l
                        ////   vybranie mena s JListu po down evente key pressed
                        l.addFocusListener(new FocusAdapter() {
                            @Override
                            public void focusLost(FocusEvent e) {
                              e.getComponent().repaint();

                              l.addKeyListener(new KeyAdapter() {
                                    @Override
                                    public void keyPressed(KeyEvent e){
                                          if(e.getKeyCode()==KeyEvent.VK_DOWN){
                                                
                                          }
                                          if(e.getKeyCode()==KeyEvent.VK_ENTER){
                                              //  System.out.println("stlačil som enter");
                                                String selectedValue = l.getSelectedValue();
                                                if (selectedValue != null) {
                                                      nameField.setText(selectedValue);
                                                      editPanel.remove(pane);
                                                }
                                                nameField.setFont(new Font("Incosolata",Font.PLAIN,19));
                                                if(!label_1.getText().isEmpty()){
                                                      label_1.setForeground(Color.green);
                                                }                                                
                                                nameField.setForeground(Color.darkGray);
                                          }
                                    }
                              });// keylisteners new keyEvent
                        }// focuslost
                              
                        });//addfocuslisteners new foc.adap..

                  }
                        frame.repaint();
                  }
               
                  }                 
              });  //  addKeylist.. for fields 
            }//   array  filed list fileds

            /// pridanie document listener pre pácu s s hladanim cez pismeno z zoznamu Jlist
            nameField.getDocument().addDocumentListener(new DocumentListener() {
                  @Override
                  public void insertUpdate(DocumentEvent e) {                        
                        updateList();frame.repaint();
                  }
                  @Override
                  public void removeUpdate(DocumentEvent e) {
                        updateList();frame.repaint();
                  }
                  @Override
                  public void changedUpdate(DocumentEvent e) {
                        updateList();frame.repaint();
                  }                 
            });

            combo_1.setPreferredSize(new Dimension(50, 30));
            combo_1.addActionListener(this);
            JLabel infoLabel = new JLabel("Zadajte údaje Osoby. * povynný údaj ");
             infoLabel.setBounds(90,1,330,45);
            label_1 = new JLabel("   Name *");
            JLabel label_2 = new JLabel("   Midle Name ");
            label_3 = new JLabel("   Last Name *");
            label_4 = new JLabel("   Gender *");
            JLabel label_5 = new JLabel("   Street");
            JLabel label_6 = new JLabel("   City");
            JLabel label_7 = new JLabel("   Post Code") ;    
            label_8 = new JLabel("   Card *");
            
            ArrayList<JLabel> labelList = new ArrayList<>();
            labelList.add(infoLabel);
            labelList.add(label_1);labelList.add(label_2);labelList.add(label_3);
            labelList.add(label_4);labelList.add(label_5);labelList.add(label_6);
            labelList.add(label_7);labelList.add(label_8);
            for(JLabel label : labelList){
                  label.setFont(new Font("Gothic",Font.ITALIC,18));
                  label.setForeground(Color.WHITE);
                  if(label==infoLabel){
                        label.setForeground(Color.BLACK);
                  }
            };         
            column.setLayout(new GridLayout(0,2));
            column_1.setLayout(new GridLayout(0,2));
            colum_3.setLayout(new GridLayout(2,0));
            colum_4.setLayout(new GridLayout(0,2));
            column.add(genderFiedl);
            column.add(combo_1);
            setCombo_2BoxData();
            combo_2.setPreferredSize(new Dimension(60, 30));
            combo_2.addActionListener(this);
            column_1.add(cardField);
            column_1.add(combo_2);
            setCombo_3BoxData();
            //combo_3.setPreferredSize(new Dimension(90, 30));
            combo_3.addActionListener(this);
            colum_3.add(cityFiled);
            colum_3.add(combo_3);
            setCombo_4BoxData();
            colum_4.add(postCode);
            colum_4.add(combo_4);
            //combo_4.setBounds(130,200, 70,30);
            combo_4.setPreferredSize(new Dimension(70, 30));
            combo_4.addActionListener(this);

            comboList.add(combo_1);comboList.add(combo_2);comboList.add(combo_3);comboList.add(combo_4);

            ///////////  pole fieldov v ktorých chcem robiť zmeny farby textu etc...
            setingTextFileds = new ArrayList<>();
            setingTextFileds.add(nameField);setingTextFileds.add(lastNamField);
            setingTextFileds.add(genderFiedl);setingTextFileds.add(cardField);
            for(JTextField f:setingTextFileds){              
                  f.setText("povinný údaj");
                  f.setForeground(Color.lightGray);
            }    
            //////////////////   pole fieldov nepovinných údajov  pre databaz  //////
            addAndRemoveFields = new ArrayList<>();
            addAndRemoveFields.add(midleNameField); addAndRemoveFields.add(streetField);
            addAndRemoveFields.add(cityFiled);  addAndRemoveFields.add(postCode);
           
            editPanel.add(label_1); 
            editPanel.add(nameField,"wrap");
            editPanel.add(label_2);
            editPanel.add(midleNameField,"wrap");
            editPanel.add(label_3);
            editPanel.add(lastNamField,"wrap");
            editPanel.add(label_4);
            editPanel.add(column,"wrap");// gender field + combo combo_1
            editPanel.add(label_5);
            editPanel.add(streetField,"wrap");
            editPanel.add(label_6);
            editPanel.add(colum_3,"wrap");
           // editPanel.add(combo_3,"gapx 125,span 2,wrap");
            editPanel.add(label_7);
            editPanel.add(colum_4,"wrap");
            //editPanel.add(combo_4,"wrap");
            editPanel.add(label_8);
            editPanel.add(column_1,"wrap");//  membership number + combo2
            
           // p.add(imagePanel);
            /////////   Button          
            // buttonPanel.setLayout(null);
            // buttonPanel.setBounds(0, 520, 500, 200);
            // customLabel.setBounds(10, 0, 500, 100);
            // topLabel.setForeground(Color.green);
            // bottomLabel.setForeground(Color.green);
            // customLabel.add(topLabel);
            // topLabel.setBounds(150, 0, 200, 45);
            // bottomLabel.setBounds(75, 55, 350, 45);
            // customLabel.add(bottomLabel);
            // button.setPreferredSize(new Dimension(500, 50));
            // button.setBounds(0, 0, 500, 50);
            // button.setBackground(new Color(0,255 ,255));
            // button.addActionListener(this);
           // buttonPanel.add(customLabel);
            // buttonPanel.add(button);
            // Border b = new LineBorder(Color.red,2);
            // buttonPanel.setBorder(b);
            button.setPreferredSize(new Dimension(400, 50));
            button.setBounds(5, 450, 400, 50);
            button.setBackground(new Color(0,255 ,255));
            button.addActionListener(this);
            button.setVisible(false);

            ArrayList<JMenuItem> itemList = new ArrayList<>();
            itemList.add(item_1);itemList.add(item_2);itemList.add(item_3);
            for(JMenuItem item : itemList){
                  item.addActionListener(this);
            }
            menu.add(item_1);menu.add(item_2);menu.add(item_3);
            bar.add(menu);
            frame.setJMenuBar(bar);
            hlavny.add(infoLabel);
            hlavny.add(editPanel);            
            hlavny.add(imagePanel);
            hlavny.add(button);
            frame.add(hlavny);
            //frame.add(buttonPanel);
            frame.add(up);
            frame.add(del);
            hlavny.setVisible(true);
            buttonPanel.setVisible(false);
            up.setVisible(false);
            del.setVisible(false);
            frame.setVisible(true);
            // frame.pack();
            // frame.pack();
            
      } //  AddNewPesronPanel() koniec 
      @Override
      public void actionPerformed(ActionEvent e) {
            ///////////    item menu  //////////
            if(e.getSource()==item_1){  /// add person item menu
                  SqlFunctions.refreschDatabase();
                  UpdatePerson.refreshComboBoxData();
                  DeletePersonPanel.refreshDeleteComboBoxData();
                  hlavny.setVisible(true);frame.repaint();del.setVisible(false);up.setVisible(false);
            }
            if(e.getSource()==item_2){  ///  update person item menu
                  SqlFunctions.refreschDatabase(); 
                  UpdatePerson.refreshComboBoxData();
                  DeletePersonPanel.refreshDeleteComboBoxData(); 
                //  UpdatePerson.refreshComboBoxData();               
                 up.setVisible(true);frame.repaint();del.setVisible(false);hlavny.setVisible(false);
            }
            if(e.getSource()==item_3){  ///  delete person item menu
                  SqlFunctions.refreschDatabase();
                  UpdatePerson.refreshComboBoxData();
                  DeletePersonPanel.refreshDeleteComboBoxData();
                  del.setVisible(true);frame.repaint();hlavny.setVisible(false);up.setVisible(false);
            }
            if(e.getSource()==combo_1){  //  cobbo box pre gender
                  String s = String.valueOf(combo_1.getSelectedItem());
                  genderFiedl.setText(s);
                  genderFiedl.setForeground(Color.darkGray);
                  label_4.setForeground(Color.green);
            }
            if(e.getSource()==combo_2){  ///   pre  členskú kartu 
                  String s = String.valueOf(combo_2.getSelectedItem());
                  cardField.setText(s);
                  cardField.setForeground(Color.darkGray);
                  label_8.setForeground(Color.green);
            }
            if(e.getSource()==combo_3){  //  pre mestá
                  String s = String.valueOf(combo_3.getSelectedItem());
                  cityFiled.setText(s);
                  cityFiled.setForeground(Color.darkGray);                 
            }
            if(e.getSource()==combo_4){  ///    pre poštové smer. čísla
                  String s = String.valueOf(combo_4.getSelectedItem());
                  postCode.setText(s);
                  postCode.setForeground(Color.darkGray);                 
            }

            /////////////   add person panel button 
            if(e.getSource()==cont){
                  boolean allFields = true;                
                  fieldList.remove(midleNameField); fieldList.remove(postCode);
                  fieldList.remove(streetField); fieldList.remove(cityFiled); 
                  
                  for(JTextField field :fieldList){
                        String s = field.getText();                        
                        if(s.isEmpty()|| s.equals("povinný údaj")){
                              allFields=false;
                              break;
                        }
                  }
                  if(allFields){
                        button.setVisible(true);
                        // buttonPanel.setVisible(true);
                        // frame.setSize(widht, scaleHeight);
                        // name= nameField.getText();midleName= midleNameField.getText(); lastName=lastNamField.getText();String g =genderFiedl.getText();
                        // topLabel.setText("<html><h2>Chystáte sa uložiť</h2></html>");
                        // bottomLabel.setText("<html><h2>"+name+" "+midleName+" "+lastName+" "+g+"</h2></html>");
                        for(JTextField a:addAndRemoveFields){
                            a.setBackground(new Color(95, 159, 160)); 
                        }
                        for(JComboBox c: comboList){
                              c.setVisible(false);c.setBackground(new Color(95, 159, 160));
                        }
                        for(JTextField field :fieldList){                                           
                              field.setBackground(new Color(95, 159, 160));                               
                          }
                        } else {
                             // frame.setSize(widht, height);
                             // buttonPanel.setVisible(false);
                             button.setVisible(false);
                             JOptionPane.showMessageDialog(null, "ZADAJTE POVINNÉ ÚDAJE", "Upozornenie !!!", JOptionPane.ERROR_MESSAGE);
                            
                           for(JTextField a:addAndRemoveFields){
                                 a.setBackground(Color.white); 
                           }
                              for(JTextField field :fieldList){
                                    field.setEditable(true);                                    
                              }
                        for(JComboBox c: comboList){
                              c.setVisible(true);c.setBackground(Color.white);
                        }
                  }
            }
            if(e.getSource()== back){
                 // buttonPanel.setVisible(false);
                 button.setVisible(false);
                fieldList.add(midleNameField); fieldList.add(postCode);
                fieldList.add(streetField); fieldList.add(cityFiled); 
                 // frame.setSize(widht, height);   //  ak by ostala varianta  s vysúvacím frame pre button
                 editPanel.remove(pane);
                 hlavny.repaint();
                  for(JTextField f : fieldList){
                        f.setText("");f.setEditable(true);f.setFocusable(true);f.setBackground(Color.white);
                  }
                  for(JComboBox c: comboList){
                      c.setVisible(true);c.setBackground(Color.white);
                  }
                  label_1.setForeground(Color.white);
                  label_3.setForeground(Color.white);
                  label_4.setForeground(Color.white);
                  label_8.setForeground(Color.white);
                  try {                       
                        imgByteDecode("src\\new_libraries\\person\\resources\\empty.txt", "src\\new_libraries\\person\\resources\\outPutImg.txt");
                        FileInputStream in = new FileInputStream(inPutPath);
                        byte[] emptyData = in.readAllBytes(); // dáta z prázdneho txt dokumentu 
                        String stringEmpty = Base64.getEncoder().encodeToString(emptyData);                     
                        FileWriter w = new FileWriter("");w.write(stringEmpty);w.close();in.close();
                        usSelImg=false;

                  } catch (Exception e1) {
                       
                        e1.printStackTrace();
                  }                  
                  for(JTextField f:setingTextFileds){
                        f.setText("povinný údaj");
                        f.setForeground(Color.lightGray);
                  }                
                  cleareByteFromTxtAndSetIconInImageLabel();
                  frame.repaint();                  
            }

            if(e.getSource()==button){  ///   button pre vytvorenmie osoby  v sql databaze
                  imageTransferToBlop();   ///  na prevod img do byte pre uložemie do tex pre následné načítanie              
                  String addname= nameField.getText();
                  String addMidleName = midleNameField.getText();
                  String addLastName = lastNamField.getText();
                  String addStreet = streetField.getText();
                  String addCity = cityFiled.getText();
                  String addPostCode = postCode.getText();
                
                  String gender = genderFiedl.getText();           
                  readByteFromTxt(); //  prečítanie zapísaneho byte kodu imageTransferToBlop
                  String byteImg = byteLabel.getText();                 
                  byte[] bytes = byteImg.getBytes();;      //  skrytý label odkial sa číta byte kod do stringu pre zápis do sql          
                  SqlFunctions.insertNewPersonToDatabaseTablePersons(addname,addMidleName,addLastName,gender,addStreet,addCity,addPostCode,bytes);
                  refreshComboBox();
                  setCombo_2BoxData();
                  this.repaint();this.revalidate();
                // String str = addname+" "+addMidleName+" "+lastName+" "+gender;
                 String str = "Person name : "+addname+"\n Person midle name : "+addMidleName+"\n Person last name : "+ addLastName+"\n Person gender : "+gender+"\n Person Adress : "+ addStreet+" City : "+addCity+"  postCode : "+addPostCode+"\n Person img : "+byteImg;
                 JOptionPane.showMessageDialog(null,str+":)","Osoba bola pridaná do databázy !", JOptionPane.INFORMATION_MESSAGE);
                 SqlFunctions.refreschDatabase();
                 UpdatePerson.refreshComboBoxData();
                 DeletePersonPanel.refreshDeleteComboBoxData();
                  label_1.setForeground(Color.white);
                  label_3.setForeground(Color.white);
                  label_4.setForeground(Color.white);
                  label_8.setForeground(Color.white);
                  fieldList.add(midleNameField); fieldList.add(postCode);
                  fieldList.add(streetField); fieldList.add(cityFiled); 
                 for(JTextField f :fieldList){ //  nastavíme fieldom prázdny text a bielu farbu
                  f.setEditable(true);f.setFocusable(true);f.setBackground(Color.white);
                  f.setText("");
                 }
                 for(JTextField f:setingTextFileds){ //  pre povinné nasatvíme čo treba
                        f.setText("povinný údaj");
                        f.setForeground(Color.lightGray);
                  }       
                 for(JComboBox c: comboList){ //  znovu zvyditeľníme combo boxs
                              c.setVisible(true);c.setBackground(Color.white);
                        }
                 cleareByteFromTxtAndSetIconInImageLabel();//  zmaže byte.txt a zmení icon v imagelabel
                // frame.setSize(widht,height);frame.remove(buttonPanel);
                 button.setVisible(false);
                 hlavny.repaint();;
                 frame.repaint();
                 
            }
      }

      //// vyber udajov do comboboxu s databazy
      public void setCombo_2BoxData(){
      try {
            String query = "SELECT id FROM membership_cards WHERE member_name IS NULL LIMIT 1;";
            Connection c = DriverManager.getConnection(SqlFunctions.url, SqlFunctions.username, SqlFunctions.password);
            Statement statement = c.createStatement();
            ResultSet resultSet = statement.executeQuery(query);    
            combo_2 = new JComboBox<Object>() ;
            while (resultSet.next()) {
                  int i = resultSet.getInt(1);                    
                  combo_2.addItem(i);                      
            }
          //  System.out.println(i);
            c.close();
            resultSet.close();
            } catch (Exception e) {
               e.printStackTrace();
               System.out.println("Data Not Corect !!!");
            }
      }
      /// znovu načítanie comboboxu po do defautnehoi boxu pre aktualne hodnoty
      public static void refreshComboBox() {
            try {
                String query = "SELECT id FROM membership_cards WHERE member_name IS NULL LIMIT 1;";
                Connection c = DriverManager.getConnection(SqlFunctions.url, SqlFunctions.username, SqlFunctions.password);
                java.sql.Statement statement = c.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
        
            //     ComboBoxModel<Object> model = new DefaultComboBoxModel<>();
                
            //     while (resultSet.next()) {
            //         String addData = resultSet.getString(1);
            //         ((DefaultComboBoxModel<Object>) model).addElement(addData);
            //     }
            DefaultComboBoxModel<Object> model = new DefaultComboBoxModel<>();
                  while (resultSet.next()) {
                        int i = resultSet.getInt(1);
                        model.addElement(i);
                  }
                c.close();
        
                combo_2.setModel(model); // Nastaví nový model do JComboBox
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Refresh is Not Correct !!!");
            }
        }

        public void setCombo_3BoxData(){
            String query = "SELECT mesto FROM city;";
            try {
                  Connection c = DriverManager.getConnection(SqlFunctions.url, SqlFunctions.username, SqlFunctions.password);
                  Statement s = c.createStatement();
                  ResultSet r = s.executeQuery(query);
                  combo_3 = new JComboBox<>();
                  while (r.next()) {
                        String addS = r.getString(1);
                        combo_3.addItem(addS);
                  }c.close();
                  
            } catch (Exception e) {
                  e.printStackTrace();
            }

        }
        public void refreshCombo_3BoxData() {
            String query = "SELECT mesto FROM city;";
            try (Connection c = DriverManager.getConnection(SqlFunctions.url, SqlFunctions.username, SqlFunctions.password)) {
                  Statement s = c.createStatement();
                  ResultSet r = s.executeQuery(query);
                  ComboBoxModel<String> cbm = new DefaultComboBoxModel<>();
                  while (r.next()) {
                        String addS = r.getString(1);
                        ((DefaultComboBoxModel<String>) cbm).addElement(addS);
                  }
                  c.close();
                  combo_3.setModel(cbm);
            } catch (SQLException e) {                
                  e.printStackTrace();
            }
        }

        ////////////////////// combo 4 ////////////////////////
        public void setCombo_4BoxData(){
            String query = "SELECT post_code FROM postCode;";
            try {
                  Connection c = DriverManager.getConnection(SqlFunctions.url, SqlFunctions.username, SqlFunctions.password);
                  Statement s = c.createStatement();
                  ResultSet r = s.executeQuery(query);
                  combo_4 = new JComboBox<>();
                  while (r.next()) {
                        String addS = r.getString(1);
                        combo_4.addItem(addS);
                  }c.close();
                  
            } catch (Exception e) {
                  e.printStackTrace();
            }

        }
        public void refreshCombo_4BoxData() {
            String query = "SELECT post_code FROM postCode;";
            try (Connection c = DriverManager.getConnection(SqlFunctions.url, SqlFunctions.username, SqlFunctions.password)) {
                  Statement s = c.createStatement();
                  ResultSet r = s.executeQuery(query);
                  ComboBoxModel<String> cbm = new DefaultComboBoxModel<>();
                  while (r.next()) {
                        String addS = r.getString(1);
                        ((DefaultComboBoxModel<String>) cbm).addElement(addS);
                  }
                  c.close();
                  combo_4.setModel(cbm);
            } catch (SQLException e) {                
                  e.printStackTrace();
            }
        }

        //////////  metoda na prepis obrázku do byte codu
        public static String imgByteEncode(String inPutPath , String outPutPath) throws Exception{
           FileInputStream in = new FileInputStream(inPutPath);
           byte[] imgData = in.readAllBytes();
           String stringImg = Base64.getEncoder().encodeToString(imgData);         

           FileWriter writer = new FileWriter(outPutPath);
           writer.write(stringImg);
           in.close();
           writer.close();           
           return stringImg;
        }
        //// metoda na prepis byte codu na  obrazok
        public static void imgByteDecode(String bytePath,String savePath) throws Exception{
            FileInputStream f = new FileInputStream(bytePath);
            byte[] b = Base64.getDecoder().decode(new String(f.readAllBytes()));
            FileOutputStream out= new FileOutputStream(savePath);
            out.write(b);
            f.close();out.close();
        }
 ///////  metoda na aktualizáciu Jlistu vybrateho z databazy cez funkciu showName.....
        private void updateList() {            
            DefaultListModel<String> d = new DefaultListModel<>();    
            // Naplniť d hodnotami z databázy alebo iného zdroja
            SqlFunctions.showNameFromDatabaseTablePersons(); // Aktualizujte SqlFunctions.x na základe hľadaného textu
            l.setModel(d); 
            for (String i : SqlFunctions.x) {
                d.addElement(i);
            }
 
        }
        public String sa(String s){
            return s;

        }
        //////  prevod  obrazku  na blop do sql 
        public static void imageTransferToBlop(){  
            File file;
            FileInputStream fis;
            BufferedImage bi;
            ByteArrayOutputStream baos; 
            byte[] byteImg;      
              try {
                  String filePath = "src\\new_libraries\\person\\resources\\img.jpg";
                  file = new File(filePath);
                  fis = new FileInputStream(file);
                  bi = ImageIO.read(fis);
                  baos = new ByteArrayOutputStream();
                  ImageIO.write(bi, "png,jpg,jpeg,gif", baos);
                  byteImg = baos.toByteArray();
                  FileWriter w= new FileWriter("src\\new_libraries\\person\\resources\\byte.txt");
                  String s = byteImg.toString();
                  System.out.println(s+"\n  To nad mnou je String s byteImg pola byte[]  iba pre provnanie s byte[]");
                  w.write(byteImg.toString());
                  w.close();                  
                  System.out.println("byteImg : "+byteImg +" || A byteImg.toString() zapísaní do byte.txt"+byteImg.toString());                 
                
            } catch (Exception e) {
                  System.out.println(e);
            }
        }
///////////    metoda  na nacitanie zapísaneneho byte kodu s img  pre blop do ssql inseretu
        public static void readByteFromTxt() {
            try {
                  File f = new File("src/new_libraries/person/resources/byte.txt");
                  Scanner scaner = new Scanner(f);
                  while (scaner.hasNext()) {
                        String s = scaner.next();                    
                        byteLabel.setText(s);                                  
                     System.out.println("Takto sa zapísal  byteImg  s byte[] do textu v  byteLabel :"+byteLabel.getText());
                  }scaner.close();
                  
            } catch (FileNotFoundException e) {
                  e.printStackTrace();
            }
        }

        ///  metoda na vymazanie udajov v byte.txt  a vratenie defaulneho obrazku do imalabele
        public static void cleareByteFromTxtAndSetIconInImageLabel(){
            File f =new File("src/new_libraries/person/resources/byte.txt");
            try {
                  FileWriter w = new FileWriter(f);
                  String emptyS = " ";
                  w.write(emptyS);
                  w.close();
                  Image i = icon.getImage();
                  Image small = i.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(),Image.SCALE_FAST);
                  scaleImgIcon =new ImageIcon(small);
                  imageLabel.setIcon(scaleImgIcon);
                 // System.out.println(scaleImgIcon.getImage().getSource().toString());
            } catch (IOException e) {                 
                  e.printStackTrace();
            }

        }


        /*/String sql = "INSERT INTO table_name (image_column) VALUES (?)";
PreparedStatement pstmt = conn.prepareStatement(sql);
pstmt.setBytes(1, imageBytes);
pstmt.executeUpdate(); 

String sql = "INSERT INTO table_name (image_column) VALUES (?)";
ByteArrayOutputStream baos = new ByteArrayOutputStream();
ImageIO.write(bi, "png", baos);
byte[] imageBytes = baos.toByteArray();
PreparedStatement pstmt = conn.prepareStatement(sql);
pstmt.setBytes(1, imageBytes);
pstmt.executeUpdate();*/
      public static void main(String[] args) throws Exception {
            new AddNewPesronPanel();         
          // AddNewPesronPanel.readByteFromTxt();
          // String inPutPath = "src\\new_libraries\\strasiak.png";
            // inPutPath = area.getText();
            // String outPutPath ="src\\new_libraries\\person\\resources\\outPutImg.txt";
            // String savePath ="src\\new_libraries\\person\\resources\\img.jpg";
            // imgByteEncode(inPutPath, outPutPath);
            // imgByteDecode(outPutPath, savePath);
      }
        
}


package new_libraries;
import javax.swing.*;
import javax.swing.event.AncestorListener;

import new_libraries.borrowed.BorrowedBookPanel_2;
import new_libraries.borrowed.BorrowedBooksPanel;
import new_libraries.person.AddNewPesronPanel;

import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;
public class MyFrame extends JFrame implements ActionListener{

// 1). Vytvoriť rám   ako samostatnú triedu 
// 2). Vytvoriť menu bar na výber konkretnej knižnice - { Martin , Sever, Zaturčie, Ľadoveň}
// 3). v menu bar menu , menu - { výber pre zápis novej knihym osoby, požičania, zoznam požičaných kníh, a zoznam knih v konkretnej knižnici}
// 4). Vytvoriť panel pre každý menu item  zatiaľ pre požičané knihy   
// 5).  new book - pole pre zápis knihy s údajmi o knihe
// 6).  new person pole s údajmi pre zápis o osobe ktorá sa zaregistrobvala do knižnice
// 7). new borrowed  polia  s výberom konkretnej knihy a konkretnej osoby a button na odolslanie údajov do databázy
// 8). bookInLibrary  pole v ktorom bude zoznam knih v danej knižnici
// 9).  

// Componets
JFrame frame = new JFrame(); // 1),
JPanel framePanel = new JPanel();  //  hlaný panel  v ktorom budú komponenty 
JMenuBar menuBar = new JMenuBar(); // 2).
JMenu menu       = new JMenu(); 
// knižnica MArtin 1
JMenu library_1 = new JMenu("library 1");  // 3).
JMenu library_2 = new JMenu("library 2"); // 3).
JMenu library_3 = new JMenu("library 3"); // 3).
JMenu library_4 = new JMenu("library 4"); // 3).
JMenuItem person = new JMenuItem("Person");
/////////////   library 1 Martin 1
JMenuItem newBook_1 = new JMenuItem("New Book");
JMenuItem newBorrowed_1 = new JMenuItem("New Borrowed");
JMenuItem borrowedBook_1 = new JMenuItem("Borrowed");
JMenuItem booksInLibrary_1 = new JMenuItem("Book in Library"); // 3).
//////  library 2 martin sever 
JMenuItem newBook_2 = new JMenuItem("New Book ");
JMenuItem newBorrowed_2 = new JMenuItem("New Borrowed");
JMenuItem borrowedBook_2 = new JMenuItem("Borrowed");
JMenuItem booksInLibrary_2 = new JMenuItem("Boo"); // 3).
////////////  libray 3  ladoven
JMenuItem newBook_3 = new JMenuItem("New Book");
JMenuItem newBorrowed_3 = new JMenuItem("New Borrowed");
JMenuItem borrowedBook_3 = new JMenuItem("Borrowed");
JMenuItem booksInLibrary_3 = new JMenuItem("Book in Library"); // 3)
//////// zaturcie    
JMenuItem newBook_4 = new JMenuItem("New Book");
JMenuItem newBorrowed_4 = new JMenuItem("New Borrowed");
JMenuItem borrowedBook_4 = new JMenuItem("Borrowed");
JMenuItem booksInLibrary_4 = new JMenuItem("Book in Library"); // 3)
///  Array Lists 
ArrayList<JMenuItem> newBorrowedItems = new ArrayList<>();
ArrayList<JMenuItem> newBookItems = new ArrayList<>();
ArrayList<JMenuItem> borrowedBookItem = new ArrayList<>();
ArrayList<JMenuItem> bookLibaryItem = new ArrayList<>();
BorrowedBooksPanel panel_1 ;
AddNewBookPanel addBookPanel ;
ImageIcon icon ;
JLabel frameLabel = new JLabel(icon);

public MyFrame(){
            // Create JFrame 1).
            
      menuBar.setBackground(new Color(192,192,192));         
      menu.setText("<html><h2>&#x2630;</h2></html>"); 
      menuBar.add(menu); // vloženie menu 
      menu.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                  framePanel.add(frameLabel);
            }

      });
      
      //////   vloženie sub item do menu
      menu.add(library_1);
      menu.add(library_2);
      menu.add(library_3);
      menu.add(library_4);
      menu.add(person);
      person.addActionListener(this);
      
      
      library_1.add(newBook_1);    
      library_1.add(newBorrowed_1);
      library_1.add(borrowedBook_1);
      library_1.add(booksInLibrary_1);
      library_2.add(newBook_2);    
      library_2.add(newBorrowed_2);
      library_2.add(borrowedBook_2);
      library_2.add(booksInLibrary_2);
      library_3.add(newBook_3);    
      library_3.add(newBorrowed_3);
      library_3.add(booksInLibrary_3);
      library_4.add(newBook_4);    
      library_4.add(newBorrowed_4);
      library_4.add(booksInLibrary_4);   
      
      framePanel.setSize(775,500);
      //framePanel.setBounds(0,0,775,500);
      framePanel.setBackground(new Color(100,149,237));
      icon= new ImageIcon("src\\new_libraries\\person\\resources\\libraries.png");
      Image image = icon.getImage();
      Image scI = image.getScaledInstance(500, 300, Image.SCALE_FAST);
      ImageIcon sI= new ImageIcon(scI);
      frameLabel.setPreferredSize(new Dimension(700, 300));
      frameLabel.setIcon(sI);
      framePanel.add(frameLabel);

      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(775,500);
      frame.setResizable(false); 
      frame.setLocationRelativeTo(null) ;
      frame.setBackground(new Color(100,149,237));
      frame.setJMenuBar(menuBar);
      frame.add(framePanel);
      ImageIcon i = new ImageIcon(getClass().getClassLoader().getResource("new_libraries\\person\\resources\\libraries.png"));
      frame.setIconImage(i.getImage()); 
      frame.setTitle("Library Project  : Adding , Editing , View Databse .....   :)");
      frame.setForeground(Color.white);  
      frame.setVisible(true);     
      
      ///////  add to  array listy pre pridanie Action listener a lahšie zapísanie pre podmienku Action event  
      newBookItems.add(newBook_1);newBookItems.add(newBook_2);newBookItems.add(newBook_3);newBookItems.add(newBook_4);     
      for (JMenuItem item : newBookItems ) {            
            item.addActionListener(this);
      }    
      // /// new Borrowed array
      newBorrowedItems.add(newBorrowed_1);newBorrowedItems.add(newBorrowed_2);
      newBorrowedItems.add(newBorrowed_3);newBorrowedItems.add(newBorrowed_4);
      for (JMenuItem item : newBorrowedItems ) {
            item.addActionListener(this);
      }
      // //b  borrowed book arry
      borrowedBookItem.add(borrowedBook_1);borrowedBookItem.add(borrowedBook_2);   
      borrowedBookItem.add(borrowedBook_3); borrowedBookItem.add(borrowedBook_4); 
      for (JMenuItem item : borrowedBookItem ) {
            item.addActionListener(this);
      }
      // /////  libary array
      bookLibaryItem.add(booksInLibrary_1);    
      for (JMenuItem item : bookLibaryItem ) {
            item.addActionListener(this);
      }

}    
      @Override
      public void actionPerformed(ActionEvent e){  
                     
            if( newBookItems.contains( e.getSource() ) ) {               
            framePanel.removeAll();
             new AddNewBookPanel();
            //framePanel.add(new AddNewBookPanel());
           // framePanel.add(frameLabel);
            // framePanel.add(new AddNewBookPanel()); // Pridá panel do framePanel
            frame.repaint(); // Aktualizuje vykreslenie framePanel
            System.out.println("Click event Book Items work !");
            }    
            if(e.getSource()==person) {
                  new AddNewPesronPanel();  
                  framePanel.add(frameLabel);         
                 // System.out.println("Click event Person Items work !");
            }    
            if( newBorrowedItems.contains( e.getSource() ) ){          
              framePanel.removeAll();framePanel.add(new BorrowedPanel());frame.repaint();               
            System.out.println("Panel pre zápis bol úspešne otvorený"); // Control sysout 
      }                   
                                
            if(e.getSource()==borrowedBook_1){  
                  new BorrowedBooksPanel();   
                  framePanel.add(frameLabel);                    
            //    framePanel.removeAll(); 
            //     panel_1 = new BorrowedBooksPanel();
            //     panel_1.setBounds(75, 45, 630, 330);
            //     framePanel.add(panel_1);frame.repaint();
            }  
            if(e.getSource()==borrowedBook_2){ 
                 new BorrowedBookPanel_2();
                 framePanel.add(frameLabel);
                  // framePanel.removeAll();
                  // BorrowedBookPanel_2 b2 = new BorrowedBookPanel_2(); 
                  // b2.setBounds(75, 10, 630, 350);
                  // framePanel.add(b2);frame.repaint();
            }                  
                 System.out.println("Click event BorrowedBook Items work !");
               
            if(bookLibaryItem.contains( e.getSource() )) {
                 System.out.println("Click event Libryries Items work !");
            }       
      }
      public static void main(String[] args) {
           new  MyFrame();
      }
      
}
/*
 * Trieda Myframe obsahuje Menu s hlavným Panelom v ktorom sa po zavolaní pomocou 
 * Action listenera cez Click event na jednotlivé Items v Menu otvoria požadované nové panely ,
 * v ktorých sú funcie na prepojenie sa s databzou podla toho aký zápis, alebo 
 * hodnotu potrebujeme  dosiahnuť .
 * pre zapísanie novej osoby do databázy použijeme .
 * - MenuItem person - PersonPanel - zápis do kniznica.persons (INESRT INTO persons VALUE(......));
 * - MenuItem  new Book - BookPanel - zápis do kniznica.books (INSERT INTO books VALUE(....));
 * MenuItem newBorrowed - BorrowedPanel - zápis do kniznica.borrowed_books (INSERT  INTO .....);
 * - MenuItem  borrowedBooks  - tabulku s zoznamom všetkých momentálne požičaných kníh .
 * - MenuItem librarie (bookInLibrary) - zoznam / tabulku všetkých kníh na danej pobočke.
 * 
 * Spustenie programu zavoláme v tirede main vytvorením noveho objektu Myframe  newMyFrame();
 */

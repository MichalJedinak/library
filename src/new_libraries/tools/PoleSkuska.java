package new_libraries.tools;
import java.awt.BorderLayout;
import java.awt.Button;

import javax.swing.JFrame;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;


public class PoleSkuska  extends JFrame {
      int index = 20;
      int result_1 = 0;
      int result_2 = 0;
      int result_3 = 0;
      int row = 0;
      int columns = 3;
      int [][] i = new int[index][columns];
String jedna; String dva;
      PoleSkuska(){
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setVisible(true);
            this.setSize(800, 500);
            this.setTitle("MigLayout pole skuska");
            
            JPanel panel = new JPanel();
            panel.setLayout(new MigLayout());
            panel.add(new Button("Button_1"));
            panel.add(new Button("Button_2"));
            panel.add(new Button("Button_3"),"wrap");
            panel.add(new Button("Button_4"),"span" );
            panel.add(new Button("Button_5"));
            panel.add(new Button("Button_6"));
            panel.add(new Button("Button_7"));
            panel.add(new Button("Button_8"));
            panel.add(new Button("Button_9"));
            this.add(panel,BorderLayout.CENTER);
            for(int p = 0; p <index ;p++){                 
                  jedna =" "+ String.valueOf(row);
                  dva = " "+ String.valueOf(columns);
                  result_1++;
                  int o = 2+ result_2++;             
                  int d = 3+ result_3++;
                  
                  row++;  
                  i[row][0]= result_1;
                  i[row][1]= result_2;
                  i[row][2]= result_3;
                  System.out.println(result_1+" | "+ o+" | "+d+ " |");
                  // System.out.println(row);
                  // System.out.println(jedna+"  "+dva);               
            }
      }
      public static void main(String[] args) {
            new PoleSkuska();
      }
}

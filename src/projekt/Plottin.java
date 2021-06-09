package projekt;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class Plottin extends JFrame {
    double[][] l;
    public Plottin(double[][] lparam){
        setSize(500, 500);
        l = lparam;
    }

    void drawLines(Graphics g) {
        //create new Graphics2D instance using Graphics parent
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);

        int width=getWidth();
        int height=getHeight();
        int mar = 20;
        int krok = width/ l.length;

        g2.draw(new Line2D.Double(mar, height - mar, mar, 0));
        g2.draw(new Line2D.Double(mar, height - mar, width - krok, height - mar));
        g2.setColor(Color.RED);
        int i;
  //      for (i = 0; i < l.length; i++) {
 //           if (i == 0) {g2.draw(new Line2D.Double(0 + mar, height - mar, l[i][0] + mar, height - l[i][1] - mar));}
  //          else {
  //              g2.draw(new Line2D.Double(l[i-1][0] + mar, height - l[i-1][1] - mar,l[i][0] + mar, height - l[i][1] - mar ));
   //         }

        for (i = 0; i < l.length; i++){
            if (i == 0) {g2.draw(new Line2D.Double(l[i][0]*krok + mar, height - l[i][1] - mar, l[i][0]*krok + mar, height - l[i][1] - mar));}
                      else {
                          g2.draw(new Line2D.Double(l[i-1][0]*krok + mar, height - l[i-1][1] - mar,l[i][0]*krok + mar, height - l[i][1] - mar ));
                     }
        }

    }

    public void paint(Graphics g) {
        super.paint(g);
        drawLines(g);
    }
}

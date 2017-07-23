import javax.swing.*;
import java.awt.*;
import javax.sound.midi.*;

class MyDrawPanel extends JPanel{
    boolean msg = false;
    
    public void controlChange(ShortMessage event){
        msg = true;
        repaint();
    }
    
    public void paintComponent(Graphics g){
        if(msg){
            Graphics2D g2 = (Graphics2D) g;
            
            int red = (int)(Math.random() * 255);
            int green = (int)(Math.random() * 255);
            int blue = (int)(Math.random() * 255);
            g.setColor(new Color(red,green,blue));
            
            int ht = (int)(Math.random() * 120);
            int wid = (int)(Math.random() * 120);
            
            int x = (int)(Math.random() * 40 + 10);
            int y = (int)(Math.random() * 40 + 10);
            
            g.fillRect(x,y,ht,wid);
            msg = false;
        }
    }
}

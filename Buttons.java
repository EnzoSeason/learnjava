import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Buttons{
    JFrame frame;
    JLabel label;
    
    public static void main(String[] args){
        Buttons B = new Buttons();
        B.go();
    }
    
    public void go(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JButton labelbutton = new JButton("Change the number");
        labelbutton.addActionListener(new LabelListener());
        
        JButton colorbutton = new JButton("Change circle");
        colorbutton.addActionListener(new ColorListener());
        
        label = new JLabel("Check it out");
        MyDrawPanel MyDrawP = new MyDrawPanel();
        
        frame.getContentPane().add(BorderLayout.SOUTH, colorbutton);
        frame.getContentPane().add(BorderLayout.CENTER, MyDrawP);
        frame.getContentPane().add(BorderLayout.EAST, labelbutton);
        frame.getContentPane().add(BorderLayout.WEST,label);
        
        frame.setSize(500, 270);
        frame.setVisible(true);
    }
    class LabelListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            double i = Math.random() * 100;
            String num = String.format("%.2f", i);
            label.setText(num);
        }
    }
    class ColorListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            frame.repaint();
        }
    }
}

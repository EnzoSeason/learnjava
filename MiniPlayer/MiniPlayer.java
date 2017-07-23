import javax.swing.*;
import javax.sound.midi.*;
import java.io;
import java.awt.*;

class MiniPlayer{
    static JFrame f = new JFrame("MiniPlayer Only Music");
    static MyDrawPanel ml;
    
    public static void main(String[] args){
        MiniPlayer mini = new MiniPlayer();
        mini.go();
    }
    
    public void go(){
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ml = new MyDrawPanel();
        f.setContentPanel(ml);
        f.setSize(300,300);
        f.setVisible();
        
        try{
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequencer.addControlEventListener(ml, new int[] {127});
        }
    }
}

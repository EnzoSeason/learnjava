import javax.swing.*;
import javax.sound.midi.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class MiniPlayer{
    JFrame f;
    MyDrawPanel ml;
    Sequencer sequencer;
    Sequence seq;
    Track track;
    
    public static void main(String[] args){
        MiniPlayer mini = new MiniPlayer();
        mini.setGUI();
    }
    
    public void setGUI(){
        f  = new JFrame("MiniPlayer For Random Music");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        ml = new MyDrawPanel();//show the movement of the rects
        
        Box buttonbox = new Box(BoxLayout.Y_AXIS);//keep the buttons in a straight line
        
        JButton start = new JButton("Start");
        start.addActionListener(new MystartListener());
        buttonbox.add(start);
        
        JButton stop = new JButton("Stop");
        stop.addActionListener(new MystopListener());
        buttonbox.add(stop);
        
        f.getContentPane().add(BorderLayout.EAST, buttonbox);
        f.getContentPane().add(BorderLayout.CENTER, ml);
        
        setMIDI();
        
        f.setSize(300,300);
        f.setVisible(true);
    }
    
    public void setMIDI(){
        try{
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            int[] list = {127};
            sequencer.addControllerEventListener(ml, list);
            seq = new Sequence(Sequence.PPQ, 4);
            track = seq.createTrack();
        } catch (Exception ex){ ex.printStackTrace();}
    }
    
    public void buildTraceStart(){
        try{
            seq.deleteTrack(track);
            track = seq.createTrack();
        
            int r = 0;
            for (int i = 0; i < 1000; i += 4){
                r = (int)(Math.random() * 90 + 10);
                track.add(makeEvent(144,1,r,120,i));
                track.add(makeEvent(176,1,127,0,i));
                track.add(makeEvent(128,1,r,120,i+2));
            }
        
            sequencer.setSequence(seq);
            sequencer.setTempoInBPM(120);
            sequencer.start();
        } catch (Exception ex){ ex.printStackTrace();}
    }
    
    public class MystartListener implements ActionListener{
        public void actionPerformed(ActionEvent a){
            buildTraceStart();
        }
    }
    
    public class MystopListener implements ActionListener{
        public void actionPerformed(ActionEvent a){
            sequencer.stop();
        }
    }
    
    public MidiEvent makeEvent(int comd, int chan, int one, int two, int tick){
        MidiEvent event = null;
        try{
            ShortMessage a = new ShortMessage();
            a.setMessage(comd, chan, one, two);
            event = new MidiEvent(a, tick);
        }catch(Exception ex){}
        return event;
    }
}

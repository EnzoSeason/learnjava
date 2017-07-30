import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class chatclient{
    JTextArea incoming;
    JTextField outcoming;
    BufferedReader reader;
    PrintWriter writer;
    Socket sock;
    
    public static void main(String[] args){
        chatclient client = new chatclient();
        client.go();
    }
    
    public void go(){

        
        setGUI();
        setNET();
    }
    
    private void setGUI(){
        JFrame frame = new JFrame("Simple ChatRoom");
        JPanel mainPanel = new JPanel();
        
        incoming = new JTextArea(15, 50);
        incoming.setLineWrap(true);
        incoming.setWrapStyleWord(true);
        incoming.setEditable(false);
        incoming.append("Welcome !\n");
        incoming.paintImmediately(incoming.getBounds());
        JScrollPane qScroller = new JScrollPane(incoming);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        outcoming = new JTextField(20);
        
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new SendButtonListener());
        
        mainPanel.add(qScroller);
        mainPanel.add(outcoming);
        mainPanel.add(sendButton);
        
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(400, 500);
        frame.setVisible(true);
        
    }
    private void setNET(){
        try{
            sock = new Socket("127.0.0.1", 5000);
            InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
            reader = new BufferedReader(streamReader);
            writer = new PrintWriter(sock.getOutputStream());
            System.out.println("networking go");
        } catch (IOException ex){ ex.printStackTrace(); }
        Thread t = new Thread(new IncomingReader());
        t.start();
    }
    
    public class SendButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent ev){
            try{
                writer.println(outcoming.getText());
                writer.flush();
            } catch (Exception ex){
                ex.printStackTrace();
            }
            outcoming.setText("");
            outcoming.requestFocus();
        }
    }
    
    public class IncomingReader implements Runnable{
        public void run(){
            String message;
            try{
                while((message = reader.readLine()) != null ){
                    System.out.println("read"+message);
                    incoming.append(message+"\n");
                    incoming.paintImmediately(incoming.getBounds());
                }
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}






















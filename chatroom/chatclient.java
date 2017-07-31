import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class chatclient{
    JFrame frame;
    JTextArea incoming;
    JTextArea history;
    JTextField outcoming;
    BufferedReader reader;
    PrintWriter writer;
    Socket sock;
    ArrayList<String> fileList;
    
    public static void main(String[] args){
        chatclient client = new chatclient();
        client.go();
    }
    
    public void go(){
        setGUI();
        setNET();
    }
    
    private void setGUI(){
        frame = new JFrame("Simple ChatRoom");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel();
        frame.setBounds(30, 30, 300, 300);
        
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
        
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new SaveButtonListener());
        
        JButton historyButton = new JButton("History");
        historyButton.addActionListener(new HistoryButtonListener());
        
        Box buttonbox = new Box(BoxLayout.Y_AXIS);
        buttonbox.add(saveButton);
        buttonbox.add(sendButton);
        buttonbox.add(historyButton);
        
        history = new JTextArea(15, 50);
        history.setLineWrap(true);
        history.setWrapStyleWord(true);
        history.setEditable(false);
        JScrollPane hScroller = new JScrollPane(history);
        hScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        hScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        mainPanel.add(qScroller);
        mainPanel.add(outcoming);
        mainPanel.add(buttonbox);
        mainPanel.add(hScroller);
        
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(800, 900);
        frame.setVisible(true);
        
        fileList = new ArrayList<String>();
        
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
    
    public class HistoryButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent ev){
            JFileChooser fileOpen = new JFileChooser();
            fileOpen.showOpenDialog(frame);
            loadFile(fileOpen.getSelectedFile());
        }
    }
    
    private void loadFile(File file){
        fileList = new ArrayList<String>();
        try{
            BufferedReader loader = new BufferedReader(new FileReader(file));
            String line = null;
            history.setText("Chat History ! \n");
            while((line = loader.readLine())!=null){
                System.out.println("load"+line);
                history.append(line+"\n");
                history.paintImmediately(history.getBounds());
            }
            loader.close();
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
    
    public class SaveButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent ev){
            String historydata = incoming.getText();
            fileList.add(historydata);
            
            JFileChooser fileSave = new JFileChooser();
            fileSave.showSaveDialog(frame);
            saveFile(fileSave.getSelectedFile());
        }
    }
    
    private void saveFile(File file){
        try{
            BufferedWriter saver = new BufferedWriter(new FileWriter(file));
            
            for(String data:fileList){
                saver.write(data);
            }
            saver.close();
        } catch (IOException ex){
            ex.printStackTrace();
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






















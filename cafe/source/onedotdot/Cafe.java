package onedotdot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Cafe{
    Drink drink;
    ArrayList<String> nameList;
    int clientNum = 0;
    JTextArea namespace;
    JTextArea result;
    double total = 0;
    
    public static void main(String[] args){
        new Cafe().setGUI();
    }
    
    public void setGUI(){
        JFrame frame = new JFrame("One Dot Dot");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel mainPanel = new JPanel();
        
        Box Type = new Box(BoxLayout.Y_AXIS);
        Box Condiment = new Box(BoxLayout.Y_AXIS);
        Box Size = new Box(BoxLayout.Y_AXIS);
        
        JLabel type = new JLabel("Type");
        Type.add(type);
        
        JLabel condiment = new JLabel("Condiment");
        Condiment.add(condiment);
        
        JLabel size = new JLabel("Size");
        Size.add(size);
        
        JButton tea = new JButton("Tea $10");
        tea.addActionListener(new teaListener());
        Type.add(tea);
        
        JButton coffee = new JButton("Coffee $12");
        coffee.addActionListener(new coffeeListener());
        Type.add(coffee);
        
        JButton milk = new JButton("Milk +$3");
        milk.addActionListener(new milkListener());
        Condiment.add(milk);

        JButton icecream = new JButton("Icecream +$8");
        icecream.addActionListener(new icecreamListener());
        Condiment.add(icecream);
        
        JButton whip = new JButton("Whip +$5");
        whip.addActionListener(new whipListener());
        Condiment.add(whip);
        
        JButton tall = new JButton("Tall +$0");
        tall.addActionListener(new tallListener());
        Size.add(tall);

        JButton grande = new JButton("Grande +$1");
        grande.addActionListener(new grandeListener());
        Size.add(grande);
        
        JButton venti = new JButton("Venti +$3");
        venti.addActionListener(new ventiListener());
        Size.add(venti);
        
        result = new JTextArea(10,45);
        result.setLineWrap(true);
        result.setWrapStyleWord(true);
        JScrollPane qScroller = new JScrollPane(result);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        JButton done = new JButton("Done");
        done.addActionListener(new doneListener());
        
        namespace = new JTextArea(1,20);
        namespace.requestFocus();
        JButton name = new JButton("Client in");
        name.addActionListener(new nameListener());
        
        nameList = new ArrayList<String>();
        
        JPanel clientPanel = new JPanel();
        clientPanel.add(namespace);
        clientPanel.add(name);
        
        mainPanel.add(Type);
        mainPanel.add(Condiment);
        mainPanel.add(Size);
        mainPanel.add(qScroller);
        mainPanel.add(done);
        
        frame.getContentPane().add(BorderLayout.NORTH,clientPanel);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(600,400);
        frame.setVisible(true);
    }
    
    public class teaListener implements ActionListener{
        public void actionPerformed(ActionEvent ev){
            drink = new Tea();
            result.append("\nTea + ");
        }
    }
    
    public class coffeeListener implements ActionListener{
        public void actionPerformed(ActionEvent ev){
            drink = new Coffee();
            result.append("\nCoffee + ");
        }
    }
    
    public class milkListener implements ActionListener{
        public void actionPerformed(ActionEvent ev){
            drink = new Milk(drink);
            result.append("Milk + ");
        }
    }
    
    public class icecreamListener implements ActionListener{
        public void actionPerformed(ActionEvent ev){
            drink = new Icecream(drink);
            result.append("Icecream + ");
        }
    }
    
    public class whipListener implements ActionListener{
        public void actionPerformed(ActionEvent ev){
            drink = new Whip(drink);
            result.append("Whip + ");
        }
    }
    
    public class tallListener implements ActionListener{
        public void actionPerformed(ActionEvent ev){
            drink = new Tall(drink);
            result.append("Size Tall\n");
            result.append(drink.getDescription() + " $" + drink.cost() + "\n");
            total += drink.cost();
        }
    }
    
    public class grandeListener implements ActionListener{
        public void actionPerformed(ActionEvent ev){
            drink = new Grande(drink);
            result.append("Size Grande\n");
            result.append(drink.getDescription() + " $" + drink.cost() + "\n");
            total += drink.cost();
        }
    }
    
    public class ventiListener implements ActionListener{
        public void actionPerformed(ActionEvent ev){
            drink = new Venti(drink);
            result.append("Size Venti\n");
            result.append(drink.getDescription() + " $" + drink.cost() + "\n");
            total += drink.cost();
        }
    }
    
    public class doneListener implements ActionListener{
        public void actionPerformed(ActionEvent ev){
            result.append("Total : $"+ total);
            total = 0;
            try{
                File file = new File("file.txt");
                FileWriter writer = new FileWriter(file);
                writer.write(result.getText());
                writer.close();
            } catch (IOException ex){
                ex.printStackTrace();
            }
            namespace.setText("");
            namespace.requestFocus();
        }
    }
    
    public class nameListener implements ActionListener{
        public void actionPerformed(ActionEvent ev){
            result.setText("");
            String clientname = namespace.getText();
            nameList.add(clientname);
            result.append("Welcome " + nameList.get(clientNum) + "\n");
            clientNum++;
        }
    }
}























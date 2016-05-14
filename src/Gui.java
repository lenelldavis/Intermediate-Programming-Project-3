/**
 * Lenell Davis
 * CMIS 242
 * Project 3
 * 4/24/16
 * Gui.java
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Gui extends JFrame{
    private final Font font = new Font ("Arial", Font.BOLD, 18);
    private final JFrame frame = new JFrame();
    
    private final ButtonGroup userSelection = new ButtonGroup();
    private final JLabel enterLabel = new JLabel("Enter a number: ");
    private final JLabel resultLabel = new JLabel("Result");
    private final JLabel effLabel = new JLabel("Efficiency");
    
    private final JRadioButton iterativeBtn = new JRadioButton("Iterative");
    private final JRadioButton recursiveBtn = new JRadioButton("Recursive");
    
    private final JTextField enterField = new JTextField(15);
    private final JTextField resultField = new JTextField();
    private final JTextField effField = new JTextField();
    
    private final JButton calcBtn = new JButton("Compute");
    
    private final JPanel choicePanel = new JPanel();
    private final JPanel enterPanel = new JPanel();
    private final JPanel buttonPanel = new JPanel();
    private final JPanel resultPanel = new JPanel();
    private final JPanel mainPanel = new JPanel();
    
    private final String fileName = "output.txt";
    private PrintWriter output; 
    private FileWriter fw;
    private BufferedWriter bw;
   
    /*
    * Constructor for the MyGui View
    */
    public Gui(){
        setFrame();
        setPanels();
        setAttributes();
        addWindowListener(new CloseApp());
    }
    
    
    /*
    * Sets the attributes for the frame
    */
    private void setFrame() {
        
        setSize(600, 300);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Recursive vs Iterative");
        add(mainPanel);
    }
    
    /*
    * Sets the attributes for the components
    */
    private void setAttributes(){
        mainPanel.setLayout(new GridLayout(4,1));
        resultPanel.setLayout(new GridLayout(2,2));
        userSelection.add(iterativeBtn);
        userSelection.add(recursiveBtn);
        iterativeBtn.setFont(font);
        recursiveBtn.setFont(font);
        enterLabel.setFont(font);
        resultLabel.setFont(font);
        effLabel.setFont(font);
        calcBtn.setFont(font);
        resultField.setEditable(false);
        effField.setEditable(false);
        iterativeBtn.setSelected(true);
        calcBtn.addActionListener(new buttonClick());
    }
    
    /*
    * Adds the components to the Panels
    * Adds the panels to the mainPanel
    */
    public void setPanels(){
        choicePanel.add(iterativeBtn);
        choicePanel.add(recursiveBtn);
        
        enterPanel.add(enterLabel);
        enterPanel.add(enterField);
        
        buttonPanel.add(calcBtn);
        
        resultPanel.add(resultLabel);
        resultPanel.add(resultField);       
        resultPanel.add(effLabel);
        resultPanel.add(effField);
        
        mainPanel.add(choicePanel);
        mainPanel.add(enterPanel);
        mainPanel.add(buttonPanel);
        mainPanel.add(resultPanel);
    }
    
    /*
    * Method that calls the Sequence class and passes in the integer
    */
    private void callSequence(int a){
        if(iterativeBtn.isSelected()){
            int iterativeValue = Sequence.computeIterative(a);
            long itEff = (Sequence.counter + 1); 
            resultField.setText(Integer.toString(iterativeValue));
            effField.setText(Long.toString(itEff));
            //Print to file
            iPrintToFile(a, itEff);
           
            Sequence.resetCounter(0);
        }
        
        else if (recursiveBtn.isSelected()){
            int recursiveValue = Sequence.computeRecursive(a);
            long recEff = Sequence.counter;
            resultField.setText(Integer.toString(recursiveValue));
            effField.setText(Long.toString(recEff));
            //Print the values to the file.
            rPrintToFile(a, recEff);
            
            Sequence.resetCounter(0);
        }
    }
    
    /*
    * Test method that parses the text to an Integer and ensures it's not negative.
    */
    private int testMethod(String enterText){
        int a = 0;
        int b = 0;
        try{
            a = Integer.parseInt(enterText);
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(frame, "Please enter a whole number.");
        }
        
        if (a >= 0){
            b = a;
        }
        else {
            JOptionPane.showMessageDialog(frame, "Please enter a positive whole number.");
        }
        
        return b;
    }
    
    /*
    * Print method that takes in the values and prints them to a file
    */
    private void iPrintToFile(int sequenceValue, long effValue){
        output.println(sequenceValue + "," + effValue);
        output.close();
    }
    
    private void rPrintToFile(int sequenceValue, long effValue){
        output.println(sequenceValue + "," + "," + effValue);
        output.close();
    }
    
    
    /**
     *Inner class that handles the Compute Button
     * Opens the filewriter, the bufferedwriter and the printwriter
     */
    private class buttonClick implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int retrievedValue = testMethod(enterField.getText());
            try{
                fw = new FileWriter(fileName, true);
                bw = new BufferedWriter(fw);
                output = new PrintWriter(bw);
            }   
            catch(IOException g){
                JOptionPane.showMessageDialog(frame, "File was not found.");
            }

            callSequence(retrievedValue);
        }
        
    }
    
    /**
     *Inner class that handles the WindowClose Event
     * Closes the filewriter and the bufferedwriter
     */
    private class CloseApp extends WindowAdapter{
        public void windowClosing(WindowEvent e)
        {
            try{
                fw.close();
                bw.close();
            }
            catch(IOException f){}
        }
        
        public void windowClosed(WindowEvent e){
            System.exit(0);
        }
    }
}

package assign3;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.*;
import javax.xml.transform.Source;

import java.awt.*;
import java.awt.event.*;


 public class SudokuFrame extends JFrame {
	 public static final int WIDTH=600;
	 public static final int HEIGHT=400;
	 
	 private JTextArea source;
	 private JTextArea result;
	 private JButton checkButton;
	 private JCheckBox autoCheck;
	 
	public SudokuFrame() {
		super("Sudoku Solver");
		
		JComponent pane=(JComponent) getContentPane();
		
		setLayout(new BorderLayout(4,4));
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		//source text field
	    source = new JTextArea(15,20);
        source.setBorder(new TitledBorder("Puzzle"));
        pane.add(source,BorderLayout.CENTER);
        
        //result text field
        result = new JTextArea(15,20);
        result.setBorder(new TitledBorder("Solution"));
        pane.add(result,BorderLayout.EAST);
        
        //box for button and check box
        Box box = Box.createHorizontalBox();    
        checkButton = new JButton("Check");
        checkButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				solveSudoku();
			}
		});
        autoCheck = new JCheckBox("Auto Check");
        autoCheck.setSelected(true);
        box.add(checkButton);
        box.add(autoCheck);
        pane.add(box,BorderLayout.SOUTH);
        
        //http://www.java2s.com/Tutorial/Java/0260__Swing-Event/ListeningtoJTextFieldEventswithaDocumentListener.htm
        DocumentListener documentListener = new DocumentListener() {
            public void changedUpdate(DocumentEvent documentEvent) {
            	if(autoCheck.isSelected())
            		solveSudoku();
            }
            public void insertUpdate(DocumentEvent documentEvent) {
            	if(autoCheck.isSelected())
            		solveSudoku();
            }
            public void removeUpdate(DocumentEvent documentEvent) {
            	if(autoCheck.isSelected())
            		solveSudoku();
            }
          };
          source.getDocument().addDocumentListener(documentListener);
        
		
		// YOUR CODE HERE
		
		// Could do this:
		// setLocationByPlatform(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	// uses Sudoku class to solve input from source JTextArea and print result on result JTextArea 
	private void solveSudoku(){
		result.setText("");
		try{
			String sudokuSourceStr=source.getText();
			Sudoku sudoku=new Sudoku(sudokuSourceStr);
			int res=sudoku.solve();
			if(res>0)
				result.append(sudoku.getSolutionText());
			result.append("solutions: "+res+"\n");
			result.append("elapsed: "+sudoku.getElapsed()+"ms");
		}catch(Exception e){
			result.append("parsing error");
		}
		
	}
	
	
	public static void main(String[] args) {
		// GUI Look And Feel
		// Do this incantation at the start of main() to tell Swing
		// to use the GUI LookAndFeel of the native platform. It's ok
		// to ignore the exception.
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ignored) { }
		
		SudokuFrame frame = new SudokuFrame();
	}

}

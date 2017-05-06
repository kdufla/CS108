package assign3;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 * view
 * make frame with table, 3 text fields, 2 buttons and 2 combo boxes
 */
public class frame extends JFrame {
	public static final int WIDTH=800;
	public static final int HEIGHT=500;
	private static final int TEXTFIELD_SIZE = 10;
	public static final String EXACT="Exact Match";
	public static final String NEXACT="Partial Match";
	public static final String GREATER="Population Larger Than";
	public static final String NGREATER="Population Smaller or Equal Than";
	
	JButton add;
	JButton search;
	JTable table;
	Controller control;
	JPanel panel;
	Box box;
	JTextField metropolis;
	JTextField continent;
	JTextField population;
	Box choiceBox;
	JComboBox match;
	JComboBox populat;
	model model;

	public frame(model m){
		super("metropolis viewer");
		
		// model
		model=m;
		
		JComponent pane=(JComponent) getContentPane();
		
		setLayout(new BorderLayout(4,4));
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		table=new JTable(model);
		pane.add(table,BorderLayout.CENTER);
		
		//up
		addUp(pane);
		
		//left
		addLeft(pane);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	/*
	 * prepare and add everything needed on the north side
	 */
	private void addUp(JComponent pane){
		panel = new JPanel();
		
		metropolis = new JTextField(TEXTFIELD_SIZE);
		panel.add(new JLabel("Metropolis: "));
		panel.add(metropolis);
		
		continent = new JTextField(TEXTFIELD_SIZE);
		panel.add(new JLabel("Continent: "));
		panel.add(continent);
		
		population = new JTextField(TEXTFIELD_SIZE);
		panel.add(new JLabel("Population"));
		panel.add(population);
		
		pane.add(panel,BorderLayout.NORTH);
	}
	
	/*
	 * prepare and add everything needed on the east side
	 */
	private void addLeft(JComponent pane){
		box = Box.createVerticalBox();
		
		add = new JButton("Add");
		box.add(add);
		
		search = new JButton("Search");
		box.add(search);
		
		choiceBox = Box.createVerticalBox();
		choiceBox.setBorder(new TitledBorder("Search Options"));
		
		match = new JComboBox();
		match.addItem(EXACT);
		match.addItem(NEXACT);
		choiceBox.add(match);
		
		populat = new JComboBox();
		populat.addItem(GREATER);
		populat.addItem(NGREATER);
		choiceBox.add(populat);
		
		box.add(choiceBox);
		
		pane.add(box,BorderLayout.EAST);
	}
}



package assign3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {

	frame f; // view
	model m; // model
	
	public Controller(frame f,model m){
		this.f=f;
		this.m=m;
		f.add.addActionListener(new addListener());
		f.search.addActionListener(new searchListener());
	}
	
	/*
	 * listeren for add button 
	 * signals model to add input to database 
	 */
	private class addListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String me = f.metropolis.getText();
			String c = f.continent.getText();
			String p = f.population.getText();
			m.add(me, c, p);
			
		}
		
	}
	
	/*
	 * listeren for search button 
	 * signals model to search input and update result
	 */
	private class searchListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String me = f.metropolis.getText();
			String c = f.continent.getText();
			String p = f.population.getText();
			boolean exact = f.match.getSelectedItem().equals(f.EXACT);
			boolean greater = f.populat.getSelectedItem().equals(f.GREATER);
			m.search(me, c, p,exact,greater);
		}
		
	}
}

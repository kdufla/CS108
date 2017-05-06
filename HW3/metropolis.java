package assign3;

/**
 * metropolis class with its name, continent, population
 * and with setters and getters.
 * setters can't update database. this class is only encapsulation of metropolis
 */
public class metropolis {
	private String metropolis;
	private String continent;
	private int population;
	
	public String getMetropolisName(){
		return metropolis;
	}
	
	public String getMetropolisContinent(){
		return continent;
	}
	
	public int getMetropolisPopulation(){
		return population;
	}
	
	public void setMetropolisName(String m){
		metropolis=m;
	}
	
	public void setMetropolisContinent(String c){
		continent=c;
	}
	
	public void setMetropolisPopulation(int p){
		population=p;
	}
}

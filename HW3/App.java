package assign3;

public class App {

	/**
	 * main program
	 * used http://stackoverflow.com/questions/5831388/what-is-the-controller-in-java-swing form mvc
	 * code may look alike
	 */
	public static void main(String[] args) {
		model m=new model();
    	
    	frame f=new frame(m);
    	
    	Controller c=new Controller(f,m);
    	
	}

}

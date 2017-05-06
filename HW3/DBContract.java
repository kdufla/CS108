package assign3;

/**
 * strings are prepared for code
 */
public class DBContract {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private DBContract() {
    }

    public static class metropolisesTable {
        public static final String TABLE_NAME = "metropolises";
        public static final String COLUMN_NAME_METROPOLIS = "metropolis";
        public static final String COLUMN_NAME_CONTINENT = "continent";
        public static final String COLUMN_NAME_POPULATION = "population";
    }
    
    public static class dataManip{
    	public static final String SELECT=" SELECT ";
    	public static final String FROM=" FROM ";
    	public static final String INSERT=" INSERT ";
    	public static final String INTO=" INTO ";
    	public static final String WHERE=" WHERE ";
    	public static final String VALUES=" VALUES ";
    	public static final String ORD=" ORDER BY ";
    	public static final String LIKE=" LIKE ";
    	public static final String AND=" AND ";
    }
}

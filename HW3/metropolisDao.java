package assign3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 * Data Access Object for metropolis database
 */
public class metropolisDao {

	private final conector cc;

	
	public metropolisDao(conector cc) {
		this.cc= cc;
	}

	/**
	 * insert values in database
	 */
	public void insertMetropolis(String met, String cont, String pop) {
		try {
			Connection conn = cc.getConnection();
			String insert = DBContract.dataManip.INSERT + DBContract.dataManip.INTO
					+ DBContract.metropolisesTable.TABLE_NAME + DBContract.dataManip.VALUES + "(? , ? , ?);";
			PreparedStatement stmnt = conn.prepareStatement(insert);
			stmnt.setString(1, met);
			stmnt.setString(2, cont);
			stmnt.setInt(3, Integer.parseInt(pop));
			stmnt.executeUpdate();
			conn.close();
		} catch (Exception e) {
		}
		
	}
	
	/*
	 * generate sql query statement for search 
	 */
	private String searchQuery(String m, String c, String p,boolean exact,boolean greater){
		String q=DBContract.dataManip.SELECT+"*"+DBContract.dataManip.FROM+DBContract.metropolisesTable.TABLE_NAME;
		
		if(!(m.isEmpty()&&c.isEmpty()&&p.isEmpty())){
			q+=DBContract.dataManip.WHERE;
			
			if(!m.isEmpty()){
				q+=DBContract.metropolisesTable.COLUMN_NAME_METROPOLIS+DBContract.dataManip.LIKE;
				if(exact){
					q+="\""+m+"\"";
				}
				else{
					q+="\"%"+m+"%\"";
				}
				if(!(c.isEmpty()&&p.isEmpty()))
					q+=DBContract.dataManip.AND;
			}
			
			if(!c.isEmpty()){
				q+=DBContract.metropolisesTable.COLUMN_NAME_CONTINENT+DBContract.dataManip.LIKE;
				if(exact){
					q+="\""+c+"\"";
				}
				else{
					q+="\"%"+c+"%\"";
				}
				if(!p.isEmpty())
					q+=DBContract.dataManip.AND;
			}
			
			if(!p.isEmpty()){
				q+=DBContract.metropolisesTable.COLUMN_NAME_POPULATION;
				if(greater){
					q+=">";
				}
				else{
					q+="<=";
				}
				q+=p;
			}
		}
		q+=";";
		return q;
	}

	/**
	 * array list of metropolises needed
	 */
	public ArrayList<metropolis> getMetropolisList(String m, String c, String p,boolean exact,boolean greater) {
		ArrayList<metropolis> list = new ArrayList<metropolis>();
		try {
			Connection conn = cc.getConnection();
			try {
				Statement stmt = conn.createStatement();
				String query = searchQuery(m, c, p, exact, greater);
				try {
					ResultSet result = stmt.executeQuery(query);

					while (result.next()) {
						list.add(fetch(result));
					}
				} catch (Exception e) {
				}
			} catch (Exception e) {
			}
			conn.close();
		} catch (Exception e) {
		}
		return list;
	}

	/*
	 * generate metropolis as object
	 */
	private metropolis fetch(ResultSet result) throws SQLException {
		metropolis m = new metropolis();
		m.setMetropolisName(result.getString(DBContract.metropolisesTable.COLUMN_NAME_METROPOLIS));
		m.setMetropolisContinent(result.getString(DBContract.metropolisesTable.COLUMN_NAME_CONTINENT));
		m.setMetropolisPopulation(result.getInt(DBContract.metropolisesTable.COLUMN_NAME_POPULATION));
		return m;
	}

}

package assign3;

import java.awt.List;
import java.util.*;

import javax.swing.table.AbstractTableModel;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 * model
 * can insert metropolis to database and to get search results
 */
public class model extends AbstractTableModel {
	
	private static final int ColCount=3;
	
	ArrayList<metropolis> list;
	metropolisDao dao;
	
	public model(){
		list=new ArrayList<metropolis>();
		conector cc=new conector();
		dao=new metropolisDao(cc);
	}

	@Override
	public String getColumnName(int c) {
		if (c == 0)
			return DBContract.metropolisesTable.COLUMN_NAME_METROPOLIS;
		else if (c == 1)
			return DBContract.metropolisesTable.COLUMN_NAME_CONTINENT;
		else
			return DBContract.metropolisesTable.COLUMN_NAME_POPULATION;
	}

	@Override
	public int getColumnCount() {
		return ColCount;
	}

	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		if(list!=null){
			metropolis m=list.get(arg0);
			if(arg1==0)return m.getMetropolisName();
			else if(arg1==1)return m.getMetropolisContinent();
			else return m.getMetropolisPopulation();
		}
		return null;
	}
	
	public void add(String m,String c, String p){
		dao.insertMetropolis(m, c, p);
		metropolis met=new metropolis();
		met.setMetropolisName(m);
		met.setMetropolisContinent(c);
		met.setMetropolisPopulation(Integer.parseInt(p));
		list=new ArrayList<metropolis>();
		list.add(met);
		fireTableDataChanged();
	}
	
	public void search(String m,String c, String p, boolean exact, boolean greater){
		list=dao.getMetropolisList(m, c, p, exact, greater);
		fireTableDataChanged();
	}

}

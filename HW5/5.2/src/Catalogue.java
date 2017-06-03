import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by kdufla on 5/28/17.
 */

public class Catalogue {
    ArrayList<Product> catalogue;
    public Catalogue(){
        catalogue = new ArrayList<Product>();
        getFromDatabase();
    }

    private void getFromDatabase(){
        Connector data = new Connector ();
        Connection temp = data.getConnection ();
        String query = DBContract.dataManip.SELECT + "*" + DBContract.dataManip.FROM + DBContract.productTable.TABLE_NAME;
        try {
            Statement st = temp.createStatement ();
            ResultSet rs = st.executeQuery ( query );
            int numRows = getLength(rs);
            for(int i = 0; i < numRows; i++){
                Product p = new Product ();
                rs.absolute ( i + 1 );
                p.setId ( rs.getString ( 1 ) );
                p.setName ( rs.getString ( 2 ) );
                p.setImage ( rs.getString ( 3 ) );
                p.setPrice ( rs.getDouble ( 4 ) );
                catalogue.add ( p );
            }
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
    }

    public Product getProduct(int index){
        return catalogue.get ( index );
    }
    public int getSize(){
        return catalogue.size ();
    }
    private int getLength(ResultSet rs){
        int length = 0;
        try {
            rs.last();
            length = rs.getRow ();
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
        return length;
    }
}

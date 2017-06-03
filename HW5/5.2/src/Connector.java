import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

/**
 * Created by kdufla on 5/28/17.
 */
public class Connector {
    private static String account;
    private static String password;
    private static String url;

    /**
     * prepare info for connection
     */
    public Connector() {
        account = MYDBInfo.MYSQL_USERNAME;
        password = MYDBInfo.MYSQL_PASSWORD;
        url = MYDBInfo.MYSQL_DATABASE_SERVER + "/" + MYDBInfo.MYSQL_DATABASE_NAME
                + "?verifyServerCertificate=false&useSSL=true";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * generates connection
     */
    public Connection getConnection() {
        try {
            return (Connection) DriverManager.getConnection("jdbc:mysql://" + url, account, password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}

package assign3;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

/**
 * class generates connections using DB info
 *
 */
public class conector {

	private static String account;
	private static String password;
	private static String url;

	/**
	 * prepare info for connection
	 */
	public conector() {
		account = MyDBInfo.MYSQL_USERNAME;
		password = MyDBInfo.MYSQL_PASSWORD;
		url = MyDBInfo.MYSQL_DATABASE_SERVER + "/" + MyDBInfo.MYSQL_DATABASE_NAME
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

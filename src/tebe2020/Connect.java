package tebe2020;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Connect {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/baru?serverTimezone=Asia/Jakarta";
	static final String USERNAME = "root";
	static final String PASSWORD = "";
	static Connection conn;
	
    public Connection getConn() throws SQLException {
        conn = DriverManager.getConnection(DB_URL,USERNAME, PASSWORD);

        return conn;
    }
}

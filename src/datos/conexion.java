package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class conexion {
	  private static final String DB_NAME = "Banco";
	    private static final String DB_URL = "jdbc:mysql://api-films-azure.mysql.database.azure.com:3306/" + DB_NAME + "?useSSL=true";
	    private static final String DB_USER = "JavierTorales";
	    private static final String DB_PASSWORD = "-CASA1234";
	    private Connection connection;

	    public Connection Open() {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            this.connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return this.connection;
	    }
	
	public ResultSet query(String query)
	{
		Statement st;
		ResultSet rs=null;
		try
		{
			st= connection.createStatement();
			rs= st.executeQuery(query);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	public boolean execute(String query)
	{
		Statement st;
		boolean save = true;
		try {
			st = connection.createStatement();
		    st.executeUpdate(query);
		}
		catch(SQLException e)
		{
			save = false;
			e.printStackTrace();
		}
		return save;
	}
	
	public boolean close()
	{
		boolean ok=true;
		try {
			connection.close();
		}
		catch(Exception e)
		{
			ok= false;
			e.printStackTrace();
		}
		return ok;
	}
}
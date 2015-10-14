package dataBase;

import java.sql.*;
import java.util.Properties;

import javax.sql.DataSource;

public class DataBase {
	private static DataBase instance = null;
	
	private DataBase() {
	      // Exists only to defeat instantiation.
	}
	public static DataBase getInstance() {
		if(instance == null) {
			instance = new DataBase();
	    }
	    return instance;
	}
	
	
	
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USER = "system";  //sys as sysdba
	private static final String PASS = "a12s3d4f";
	private static Connection connection = null;
	private static Statement stmt = null;
	private static ResultSet result = null;
	//private static PreparedStatement statement = null;
	
	
	// procedura de conectare la Baza de Date
	public static void connectDataBase() throws SQLException {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver-ul nu a putut fi inregistrat!");
			e.printStackTrace();
            System.exit(0);
		}
		
		
        try {
            Properties props = new Properties();
            props.setProperty("user", USER);
            props.setProperty("password", PASS);
            connection = DriverManager.getConnection(URL,props);
            System.out.println("DataBase has started!");
            
        } catch (SQLException error) {
        	error.printStackTrace();
            System.out.println("Error on DataBase open!");
            System.exit(0);
        }
    }
	
	
	// procedura de inchidere a Bazei de Date
	public static void closeDataBaseConnection() throws SQLException {
        try {
            connection.close();
            System.out.println("DataBase has closed.");
        } catch (SQLException errorOnClose) {
            System.out.println("Error on DataBase close!");
        }
    }
	
	public static Connection getConnection() {
        return connection;
    }
	
	
	
	/*========================================= INSERTURI-uri =========================================*/
	
	public void addPersoana(String name) throws SQLException {
		String sql = "INSERT INTO Persoane (PERSNAME) VALUES ('"+name+"')";
	    Connection conn = null;
	    Statement stmt = null;
	    try {
	    	conn = DataBase.getConnection();
		    stmt = conn.createStatement();
			stmt.execute(sql);
			
			stmt.close();
	    } catch (SQLException eroare) {
            System.out.println("Error on adding new person");
            eroare.printStackTrace();
        }
	}
	
	public void addQuestion(String question) throws SQLException {
		String sql = "INSERT INTO Questions (QUESTION) VALUES ('"+question+"')";
	    Connection conn = null;
	    Statement stmt = null;
	    try {
	    	conn = DataBase.getConnection();
		    stmt = conn.createStatement();
			stmt.execute(sql);
			
			stmt.close();
	    } catch (SQLException eroare) {
            System.out.println("Error on adding new question");
            eroare.printStackTrace();
        }
	}
	
	/*========================================= UPDATE-uri =========================================*/
	
	public void setAgePersoana(String name, int age) throws SQLException {
		String sql = "UPDATE Persoane SET AGE = "+age+" WHERE PERSNAME = '"+name+"'";
	    Connection conn = null;
	    Statement stmt = null;
	    try {
	    	conn = DataBase.getConnection();
		    stmt = conn.createStatement();
			stmt.execute(sql);
			
			stmt.close();
	    } catch (SQLException eroare) {
            System.out.println("Error on setting the age of person");
            eroare.printStackTrace();
        }
	}
	
	public void setJobPersoana(String name, String job) throws SQLException {
		String sql = "UPDATE Persoane SET JOB = '"+job+"' WHERE PERSNAME = '"+name+"'";
	    Connection conn = null;
	    Statement stmt = null;
	    try {
	    	conn = DataBase.getConnection();
		    stmt = conn.createStatement();
			stmt.execute(sql);
			
			stmt.close();
	    } catch (SQLException eroare) {
            System.out.println("Error on setting the job of person");
            eroare.printStackTrace();
        }
	}
	
	public void setInfoPersoana(String name, String info) throws SQLException {
		String sql = "UPDATE Persoane SET INFO = '"+info+"' WHERE PERSNAME = '"+name+"'";
	    Connection conn = null;
	    Statement stmt = null;
	    try {
	    	conn = DataBase.getConnection();
		    stmt = conn.createStatement();
			stmt.execute(sql);
			
			stmt.close();
	    } catch (SQLException eroare) {
            System.out.println("Error on setting the info of person");
            eroare.printStackTrace();
        }
	}
	
	
	public void setTipIntrebare(String question, String tip) throws SQLException {
		String sql = "UPDATE Questions SET TIPNAME = '"+tip+"' WHERE QUESTION = '"+question+"'";
	    Connection conn = null;
	    Statement stmt = null;
	    try {
	    	conn = DataBase.getConnection();
		    stmt = conn.createStatement();
			stmt.execute(sql);
			
			stmt.close();
	    } catch (SQLException eroare) {
            System.out.println("Error on setting the tipname of question");
            eroare.printStackTrace();
        }
	}
	
	public void setInfoIntrebare(String question, String info) throws SQLException {
		String sql = "UPDATE Questions SET INFO = '"+info+"' WHERE QUESTION = '"+question+"'";
	    Connection conn = null;
	    Statement stmt = null;
	    try {
	    	conn = DataBase.getConnection();
		    stmt = conn.createStatement();
			stmt.execute(sql);
			
			stmt.close();
	    } catch (SQLException eroare) {
            System.out.println("Error on setting the info of question");
            eroare.printStackTrace();
        }
	}
	
	/*========================================= VIEW-uri si Selecturi =========================================*/
		public ResultSet viewPersoane()
	            throws SQLException {
	        String sql = "SELECT * FROM Persoane";
	        Connection conn = null;
	        Statement stmt = null;
	        ResultSet rs = null;
	        try {
	            conn = DataBase.getConnection();
	            stmt = conn.createStatement();
	            rs = stmt.executeQuery(sql);
	         
	            rs.close();
	            stmt.close();
	            return rs;
	            
	        } catch (SQLException eroare) {
	            System.out.println("Error on view persoane");
	            eroare.printStackTrace();
	            return null;
	        }
	    }
		
		public ResultSet viewQuestionsAsked()
	            throws SQLException {
	        String sql = "SELECT * FROM QuestionsAsked";
	        Connection conn = null;
	        Statement stmt = null;
	        ResultSet rs = null;
	        try {
	            conn = DataBase.getConnection();
	            stmt = conn.createStatement();
	            rs = stmt.executeQuery(sql);
	         
	            rs.close();
	            stmt.close();
	            return rs;
	            
	        } catch (SQLException eroare) {
	            System.out.println("Error on view QuestionsAsked");
	            eroare.printStackTrace();
	            return null;
	        }
	    }
		
		public int maxNrQuestions() throws SQLException {
	        String sql = "SELECT MAX(QUEID) FROM Questions";
	        Connection conn = null;
	        stmt = null;
	        result = null;
	        try {
	            conn = DataBase.getConnection();
	            stmt = conn.createStatement();
	            result = stmt.executeQuery(sql);
	            int max=0;
	            if (result.next())
	            	max = result.getInt(1);
	            
	            return max;
	            
	        } catch (SQLException eroare) {
	            System.out.println("Error on calculate maxim of questions");
	            eroare.printStackTrace();
	            return 0;
	        }
	    }
}
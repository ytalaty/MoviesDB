package dbexample;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MovieIDTitle {

	public static void main(String[] args) throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		
		System.out.print( "\nLoading JDBC driver...\n\n" );
	      try {
	         Class.forName("oracle.jdbc.OracleDriver");
	         }
	      catch(ClassNotFoundException e) {
	         System.out.println(e);
	         System.exit(1);
	         }
	      
	      try {
	          System.out.print( "Connecting to CSC352 database...\n\n" );

	          conn = DriverManager.getConnection("jdbc:oracle:thin:@acadoradbprd01.dpu.depaul.edu:1521:ACADPRD0", "ytalaty", "cdm1538550");

	          System.out.println( "Connected to database CSC352..." );
	          System.out.println();
	          stmt = conn.createStatement();
	      }
	      catch (SQLException se) {
	          System.out.println(se);
	          System.exit(1);
	          }
	      
	      try {
	    	  //String dropString = "DROP TABLE MOVIESIDTITLE CASCADE CONSTRAINTS";
	    	  //stmt.executeUpdate(dropString);
	    	  //System.out.println("MOVIESIDTITLE successfully table dropped");
	    	  stmt.execute("create or replace PROCEDURE MOVEIDTITLE AS" + " " +
	    			  "BEGIN" + " " +
	    			  "DECLARE" + " " +
	    			  "sqlstring VARCHAR2(1000) :=" +
	    			  "'CREATE TABLE moviesidtitle (movieid NUMBER(4), movietitle VARCHAR2(200))';" +
	    			  "BEGIN" + " " +
	    			  "EXECUTE IMMEDIATE sqlstring;" +
	    			  "DBMS_OUTPUT.PUT_LINE('MovieIDTitle Table Created');" +
	    			  "END;" +
	    			  "END MOVEIDTITLE;");
	    	  System.out.println("MoviesIDTitle Table Procedure Successfully Created");
	    	  CallableStatement cStmt = conn.prepareCall("{call IM_MOVIEIDTITLE}");
	    	  cStmt.execute();
	    	  cStmt.close();
	    	  stmt.close();
		      conn.close();
	      }
	      catch (SQLException se) {
	    	  stmt.close();
	    	  conn.close();
	    	  System.out.println( "SQL ERROR: " + se );
	      }

	}

}

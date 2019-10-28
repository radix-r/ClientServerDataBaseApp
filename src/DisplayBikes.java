// Simple JDBC Example which uses Metadata 
//Java application program connects to the bikedb database and
//has the query SELECT (attribute set) FROM bikes executed with results
//displayed in the command window.

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import com.mysql.cj.jdbc.MysqlDataSource;

public class DisplayBikes 
{
   // launch the application
   public static void main( String args[] )
   {
	 //using a properties file  
	    Properties properties = new Properties();
	    FileInputStream filein = null;
	    MysqlDataSource dataSource = null;
	    //read properties file
	    try {
	    	filein = new FileInputStream("db.properties");
	    	properties.load(filein);
	    	dataSource = new MysqlDataSource();
	    	dataSource.setURL(properties.getProperty("MYSQL_DB_URL"));
	    	dataSource.setUser(properties.getProperty("MYSQL_DB_USERNAME"));
	    	dataSource.setPassword(properties.getProperty("MYSQL_DB_PASSWORD"));	 	
	    
            // connect to database bikes and query database
   	        // establish connection to database
    	    Connection connection = dataSource.getConnection();
    	  
	         // create Statement for querying database
	         Statement statement = connection.createStatement();
	         
	         // query the database
	         ResultSet resultSet = statement.executeQuery("SELECT * FROM bikes" );
	         
	         // process query results
	         ResultSetMetaData metaData = resultSet.getMetaData();
	         int numberOfColumns = metaData.getColumnCount();
	         System.out.println( "Bikes Table of bikedb Database:" );
	
	         for ( int i = 1; i <= numberOfColumns; i++ )
	            System.out.printf( "%-20s\t", metaData.getColumnName( i ) );
	         System.out.println();
	         
	         for ( int i = 1; i <= numberOfColumns; i++ )
	             System.out.printf( "%-20s\t", "-------------------" );
	          System.out.println();
	         
	         while ( resultSet.next() ) 
	         {
	            for ( int i = 1; i <= numberOfColumns; i++ )
	               System.out.printf( "%-20s\t", resultSet.getObject( i ) );
	            System.out.println();
	         } // end while
	         System.out.println(); System.out.println();
	         //close connection and statement
	         statement.close();
	         connection.close();
	      }  // end try
	      catch ( SQLException sqlException ) 
	      {
	         sqlException.printStackTrace();
	         System.exit( 1 );
	      } // end catch
	      catch (IOException e) {
	   	     e.printStackTrace();
	       }  
     } // end main
}  // end class DisplayBikes

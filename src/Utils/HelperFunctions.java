package Utils;

import Package.Patient;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HelperFunctions {

	/**
	 * Run a SQL command which does not return a recordset:
	 * CREATE/INSERT/UPDATE/DELETE/DROP/etc.
	 * 
	 * @throws SQLException If something goes wrong
	 */
	public boolean executeUpdate(Connection conn, String command) throws SQLException {
	    Statement stmt = null;
	    try {
	        stmt = conn.createStatement();
	        stmt.executeUpdate(command); // This will throw a SQLException if it fails
	        return true;
	    } finally {

	    	// This will run whether we throw an exception or not
	        if (stmt != null) { stmt.close(); }
	    }
	}
	
	public String getStringHelper(String attribute, ResultSet rs)
	{
		try{
			if(rs.findColumn(attribute) > -1)
				return rs.getString(attribute);
			else
				return null;
		}
		catch(Exception e)
		{
			return null;
		}
		
		
	}
	
	public Date getDateHelper(String attribute, ResultSet rs)
	{
		try{
			if(rs.findColumn(attribute) > -1)
				return rs.getDate(attribute);
			else
				return null;
		}
		catch(Exception e)
		{
			return null;
		}
		
		
	}
	
	public List<Patient> executeQuery(Connection conn, String command) throws SQLException {
	
		Statement stmt = null;
	    try {
	    	List<Patient> l = new ArrayList<Patient>();
	        stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(command); // This will throw a SQLException if it fails
	        while(rs.next()){
	        	Patient p = new Patient(getStringHelper("patientId",rs),getStringHelper("patientrole",rs),getStringHelper("FirstName",rs),getStringHelper("GivenName",rs),getStringHelper("FamilyName",rs),getStringHelper("suffix",rs),getStringHelper("gender",rs), getDateHelper("BirthTime",rs),getStringHelper("providerId",rs),getDateHelper("xmlCreationdate",rs));
	        	l.add(p);
	        }
	        return l;
	    } catch (Exception e){
	    	return null;
	    
	    }
	    finally {
	    

	    	// This will run whether we throw an exception or not
	        if (stmt != null) { stmt.close(); }
	    }
		
}
}
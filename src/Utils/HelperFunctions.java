package Utils;

import Package.Allergy;
import Package.Patient;
import Package.Plan;

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

	public Patient getPatient(Connection conn, String pid) throws SQLException {	
		Statement stmt = null;
	    try {
	        stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM Patient P WHERE P.patientId=" + pid + ";"); // This will throw a SQLException if it fails
	        Patient p = new Patient(pid,getStringHelper("patientrole",rs),getStringHelper("FirstName",rs),getStringHelper("GivenName",rs),getStringHelper("FamilyName",rs),getStringHelper("suffix",rs),getStringHelper("gender",rs), getDateHelper("BirthTime",rs),getStringHelper("providerId",rs),getDateHelper("xmlCreationdate",rs));
	        return p;
	    } catch (Exception e){
	    	return null;
	    
	    }
	    finally {
	    	// This will run whether we throw an exception or not
	        if (stmt != null) { stmt.close(); }
	    }	
	}
	
	public Boolean updatePatient(Connection conn, String pid, List<String> attributes) throws SQLException {	
		Statement stmt = null;
	    try {
	        stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery("UPDATE * FROM Patient P WHERE P.patientId=" + pid + ";"); // This will throw a SQLException if it fails
	        Patient p = new Patient(pid,getStringHelper("patientrole",rs),getStringHelper("FirstName",rs),getStringHelper("GivenName",rs),getStringHelper("FamilyName",rs),getStringHelper("suffix",rs),getStringHelper("gender",rs), getDateHelper("BirthTime",rs),getStringHelper("providerId",rs),getDateHelper("xmlCreationdate",rs));
	        return true;
	    } catch (Exception e){
	    	return false;
	    }
	    finally {
	    	// This will run whether we throw an exception or not
	        if (stmt != null) { stmt.close(); }
	    }	
	}
	
	public List<Plan> getPlan(Connection conn, String pid) throws SQLException {
		Statement stmt = null;
	    try {
	    	List<Plan> l = new ArrayList<Plan>();
	        stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT P.description, P.procedureDate FROM Plan P WHERE P.patientId=" + pid + ";"); // This will throw a SQLException if it fails
	        while(rs.next()){
	        	Plan p = new Plan(getStringHelper("patientId", rs), getStringHelper("description",rs), getDateHelper("procedureDate",rs));
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
	
	public List<Allergy> getAllergy(Connection conn, String pid) throws SQLException {
		Statement stmt = null;
	    try {
	    	List<Allergy> l = new ArrayList<Allergy>();
	        stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM Allergy A WHERE A.allerginId=" + pid + ";"); // This will throw a SQLException if it fails
	        while(rs.next()){	
	        	Allergy a = new Allergy(getStringHelper("allerginId", rs), getStringHelper("substance",rs), getStringHelper("reactionType",rs), getStringHelper("status", rs));
	        	l.add(a);
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
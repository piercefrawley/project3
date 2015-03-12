package Utils;

import Package.Allergy;
import Package.Guardian;
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
	        ResultSet rs = stmt.executeQuery("SELECT * FROM messages WHERE patientId = " + pid); // This will throw a SQLException if it fails
	        Patient p = null;
	        while(rs.next())
	        	p = new Patient(pid,getStringHelper("patientrole",rs),getStringHelper("GivenName",rs),getStringHelper("FamilyName",rs),getStringHelper("Suffix",rs),getStringHelper("Gender",rs), getStringHelper("Birthtime",rs),getStringHelper("ProviderId",rs),getStringHelper("XMLHealth",rs));
	      
	        return p;
	    } catch (Exception e){
	    	System.out.println(e);
	    	
	    	return null;
	    
	    }
	    finally {
	    	// This will run whether we throw an exception or not
	        if (stmt != null) { stmt.close(); }
	    }	
	}

	public Guardian getGuardian(Connection conn, String role) throws SQLException {	
		Statement stmt = null;
	    try {
	    	
	        stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM Guardian G WHERE G.GuardianNo = " + role); // This will throw a SQLException if it fails
	        Guardian g = null;
	        while(rs.next())
	        	g = new Guardian(role, getStringHelper("GivenName",rs), getStringHelper("FamilyName",rs), getStringHelper("Phone",rs), getStringHelper("Address",rs), getStringHelper("City",rs), getStringHelper("State",rs), getStringHelper("Zip",rs)); 
	      
	        return g;
	    } catch (Exception e){
	    	System.out.println(e);
	    	
	    	return null;
	    
	    }
	    finally {
	    	// This will run whether we throw an exception or not
	        if (stmt != null) { stmt.close(); }
	    }	
	}
	
	public Boolean updateAllergy(Connection conn, String pid, String reactiontype, String aid, String substance) throws SQLException {	
		Statement stmt = null;
	    try {
	        stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery("UPDATE Allergy A SET " + 
									        "A.ReactionType=" + reactiontype + ", " +
									        "A.AllerginId=" + aid + ", " + 
									        "A.Substance=" + substance + 
									        "WHERE A.PatientId=" + pid); // This will throw a SQLException if it fails
	        return true;
	    } catch (Exception e){
	    	return false;
	    }
	    finally {
	    	// This will run whether we throw an exception or not
	        if (stmt != null) { stmt.close(); }
	    }	
	}
	
	public Boolean updatePlan(Connection conn, String pid, String procdate, String desc) throws SQLException {	
		Statement stmt = null;
	    try {
	        stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery("UPDATE Plan P SET" +
									        "P.ProcDate=" + procdate + ", " +
									        "P.Desc=" + desc + 
									        "WHERE P.PatientId=" + pid); // This will throw a SQLException if it fails
	        return true;
	    } catch (Exception e){
	    	return false;
	    }
	    finally {
	    	// This will run whether we throw an exception or not
	        if (stmt != null) { stmt.close(); }
	    }	
	}
	
	public Boolean updatePatient(Connection conn, String pid, String role, String name, String famname, String suffix, String gender, String birthtime, String provId, String xmlHealth) throws SQLException {	
		Statement stmt = null;
	    try {
	        stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery("UPDATE Patient P SET " + 
									        "P.PatientRole=" + role + ", " +
									        "P.GivenName=" + name + ", " +
									        "P.FamilyName=" + famname + ", " +
									        "P.Suffix=" + suffix + ", " +
									        "P.Gender=" + gender + ", " +
									        "P.BirthTime=" + birthtime + ", " +
									        "P.ProviderID=" + provId + ", " +
									        "P.XMLHealth=" + xmlHealth + 
									        "WHERE P.patientId=" + pid); // This will throw a SQLException if it fails
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
	        ResultSet rs = stmt.executeQuery("SELECT P.Description, P.ProcedureDate FROM Plan P WHERE P.PatientId=" + pid + ";"); // This will throw a SQLException if it fails
	        while(rs.next()){
	        	Plan p = new Plan(pid, getStringHelper("Description",rs), getStringHelper("ProcedureDate",rs));
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
	        ResultSet rs = stmt.executeQuery("SELECT * FROM Allergy A WHERE A.AllerginId=" + pid + ";"); // This will throw a SQLException if it fails
	        while(rs.next()){	
	        	Allergy a = new Allergy(pid, getStringHelper("AllerginId", rs), getStringHelper("Substance",rs), getStringHelper("ReactionType",rs), getStringHelper("Status", rs));
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
	
	public List<Patient> patientsWithAllergy(Connection conn, String aid) throws SQLException {
		Statement stmt = null;
	    try {
	    	List<Patient> l = new ArrayList<Patient>();
	        stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM Allergy A WHERE A.AllerginId=" + aid + ";"); // This will throw a SQLException if it fails
	        while(rs.next()){	
	        	Patient p = new Patient(getStringHelper("PatientId", rs), getStringHelper("PatientRole", rs), getStringHelper("GivenName",rs), getStringHelper("FamilyName", rs), getStringHelper("Suffix", rs), getStringHelper("Gender", rs), getStringHelper("Birthtime", rs), getStringHelper("ProviderId", rs), getStringHelper("XMLHealth", rs));
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

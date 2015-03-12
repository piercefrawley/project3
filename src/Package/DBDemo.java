package Package;
import Utils.HelperFunctions;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.lang.reflect.*;

/**
 * This class demonstrates how to connect to MySQL and run some basic commands.
 * 
 * In order to use this, you have to download the Connector/J driver and add
 * its .jar file to your build path.  You can find it here:
 * 
 * http://dev.mysql.com/downloads/connector/j/
 * 
 * You will see the following exception if it's not in your class path:
 * 
 * java.sql.SQLException: No suitable driver found for jdbc:mysql://localhost:3306/
 * 
 * To add it to your class path:
 * 1. Right click on your project
 * 2. Go to Build Path -> Add External Archives...
 * 3. Select the file mysql-connector-java-5.1.24-bin.jar
 *    NOTE: If you have a different version of the .jar file, the name may be
 *    a little different.
 *    
 * The user name and password are both "root", which should be correct if you followed
 * the advice in the MySQL tutorial. If you want to use different credentials, you can
 * change them below. 
 * 
 * You will get the following exception if the credentials are wrong:
 * 
 * java.sql.SQLException: Access denied for user 'userName'@'localhost' (using password: YES)
 * 
 * You will instead get the following exception if MySQL isn't installed, isnt
 * running, or if your serverName or portNumber are wrong:
 * 
 * java.net.ConnectException: Connection refused
 */
public class DBDemo {

	/** The name of the MySQL account to use (or empty for anonymous) */
	private final String userName = "root";

	/** The password for the MySQL account (or empty for anonymous) */
	private final String password = "CS174a";

	/** The name of the computer running MySQL */
	private final String serverName = "localhost";

	/** The port of the MySQL server (default is 3306) */
	private final int portNumber = 3306;

	/** The name of the database we are testing with (this default is installed with MySQL) */
	private final String dbName = "healthmessagesexchange3";
	
	/** The name of the table we are testing with */
	private final String tableName = "JDBC_TEST";
	
	/**
	 * Get a new database connection
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.userName);
		connectionProps.put("password", this.password);

		conn = DriverManager.getConnection("jdbc:mysql://"
				+ this.serverName + ":" + this.portNumber + "/" + this.dbName,
				connectionProps);

		return conn;
	}

	
		
		/**
		 * Connect to the DB and do some stuff
		 * @throws SQLException 
		 * @throws ParseException 
		 * @throws IllegalAccessException 
		 * @throws IllegalArgumentException 
		 */
		public static void main(String[] args) throws SQLException, ParseException, IllegalArgumentException, IllegalAccessException {
			DBDemo app = new DBDemo();
			//app.run();
			System.out.println(args[0]);
			HelperFunctions HF = new HelperFunctions();
			//patient TODO: add gaurdian
			if(args[0].contains("1"))
			{
				System.out.println("inside");
			
					String userID = System.console().readLine("Enter Patient ID: ");
					
					Patient p = HF.getPatient(app.getConnection(), userID);
					if(p == null)
					{
						System.out.println("Error: Could not find patient");
					}
					else
					{
						Field[] fields = p.getClass().getDeclaredFields();
						for(Field f: fields)
						{
							System.out.println(f.getName() +": " + f.get(p));
						}
						
						System.out.println("Hi " + p.getGivenName() +", Would you like to edit? (enter: yes/no, if you want to view/edit guardian enter 'guardian') ");
						String edit = System.console().readLine();
						while(!(edit.contentEquals("yes") || edit.contentEquals("no") || edit.contentEquals("guardian")))
							edit = System.console().readLine("(Incorrect Response) Hi " + p.getGivenName() +", Would you like to edit? (enter: yes/no, if you want to view/edit guardian enter 'guardian') ");
						if(edit.contentEquals("yes"))
						{
							
							
							System.out.println("\nWhich Column/Input do you want to change (if there is space in your input add %20 instead): ");
							String stringOfChanges = System.console().readLine();
							String[] arrayOfChanges = stringOfChanges.split(" ");
							for(String s: arrayOfChanges)
							{
								String[] input = s.split("/");
								input[1] = input[1].replace("%20", " ");
								switch(input[0])
								{
						
								case "PatientRole":
									p.setPatientrole(input[1]);
									break;
								case "GivenName":
									p.setGivenName(input[1]);
									break;
								case "FamilyName":
									p.setFamilyName(input[1]);
									break;
								case "Suffix":
									p.setSuffix(input[1]);
									break;
								case "Gender":
									p.setGender(input[1]);
									break;
								case "BirthTime":
									p.setBirthTime(input[1]);
									break;
								case "ProviderId":
									p.setProviderId(input[1]);
									break;
								case "XMLHealth":
									p.setXmlCreationdate(input[1]);
									break;
								default:
									break;
							}
							}
							HF.updatePatient(app.getConnection(), p.PatientId, p.PatientRole, p.GivenName, p.FamilyName, p.Suffix, p.Gender, p.Birthtime, p.ProviderId, p.XMLHealth);
							//
						}
						else if(edit.contentEquals("guardian"))
						{
							//lookup guardian based on PatientRole attribute
							if(p.PatientRole == null)
							{
								System.out.println("no guardian for that patient");
							}
							Guardian g = HF.getGuardian(app.getConnection(),p.PatientRole);
							Field[] fieldses = g.getClass().getDeclaredFields();
							for(Field f: fieldses)
							{
								System.out.println(f.getName() +": " + f.get(g));
							}
							
							
							//edit guardian
							System.out.println("Hi " + p.GivenName +", " + g.GivenName +" is your guardian, Would you like to edit? (enter: yes/no) ");
							String editt = System.console().readLine();
							while(!(editt.contentEquals("yes") || editt.contentEquals("no") ))
								editt = System.console().readLine("(Incorrect Response) Hi " + p.GivenName +", " + g.GivenName +" is your guardian,, Would you like to edit? (enter: yes/no) ");
							if(editt.contentEquals("yes"))
							{
									
								System.out.println("\nWhich Column/Input do you want to change (if there is space in your input add %20 instead): ");
								String stringOfChanges = System.console().readLine();
								String[] arrayOfChanges = stringOfChanges.split(" ");
								for(String s: arrayOfChanges)
								{
									String[] input = s.split("/");
									input[1] = input[1].replace("%20", " ");
									switch(input[0])
									{
							
									
									case "GivenName":
										g.GivenName = input[1];
										break;
									case "FamilyName":
										g.FamilyName = input[1];
										break;
									case "Phone":
										g.Phone = input[1];
										break;
									case "Address":
										g.Address = input[1];
										break;
									case "City":
										g.City = input[1];
										break;
									case "State":
										g.State = input[1];
										break;
									case "Zip":
										g.Zip = input[1];
										break;
									default:
										break;
								}
								}
								HF.updateGuardian(app.getConnection(), g.GuardianNo, g.GivenName, g.FamilyName, g.Phone, g.Address, g.City, g.State, g.Zip);
								
							
						}
					}
					
					
				
			}
			}
			//Doctor and Author
			else if(args[0].contains("2"))
			{
				//String doctorID = System.console().readLine("Enter Doctor or Author ID: ");
				//TODO: show all the patients
				String pID = System.console().readLine("Which patient do you want to view or edit (Enter patientId)?");
				//TODO:  Show that patient's info
				
				Patient p = HF.getPatient(app.getConnection(), pID);
				if(p == null)
				{
					System.out.println("Error: Could not find patient");
				}
				else
				{
					Field[] fields = p.getClass().getDeclaredFields();
					for(Field f: fields)
					{
						System.out.println(f.getName() +": " + f.get(p));
					}
				}
				
				String editOrView  = System.console().readLine("\nWould you like to edit the patient's info or view his/her Plan and Allergin (edit/view)?");
				while(!(editOrView.contentEquals("edit") || editOrView.contentEquals("view")))
					editOrView  = System.console().readLine("Would you like to edit the patient's info or view his/her Plan and Allergin (edit/view)?");
				if(editOrView.contentEquals("view"))
				{
					
						String planOrAllergin = System.console().readLine("Plan or Allergin (plan/allergin)");
						while(!(planOrAllergin.contentEquals("plan") || planOrAllergin.contentEquals("allergin")))
						{
							planOrAllergin = System.console().readLine("(Error Input) Plan or Allergin");
						}
						if(planOrAllergin.contentEquals("allergin"))
						{
							//TODO: use the patientID to view the Allergin and Plan
							List<Allergy> listOfAllergy = HF.getAllergy(app.getConnection(), p.PatientId);
							//iterate through each allergy and print out their info
							for(Allergy a : listOfAllergy)
							{
								
								Field[] fields = a.getClass().getDeclaredFields();
								for(Field f: fields)
								{
									System.out.println(f.getName() +": " + f.get(a));
								}
								System.out.println();
							}
							String edit = System.console().readLine("\nEdit Allergin (yes/no)");
							while(!(edit.contentEquals("yes") || edit.contentEquals("no")))
								edit  = System.console().readLine("Edit Allergin (yes/no)");
							
							if(edit.contentEquals("yes"))
							{
								String index = System.console().readLine("Which Allergy do you want to edit (enter index)?");
								Allergy a = listOfAllergy.get(Integer.parseInt(index));
								System.out.println("\nWhich Column/Input do you want to change (if there is space in your input add %20 instead) ");
								String stringOfChanges = System.console().readLine();
								String[] arrayOfChanges = stringOfChanges.split(" ");
								
								for(String s: arrayOfChanges)
								{
									String[] input = s.split("/");
									input[1] = input[1].replace("%20", " ");
									switch(input[0])
									{
									case "PatientId":
										a.PatientId = input[1];
										break;
									case "Substance":
										a.Substance = input[1];
										break;
									case "ReactionType":
										a.ReactionType = input[1];
										break;
									case "Status":
										a.Status = input[1];
										break;
									default:
										break;
								}
								}
								HF.updateAllergy(app.getConnection(),a.PatientId, a.ReactionType, a.AllerginId, a.Substance, a.Status);
								
							
								
							}
						}
						else // plan
						{
							List<Plan> listOfPlan= HF.getPlan(app.getConnection(), p.PatientId);
							//iterate through each plan and print out their info
							for(Plan plan : listOfPlan)
							{
								Field[] fields = plan.getClass().getDeclaredFields();
								for(Field f: fields)
								{
									System.out.println(f.getName() +": " + f.get(plan));
								}
								System.out.println();
							}
							
							String edit = System.console().readLine("\nEdit Plan (yes/no)");
							while(!(edit.contentEquals("yes") || edit.contentEquals("no")))
								edit  = System.console().readLine("Edit Plan (yes/no)");
							
							if(edit.contentEquals("yes"))
							{
								String index = System.console().readLine("Which Plan do you want to edit (enter index)?");
								Plan a = listOfPlan.get(Integer.parseInt(index));
								
								System.out.println("\nWhich Column/Input do you want to change (if there is space in your input add %20 instead) ");
								String stringOfChanges = System.console().readLine();
								String[] arrayOfChanges = stringOfChanges.split(" ");
								
								for(String s: arrayOfChanges)
								{
									String[] input = s.split("/");
									input[1] = input[1].replace("%20", " ");
									switch(input[0])
									{
												
									case "PatientId":
										a.PatientId = input[1];
										break;
									case "Description":
										a.Description = input[1];
										break;
									case "ProcDate":
										a.ProcDate = input[1];
										break;
									default:
										break;
								}
								}
								HF.updatePlan(app.getConnection(), a.PatientId, a.ProcDate, a.Description, a.PlanId);
								
							
						}
						//edit Allergin and Plan
						
						
					}
				}
				else
				{
					
					System.out.println("\nWhich Column/Input do you want to change (if there is space in your input add %20 instead): ");
					String stringOfChanges = System.console().readLine();
					String[] arrayOfChanges = stringOfChanges.split(" ");
					for(String s: arrayOfChanges)
					{
						String[] input = s.split("/");
						input[1] = input[1].replace("%20", " ");
						switch(input[0])
						{
							
						case "PatientRole":
							p.setPatientrole(input[1]);
							break;
						case "GivenName":
							p.setGivenName(input[1]);
							break;
						case "FamilyName":
							p.setFamilyName(input[1]);
							break;
						case "Suffix":
							p.setSuffix(input[1]);
							break;
						case "Gender":
							p.setGender(input[1]);
							break;
						case "BirthTime":
							p.setBirthTime(input[1]);
							break;
						case "ProviderId":
							p.setProviderId(input[1]);
							break;
						case "XMLHealth":
							p.setXmlCreationdate(input[1]);
							break;
						default:
							break;
					}
					}
					HF.updatePatient(app.getConnection(), p.PatientId, p.PatientRole, p.GivenName, p.FamilyName, p.Suffix, p.Gender, p.Birthtime, p.ProviderId, p.XMLHealth);
				
				}
				
				
			}
			
		
			//Admin
			else
			{
				System.out.println("\nView number of patients for each type of allergy(1)\n"
						+ "List the patients who have more than one allergy(2)\n"
						+ "List the patients who have a plan for surgery today(3)\n"
						+ "Identify authors with more than one patient(4)");
				String input = System.console().readLine();
				switch(input)
				{
				case "1":
					
					HashMap<String,Integer> hm = HF.numPatientsWithAllergy(app.getConnection());
					Iterator<Entry<String,Integer>> it = hm.entrySet().iterator();
				    while (it.hasNext()) {
				        HashMap.Entry<String,Integer> pair = (HashMap.Entry<String,Integer>)it.next();
				        System.out.println(pair.getKey() + " = " + pair.getValue());
				        it.remove(); 
				    }
					break;
				case "2":
					List<Patient> list = HF.patientsWithMoreThan1Allergy(app.getConnection());
					if(list != null){
						for(Patient pt : list)
						{
							Field[] fields = pt.getClass().getDeclaredFields();
							for(Field f: fields)
							{
								System.out.println(f.getName() +": " + f.get(pt));
							}
							System.out.println();
						}
					}
					break;
				case "3":
					List<Patient> listt = HF.patientsSurgeryToday(app.getConnection());
					if(listt != null)
					{
						for(Patient pt : listt)
						{
							Field[] fields = pt.getClass().getDeclaredFields();
							for(Field f: fields)
							{
								System.out.println(f.getName() +": " + f.get(pt));
							}
							System.out.println();
						}
					}
					break;
				
				case "4":
					List<Author> listtt = HF.authorsWithMoreThan1Patient(app.getConnection());
					if(listtt != null)
					{
						for(Author pt : listtt)
						{
							Field[] fields = pt.getClass().getDeclaredFields();
							for(Field f: fields)
							{
								System.out.println(f.getName() +": " + f.get(pt));
							}
							System.out.println();
						}
					}
					break;

				}
			}
			
			}
		}


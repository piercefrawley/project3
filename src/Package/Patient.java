package Package;

import java.util.Date;

public class Patient {
private String patientId;
private String patientrole;
private String FirstName;
private String GivenName;
private String FamilyName;
private String suffix;
private String gender;
private Date BirthTime;
private String providerId;
private Date xmlCreationdate;
public Patient(String ID, String patientrole, String firstName, String givenname, String familyname, String suffix, String gender, Date birthtime, String providerid, Date xmlCreationDate)
{
this.patientId = ID;
this.patientrole = patientrole;
this.GivenName = givenname;
this.FamilyName = familyname;
this.FirstName = firstName;
this.suffix = suffix;
this.gender = gender;
this.BirthTime = birthtime;
this.providerId = providerid;
this.xmlCreationdate = xmlCreationDate;
}
public String getPatientId() {
	return patientId;
}
public void setPatientId(String patientId) {
	this.patientId = patientId;
}
public String getPatientrole() {
	return patientrole;
}
public void setPatientrole(String patientrole) {
	this.patientrole = patientrole;
}
public String getFirstName() {
	return FirstName;
}
public void setFirstName(String firstName) {
	FirstName = firstName;
}
public String getGivenName() {
	return GivenName;
}
public void setGivenName(String givenName) {
	GivenName = givenName;
}
public String getFamilyName() {
	return FamilyName;
}
public void setFamilyName(String familyName) {
	FamilyName = familyName;
}
public String getSuffix() {
	return suffix;
}
public void setSuffix(String suffix) {
	this.suffix = suffix;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public Date getBirthTime() {
	return BirthTime;
}
public void setBirthTime(Date birthTime) {
	BirthTime = birthTime;
}
public String getProviderId() {
	return providerId;
}
public void setProviderId(String providerId) {
	this.providerId = providerId;
}
public Date getXmlCreationdate() {
	return xmlCreationdate;
}
public void setXmlCreationdate(Date xmlCreationdate) {
	this.xmlCreationdate = xmlCreationdate;
}


}

package Package;

public class Patient {
	public String PatientId;
	public String PatientRole;
	public String GivenName;
	public String FamilyName;
	public String Suffix;
	public String Gender;
	public String Birthtime;
	public String ProviderId;
	public String XMLCreateDate;
	
	public Patient(String ID, String patientrole, String name, String familyname, String suffix, String gender, String birthtime, String providerid, String xmlHealth) {
		this.PatientId = ID;
		this.PatientRole = patientrole;
		this.GivenName = name;
		this.FamilyName = familyname;
		this.Suffix = suffix;
		this.Gender = gender;
		this.Birthtime = birthtime;
		this.ProviderId = providerid;
		this.XMLCreateDate = xmlHealth;
	}
	
	public String getPatientId() {
		return PatientId;
	}
	public void setPatientId(String patientId) {
		this.PatientId = patientId;
	}
	public String getPatientrole() {
		return PatientRole;
	}
	public void setPatientrole(String patientrole) {
		this.PatientRole = patientrole;
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
		return Suffix;
	}
	public void setSuffix(String suffix) {
		this.Suffix = suffix;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		this.Gender = gender;
	}
	public String getBirthTime() {
		return Birthtime;
	}
	public void setBirthTime(String birthTime) {
		Birthtime = birthTime;
	}
	public String getProviderId() {
		return ProviderId;
	}
	public void setProviderId(String providerId) {
		this.ProviderId = providerId;
	}
	public String getXmlCreationdate() {
		return XMLCreateDate;
	}
	public void setXmlCreationdate(String xmlCreationdate) {
		this.XMLCreateDate = xmlCreationdate;
	}
}

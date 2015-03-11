package Package;

import java.util.Date;

public class Plan {
	public String patientId;
	public String description;
	public Date procedureDate;
	
	public Plan(String ID, String desc, Date procDate) {
	this.patientId = ID;
	this.description = desc;
	this.procedureDate = procDate;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getProcedureDate() {
		return procedureDate;
	}

	public void setProcedureDate(Date procedureDate) {
		this.procedureDate = procedureDate;
	}
}
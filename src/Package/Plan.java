package Package;

public class Plan {
	public String PatientId;
	public String PlanId;
	public String Description;
	public String ProcDate;
	
	public Plan(String pid, String planid, String desc, String procDate) {
	this.PatientId = pid;
	this.PlanId = planid;
	this.Description = desc;
	this.ProcDate = procDate;
	}

	public String getPatientId() {
		return PatientId;
	}

	public void setPatientId(String patientId) {
		this.PatientId = patientId;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		this.Description = description;
	}

	public String getProcedureDate() {
		return ProcDate;
	}

	public void setProcedureDate(String procedureDate) {
		this.ProcDate = procedureDate;
	}
}
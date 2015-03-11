package Package;

import java.util.Date;

public class Allergy {
	public String patientId;
	public String allerginId;
	public String substance;
	public String reactionType;
	public String status;
	
	public Allergy(String aid, String sub, String react, String stat) {
		this.allerginId = aid;
		this.substance = sub;
		this.reactionType = react;
		this.status = stat;
	}

	public String getAllerginId() {
		return allerginId;
	}

	public void setAllerginId(String allerginId) {
		this.allerginId = allerginId;
	}

	public String getSubstance() {
		return substance;
	}

	public void setSubstance(String substance) {
		this.substance = substance;
	}

	public String getReactionType() {
		return reactionType;
	}

	public void setReactionType(String reactionType) {
		this.reactionType = reactionType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
package Package;

public class Allergy {
	public String PatientId;
	public String AllerginId;
	public String Substance;
	public String ReactionType;
	public String Status;
	
	public Allergy(String pid, String aid, String sub, String react, String stat) {
		this.PatientId = pid;
		this.AllerginId = aid;
		this.Substance = sub;
		this.ReactionType = react;
		this.Status = stat;
	}

	public String getAllerginId() {
		return AllerginId;
	}

	public void setAllerginId(String allerginId) {
		this.AllerginId = allerginId;
	}

	public String getSubstance() {
		return Substance;
	}

	public void setSubstance(String substance) {
		this.Substance = substance;
	}

	public String getReactionType() {
		return ReactionType;
	}

	public void setReactionType(String reactionType) {
		this.ReactionType = reactionType;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		this.Status = status;
	}
}
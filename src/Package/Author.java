package Package;

public class Author {
	public String AuthorId;
	public String PatientId;
	public String AuthorTitle;
	public String AuthorFirstName;
	public String AuthorLastName;
	public String ParticipatingRole;

	public Author(String aid, String pid, String title, String fname, String lname, String role) {
		this.AuthorId = aid;
		this.PatientId = pid;
		this.AuthorTitle = title;
		this.AuthorFirstName = fname;
		this.AuthorLastName = lname;
		this.ParticipatingRole = role;
	}
	
	String getAuthorId() {
		return AuthorId;
	}

	void setAuthorId(String authorId) {
		AuthorId = authorId;
	}

	String getPatientId() {
		return PatientId;
	}

	void setPatientId(String patientId) {
		PatientId = patientId;
	}

	String getAuthorTitle() {
		return AuthorTitle;
	}

	void setAuthorTitle(String authorTitle) {
		AuthorTitle = authorTitle;
	}

	String getAuthorFirstName() {
		return AuthorFirstName;
	}

	void setAuthorFirstName(String authorFirstName) {
		AuthorFirstName = authorFirstName;
	}

	String getAuthorLastName() {
		return AuthorLastName;
	}

	void setAuthorLastName(String authorLastName) {
		AuthorLastName = authorLastName;
	}

	String getParticipatingRole() {
		return ParticipatingRole;
	}

	void setParticipatingRole(String participatingRole) {
		ParticipatingRole = participatingRole;
	}
}
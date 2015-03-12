package Package;

public class Guardian {
	public String GuardianNo; 
	public String GivenName;
	public String FamilyName;
	public String Phone;
	public String Address;
	public String City;
	public String State;
	public String Zip;
	
	public Guardian(String gnum, String name, String famname, String phone, String address, String city, String state, String zip) {
		this.GuardianNo = gnum;
		this.GivenName = name;
		this.FamilyName = famname;
		this.Phone = phone;
		this.Address = address;
		this.City = city;
		this.State = state;
		this.Zip = zip;
	}

	String getGuardianNo() {
		return GuardianNo;
	}

	void setGuardianNo(String guardianNo) {
		GuardianNo = guardianNo;
	}

	String getGivenName() {
		return GivenName;
	}

	void setGivenName(String givenName) {
		GivenName = givenName;
	}

	String getFamilyName() {
		return FamilyName;
	}

	void setFamilyName(String familyName) {
		FamilyName = familyName;
	}

	String getPhone() {
		return Phone;
	}

	void setPhone(String phone) {
		Phone = phone;
	}

	String getAddress() {
		return Address;
	}

	void setAddress(String address) {
		Address = address;
	}

	String getCity() {
		return City;
	}

	void setCity(String city) {
		City = city;
	}

	String getState() {
		return State;
	}

	void setState(String state) {
		State = state;
	}

	String getZip() {
		return Zip;
	}

	void setZip(String zip) {
		Zip = zip;
	}
}
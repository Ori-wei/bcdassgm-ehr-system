package DatabaseObject;

public class Clinician {
	private String clinicianID;
	private String username;
	private String saltedPassword;
	private String salt;
	private String designation;
	private String department;
	private String name;
	private String hospitalID;
		
	public Clinician(String clinicianID, String username, String saltedPassword, String salt) {
		super();
		this.clinicianID = clinicianID;
		this.username = username;
		this.saltedPassword = saltedPassword;
		this.salt = salt;
	}
	
	public String getClinicianID() {
		return clinicianID;
	}
	public void setClinicianID(String clinicianID) {
		this.clinicianID = clinicianID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSaltedPassword() {
		return saltedPassword;
	}
	public void setSaltedPassword(String saltedPassword) {
		this.saltedPassword = saltedPassword;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHospitalID() {
		return hospitalID;
	}
	public void setHospitalID(String hospitalID) {
		this.hospitalID = hospitalID;
	}
	
	
	
}

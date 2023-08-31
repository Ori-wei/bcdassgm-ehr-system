package DatabaseObject;

public class Patient {
	private String patientID;
	private String name; 
	private String phoneNumber;
	private String email;
	private String address;
	private String DoB;
	private String sex;
	private String emergencyContactPerson;
	private String relationship;
	private String emergencyPhoneNumber;
	
	
	
	public Patient(String patientID, String name) {
		super();
		this.patientID = patientID;
		this.name = name;
	}
	public String getPatientID() {
		return patientID;
	}
	public void setPatientID(String patientID) {
		this.patientID = patientID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDoB() {
		return DoB;
	}
	public void setDoB(String doB) {
		DoB = doB;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getEmergencyContactPerson() {
		return emergencyContactPerson;
	}
	public void setEmergencyContactPerson(String emergencyContactPerson) {
		this.emergencyContactPerson = emergencyContactPerson;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	public String getEmergencyPhoneNumber() {
		return emergencyPhoneNumber;
	}
	public void setEmergencyPhoneNumber(String emergencyPhoneNumber) {
		this.emergencyPhoneNumber = emergencyPhoneNumber;
	}
	@Override
	public String toString() {
	    return this.getPatientID(); // assuming getPatientID() returns the name of the patient
	}
	
	
	
}

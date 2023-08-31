package DatabaseObject;

public class Hospital {
	private String hospitalID;
	private String name;
	private String address;
	private String contact;
		
	public Hospital(String hospitalID, String name) {
		super();
		this.hospitalID = hospitalID;
		this.name = name;
	}
	public String getHospitalID() {
		return hospitalID;
	}
	public void setHospitalID(String hospitalID) {
		this.hospitalID = hospitalID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	@Override
	public String toString() {
	    return this.getName(); // assuming getName() returns the name of the hospital
	}
	
}

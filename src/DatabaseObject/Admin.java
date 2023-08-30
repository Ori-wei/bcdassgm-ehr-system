package DatabaseObject;

public class Admin {
	private String adminID;
	private String username;
	private String saltedPassword;
	private String salt;
	
	public Admin(String adminID, String username, String saltedPassword, String salt) {
		this.adminID = adminID;
		this.username = username;
		this.saltedPassword = saltedPassword;
		this.salt = salt;
	}
	public String getAdminID() {
		return adminID;
	}
	public void setAdminID(String adminID) {
		this.adminID = adminID;
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
	
}

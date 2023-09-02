package BlockchainObject;

public class ClinicalSummary {
	private String CSID;
	private String Date;
	private String admissionID;
	private String statusAtDischarge;
	
	public ClinicalSummary(String cSID, String date, String admissionID, String statusAtDischarge) {
		super();
		CSID = cSID;
		Date = date;
		this.admissionID = admissionID;
		this.statusAtDischarge = statusAtDischarge;
	}

	public String getCSID() {
		return CSID;
	}
	public void setCSID(String cSID) {
		CSID = cSID;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public String getAdmissionID() {
		return admissionID;
	}
	public void setAdmissionID(String admissionID) {
		this.admissionID = admissionID;
	}
	public String getStatusAtDischarge() {
		return statusAtDischarge;
	}
	public void setStatusAtDischarge(String statusAtDischarge) {
		this.statusAtDischarge = statusAtDischarge;
	}
	
	
}

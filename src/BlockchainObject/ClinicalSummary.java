package BlockchainObject;

public class ClinicalSummary {
	private String CSID;
	private String Date;
	
	public ClinicalSummary(String CSID, String date) {
		this.CSID = CSID;
		Date = date;
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
	
	
}

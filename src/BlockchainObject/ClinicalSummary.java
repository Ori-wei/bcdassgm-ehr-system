package BlockchainObject;

public class ClinicalSummary {
	private String CSID;
	private String areaOfSpecialist;
	private String dateOfVisit;
	private String doctorID;
	private String history;
	private String physicalExamination;
	private String diagnosis;
	private String summary;
	private String treatment;
	private String followUpProgress;
	private String admissionID;
	private String dateTimeOfAdmission;
	private String dateTimeOfDischarge;
	private String statusAtDischarge;
	private String timestamp;
	private String signature;
	
	
	public ClinicalSummary(String cSID, String timestamp, String admissionID, String statusAtDischarge) {
		super();
		CSID = cSID;
		this.timestamp = timestamp;
		this.admissionID = admissionID;
		this.statusAtDischarge = statusAtDischarge;
	}
	
	
	public ClinicalSummary(String cSID, String areaOfSpecialist, String dateOfVisit, String doctorID, String history,
			String physicalExamination, String diagnosis, String summary, String treatment, String followUpProgress,
			String admissionID, String dateTimeOfAdmission, String dateTimeOfDischarge, String statusAtDischarge,
			String timestamp, String signature) {
		super();
		CSID = cSID;
		this.areaOfSpecialist = areaOfSpecialist;
		this.dateOfVisit = dateOfVisit;
		this.doctorID = doctorID;
		this.history = history;
		this.physicalExamination = physicalExamination;
		this.diagnosis = diagnosis;
		this.summary = summary;
		this.treatment = treatment;
		this.followUpProgress = followUpProgress;
		this.admissionID = admissionID;
		this.dateTimeOfAdmission = dateTimeOfAdmission;
		this.dateTimeOfDischarge = dateTimeOfDischarge;
		this.statusAtDischarge = statusAtDischarge;
		this.timestamp = timestamp;
		this.signature = signature;
	}


	public String getAreaOfSpecialist() {
		return areaOfSpecialist;
	}

	public void setAreaOfSpecialist(String areaOfSpecialist) {
		this.areaOfSpecialist = areaOfSpecialist;
	}

	public String getDateOfVisit() {
		return dateOfVisit;
	}

	public void setDateOfVisit(String dateOfVisit) {
		this.dateOfVisit = dateOfVisit;
	}

	public String getDoctorID() {
		return doctorID;
	}

	public void setDoctorID(String doctorID) {
		this.doctorID = doctorID;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public String getPhysicalExamination() {
		return physicalExamination;
	}

	public void setPhysicalExamination(String physicalExamination) {
		this.physicalExamination = physicalExamination;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getTreatment() {
		return treatment;
	}

	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}

	public String getFollowUpProgress() {
		return followUpProgress;
	}

	public void setFollowUpProgress(String followUpProgress) {
		this.followUpProgress = followUpProgress;
	}

	public String getDateTimeOfAdmission() {
		return dateTimeOfAdmission;
	}

	public void setDateTimeOfAdmission(String dateTimeOfAdmission) {
		this.dateTimeOfAdmission = dateTimeOfAdmission;
	}

	public String getDateTimeOfDischarge() {
		return dateTimeOfDischarge;
	}

	public void setDateTimeOfDischarge(String dateTimeOfDischarge) {
		this.dateTimeOfDischarge = dateTimeOfDischarge;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getCSID() {
		return CSID;
	}
	public void setCSID(String cSID) {
		CSID = cSID;
	}
	public String getdateOfVisit() {
		return dateOfVisit;
	}
	public void setdateOfVisit(String date) {
		this.dateOfVisit = date;
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

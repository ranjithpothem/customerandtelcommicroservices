package com.database.InternalDB.DTOResponse;

public class DTOResponse {

	private String status;
	private String responseCode;
	private String uniqueAcknowledgementID;

	public DTOResponse(String status, String responseCode, String uniqueAcknowledgementID) {
		this.status = status;
		this.responseCode = responseCode;
		this.uniqueAcknowledgementID = uniqueAcknowledgementID;
	}

	@Override
	public String toString() {
		return "status=" + status + ", responseCode=" + responseCode + ", uniqueAcknowledgementID="
				+ uniqueAcknowledgementID;
	}

}

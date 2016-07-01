package br.edu.ifrs.canoas.lds.starter.domain;

public enum Status {
	Approved, Waiting, Rejected;
	
	public static Status get(String str){
		if (str.equalsIgnoreCase(Status.Approved.toString()))
			return Status.Approved;
		else if (str.equalsIgnoreCase(Status.Rejected.toString()))
			return Status.Rejected;
		else if (str.equalsIgnoreCase(Status.Waiting.toString()))
			return Status.Waiting;
		return null;
	}

}
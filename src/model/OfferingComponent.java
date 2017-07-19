package model;


public class OfferingComponent implements Comparable<OfferingComponent>{
	private String type;
	private int enrollmentCap;
	private int enrollmentTotal;
	
	public OfferingComponent(String type, int enrollmentCap, int enrllomentTotal) {
		this.type = type;
		this.enrollmentCap = enrollmentCap;
		this.enrollmentTotal = enrllomentTotal;
	}

	public String getType() {
		return type;
	}

	public int getEnrollmentCap() {
		return enrollmentCap;
	}

	public int getEnrollmentTotal() {
		return enrollmentTotal;
	}

	public void addToComponent(int enrollmentCap, int enrollmentTotal) {
		this.enrollmentCap += enrollmentCap;
		this.enrollmentTotal += enrollmentTotal;
	}
	
	@Override
	public int compareTo(OfferingComponent other) {
		return type.compareTo(other.getType());
	}		
	
	@Override
	public String toString() {
		return "Type=" + type + ", Enrollment="+enrollmentTotal+"/"+enrollmentCap;
	}
}
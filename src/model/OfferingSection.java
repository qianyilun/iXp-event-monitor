package model;


/**
 * Store information about a single section of a specific offering of a course.
 * For example: The lecture component, of the Surrey-Spring-2013 offering, of CMPT 213.
 */
public class OfferingSection implements Comparable<OfferingSection>{
	private String type;
	private int enrollmentCap;
	private String enrollmentTotal;
	
	
	public OfferingSection(String type, int enrollmentCap, String enrllomentTotal) {
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
	
	public String getEnrollmentTotal() {
		return enrollmentTotal;
	}
 
	public void addToSection(int enrollmentCap, String enrollmentTotal) {
		this.enrollmentCap += enrollmentCap;
		this.enrollmentTotal += enrollmentTotal;
	}
	
	@Override
	public int compareTo(OfferingSection other) {
		return type.compareTo(other.getType());
	}		
	
	@Override
	public String toString() {
		return "Type=" + type + ", Enrollment="+enrollmentTotal+"/"+enrollmentCap;
	}
}
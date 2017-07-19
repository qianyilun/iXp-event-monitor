package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Store information about a course and manage its course offerings.
 */
public class Course implements Comparable<Course>{
	private final static int MAX_UNDER_GRAD_NUMBER = 499;
	
	private Department department;
	private String catalogNumber;
	private List<CourseOffering> offerings = new ArrayList<CourseOffering>();

	public Course(Department department, String catalogNumber) {
		this.department = department;
		this.catalogNumber = catalogNumber;
	}

	public String getCatalogNumber() {
		return catalogNumber;
	}

	public void addSection(Semester semester, String location,
                           String componentType, int enrollmentCap, String enrollmentTotal, String instructor, String title, String description) {
		
		CourseOffering offering = findOrAddNewOffering(semester, location, title, description);
		offering.addSection(componentType, enrollmentCap, enrollmentTotal, instructor);
	}

	private CourseOffering findOrAddNewOffering(Semester semester, String location, String title, String description) {
		for (CourseOffering offering : offerings) {
			if (offering.matches(semester, location, title)) {
				return offering;
			}
		}
		CourseOffering newOffering = new CourseOffering(this, semester, location, title, description);
		offerings.add(newOffering);
		Collections.sort(offerings);
		return newOffering;
	}

	public Iterable<CourseOffering> offerings() {
		return new Iterable<CourseOffering>() {
			@Override
			public Iterator<CourseOffering> iterator() {
				return Collections.unmodifiableList(offerings).iterator();
			}
			
		};
	}

	public boolean isGrad() {
		return getCourseNumberAsInt() > MAX_UNDER_GRAD_NUMBER;
	}
	public boolean isUndergrad() {
		return getCourseNumberAsInt() <= MAX_UNDER_GRAD_NUMBER;
	}
	private int getCourseNumberAsInt() {
		try {
			return Integer.valueOf("0" + catalogNumber.replaceAll("(\\d*).*", "$1"));
		} catch(NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public boolean matchesCatalogNumber(String catalogNumber) {
		return getCatalogNumber().equals(catalogNumber);
	}

	@Override
	public int compareTo(Course other) {
		if (department.compareTo(other.department) != 0){
			return department.compareTo(other.department); 
		} else {
			return getCatalogNumber().compareTo(other.getCatalogNumber());
		}
	}
	
	@Override
	public String toString() {
		return department.getName() + " " + catalogNumber;
	}

}

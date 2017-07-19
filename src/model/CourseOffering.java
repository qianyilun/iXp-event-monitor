package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Store information about a single course offering.
 */
public class CourseOffering implements Comparable<CourseOffering>{
	private Course course;
	private Semester semester;
	private String location;
	private List<String> instructors = new ArrayList<>();
	private List<OfferingSection> sections = new ArrayList<OfferingSection>();
	private String title;
	private String description;

	public CourseOffering(Course course, Semester semester, String location, String title, String description) {
		this.course = course;
		this.semester = semester;
		this.location = location;
		this.title = title;
		this.description = description;
	}
 
	public Course getCourse() {
		return course;
	}
	public Semester getSemester() {
		return semester;
	}
	public String getLocation() {
		return location;
	}
	
	public String getInstructors() {
		String strInstructors = "";
		for (String instructor : instructors) {
			if (strInstructors.length() > 0) {
				strInstructors += ", ";
			}
			strInstructors += instructor;
		}
		return strInstructors;
	}
	
	public void addSection(String sectionType, int enrollmentCap, String enrollmentTotal, String instructor) {
		for (OfferingSection component : sections) {
			if (component.getType().equals(sectionType)) {
				component.addToSection(enrollmentCap, enrollmentTotal);
				return;
			}
		}
		
		if (instructor != null 
				&& instructor.length() > 0
				&& !instructors.contains(instructor)) {
			instructors.add(instructor);
		}
		
		OfferingSection newComponent = new OfferingSection(sectionType, enrollmentCap, enrollmentTotal);
		sections.add(newComponent);
		Collections.sort(sections);
	}
	
	public Iterable<OfferingSection> components() {
		return Collections.unmodifiableList(sections);
	}
	
	public boolean matches(Semester semester, String location, String title) {
		return getSemester().equals(semester) && getLocation().equals(location);
	}

	
	@Override
	public int compareTo(CourseOffering other) {
		if (!semester.equals(other.getSemester())) {
			return semester.compareTo(other.getSemester());
		}
		return location.compareTo(other.getLocation());
	}

	@Override
	public String toString() {
		return semester + " in " + location + " by " + getInstructors();
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}
}

package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Store information about a department (such as CMPT or MATH)
 * and manage its courses.
 */
public class Department implements Comparable<Department>{
	private String name; 
	private List<Course> courses = new ArrayList<Course>();

	public Department(String name) {
		this.name = name;
	}
	
	public void addCourse(Course course) {
		courses.add(course);
	}
	
	public Iterable<Course> courses() {
		return new Iterable<Course>() {
			@Override
			public Iterator<Course> iterator() {
				return Collections.unmodifiableList(courses).iterator();
			}
		};
	}

	public String getName() {
		return name;
	}
	
	public Course findOrMakeCourse(String catalogNumber) {
		for (Course course : courses) {
			if (course.matchesCatalogNumber(catalogNumber)) {
				return course;
			}
		}
		Course newCourse = new Course(this, catalogNumber);
		courses.add(newCourse);
		Collections.sort(courses);
		return newCourse;
	}

	@Override
	public String toString() {
		return name;
	}
	
	@Override
	public int compareTo(Department other) {
		return name.compareTo(other.getName());
	}
}

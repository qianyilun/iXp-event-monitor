package model;

import javax.swing.event.ChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Facade to group model functionality into one interface for centralize access.
 */
public class Model {
	private DepartmentManager departmentManager = new DepartmentManager();
	private CourseFilterManager filterManager = new CourseFilterManager(this);
	private CourseSelectionManager courseSelectManager = new CourseSelectionManager(this);
	private OfferingSelectionManager selectedOfferingManager = new OfferingSelectionManager();

	/*
	 * Manage Departments
	 */
	public void loadDataFromFile(File file) throws FileNotFoundException {
		departmentManager.loadDataFromFile(file);
	}
	public Iterable<Department> departments(){
		return departmentManager;
	}

	/*
	 * Course Filter
	 */
	public void setCourseFilter(CourseFilter filter){
		filterManager.setCourseFilter(filter);
	}
	public CourseFilter getCourseFilter(){
		return filterManager.getCourseFilter();
	}
	public List<Course> getFilteredCourses() {
		return filterManager.getFilteredCourses();
	}
	public void addCourseFilterListener(ChangeListener listener){
		filterManager.addListener(listener);
	}
	
	/*
	 * Selected Courses
	 */
	public void selectCourse(Course course){
		courseSelectManager.selectCourse(course);
	}
	public Course getSelectedCourse() {
		return courseSelectManager.getSelectedCourse();
	}
	public List<CourseOffering> getOfferingsOfSelectedCourse(){
		return courseSelectManager.getOfferingsOfSelectedCourse();
	}
	public void addSelectedCourseListener(ChangeListener listener){
		courseSelectManager.addListener(listener);
	}
	
	/*
	 * Selected Offering
	 */
	public void selectOffering(CourseOffering offering){
		selectedOfferingManager.setSelectedOffering(offering);
	}
	public CourseOffering getSelectedSection(){
		return selectedOfferingManager.getSelectedSection();
	}
	public void addSelectedOfferingListener(ChangeListener listener){
		selectedOfferingManager.addListener(listener);
	}
	
	/*
	 * Debug routines to display the contents of the model to the console.
	 */
	public void dumpModelToConsole() {
		for (Department department : departments()) {
			dumpDepartmentToConsole(department);
		}
	}
	private void dumpDepartmentToConsole(Department department) {
		for (Course course : department.courses()) {
//			System.out.println(course);
			dumpCourseToConsole(course);
		}
	}
	private void dumpCourseToConsole(Course course) {
		for (CourseOffering offering : course.offerings()) {
			dumpComponentsToConsole(offering);
		}
	}
	private void dumpComponentsToConsole(CourseOffering offering) {
//		System.out.println("\t" + offering);
		
		String componentInfo = "";
		for (OfferingSection component : offering.components()) {
			componentInfo += "\t\t" + component + "\n";
		}
		
//		System.out.println("\t\t" + componentInfo.trim());
	}
}

package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Store the selected course filter, apply it to classes in the model, 
 * and notify observers when it changes.
 */
public class CourseFilterManager extends ModelObservableImpl {
	private Model model;
	private CourseFilter filter;
	
	public CourseFilterManager(Model model) {
		this.model = model;
	}
	
	public void setCourseFilter(CourseFilter filter){
		this.filter = filter;
		notifyListeners();
	}
	public CourseFilter getCourseFilter(){
		return filter;
	}
	
	public List<Course> getFilteredCourses() {
		List<Course> courses = new ArrayList<Course>();
		if (filter != null) {
			for (Department department : model.departments()) {
				if (!filter.matchesDepartment(department)) {
					continue;
				}
				
				for (Course course : department.courses()) {
					if (!filter.matchesCourse(course)) {
						continue;
					}
					
					courses.add(course);
				}
			}
		}
		return courses;
	}


}

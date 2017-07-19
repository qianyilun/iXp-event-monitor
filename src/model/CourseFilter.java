package model;

/**
 * Strategy object for checking if a department or course matches the 
 * configuration of this filter.
 */
public class CourseFilter {
	private String department;
	private boolean includeUndergrad;
	private boolean includeGrad;
	
	public CourseFilter(String department, boolean includeUGrad, boolean includeGrad) {
		this.department = department;
		this.includeUndergrad = includeUGrad;
		this.includeGrad = includeGrad;
	}

	public boolean matchesDepartment(Department targetDepartment) {
		return department.equals(targetDepartment.getName());
	}

	public boolean matchesCourse(Course course) {
		boolean matchesAsUndergrad = includeUndergrad && course.isUndergrad();
		boolean matchesAsGrad = includeGrad && course.isGrad();
		
		return matchesAsUndergrad || matchesAsGrad;
	}
}

package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Track the currently selected course, get the offerings for selected
 * courses, and notify listeners when it changes.
 */
public class CourseSelectionManager extends ModelObservableImpl {

	private Model model;
	private Course selectedCourse;
	
	public CourseSelectionManager(Model model) {
		this.model = model;
	}
	
	public void selectCourse(Course course){
		model.selectOffering(null);
		selectedCourse = course;
		notifyListeners();
	}
	public Course getSelectedCourse() {
		return selectedCourse;
	}
	
	public List<CourseOffering> getOfferingsOfSelectedCourse(){
		List<CourseOffering> offeringList = new ArrayList<CourseOffering>();

		if (selectedCourse != null) {
			for (CourseOffering offering : selectedCourse.offerings()) {
				offeringList.add(offering);
			}
			
			Collections.sort(offeringList);
		}
		return offeringList;
	}

}

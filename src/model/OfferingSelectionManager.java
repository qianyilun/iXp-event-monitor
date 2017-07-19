package model;

import javax.swing.event.ChangeListener;

/**
 * Track the selected course offering and notify observers when it changes.
 */
public class OfferingSelectionManager extends ModelObservableImpl {
	private CourseOffering selectedOffering;
	
	public void setSelectedOffering(CourseOffering offering){
		selectedOffering = offering;
		notifyListeners();
	}
	
	public CourseOffering getSelectedSection(){
		return selectedOffering;
	}
	
	public void addListener_SelectedOffering(ChangeListener listener){
		addListener(listener);
	}
}

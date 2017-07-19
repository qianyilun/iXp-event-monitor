package model;

import javax.swing.event.ChangeListener;

/**
 * Interface for observable objects in the model.
 */
public interface ModelObserbable {

	public void addListener(ChangeListener listener);

	public void notifyListeners();

}
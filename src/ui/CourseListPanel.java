package ui;

import model.Course;
import model.Model;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.List;


/**
 * Display the list of filtered courses, allow selection of a course and 
 * notify model of that selection. Subscribe as a listener for department-selection change notifications.
 */
@SuppressWarnings("serial")
public class CourseListPanel extends TitledPanel {

	private static final int CELL_WIDTH = 100;
	private static final int DEFAULT_LIST_HEIGHT = 250;
	private static final int DEFAULT_LIST_WIDTH = CELL_WIDTH * 2 + 50;

	DefaultListModel<Course> listModel;

	public CourseListPanel(Model model) {
		super("Team List", model);
		setMainContents(makeScrollableCourseListView());
		registerForCallbacks();
		updateCourseList();
	}

	private JComponent makeScrollableCourseListView() {
		JComponent list = makeCourseListView();
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(DEFAULT_LIST_WIDTH, DEFAULT_LIST_HEIGHT));
		return listScroller;
	}
	private JComponent makeCourseListView() {
		// From: http://docs.oracle.com/javase/tutorial/uiswing/components/list.html
		listModel = new DefaultListModel<>();

		JList<Course> list = new JList<Course>(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setFixedCellWidth(CELL_WIDTH);
		list.getSelectionModel().addListSelectionListener(selectionListener());
		list.setVisibleRowCount(-1);
		
		return list;
	}


	private ListSelectionListener selectionListener() {
		return new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				handleListSelectionChange(e);
			}
		};
	}

	private void handleListSelectionChange(ListSelectionEvent selectionEvent) {
		// Source: http://docs.oracle.com/javase/tutorial/uiswing/events/listselectionlistener.html
		ListSelectionModel lsm = (ListSelectionModel)selectionEvent.getSource();

		// Skip when the user is click and dragging, or pressing button down.
		if (selectionEvent.getValueIsAdjusting()) {
			return;
		}

		if (lsm.isSelectionEmpty()) {
			getModel().selectCourse(null);
		} else {
			int minIndex = lsm.getMinSelectionIndex();
			int maxIndex = lsm.getMaxSelectionIndex();
			for (int i = minIndex; i <= maxIndex; i++) {
				if (lsm.isSelectedIndex(i)) {
					Course course = getModel().getFilteredCourses().get(i);
					System.out.println();
					getModel().selectCourse(course);
				}
			}
		}
	}

	private void registerForCallbacks() {
		getModel().addCourseFilterListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				updateCourseList();
			}
		});
	}

	private void updateCourseList() {
		listModel.removeAllElements();
		List<Course> courses = getModel().getFilteredCourses();
		for (Course course : courses) {
			listModel.addElement(course);
		}
	}

}

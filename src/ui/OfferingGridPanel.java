package ui;

import model.CourseOffering;
import model.Model;
import model.Semester;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


/**
 * UI to display the grid of course offerings and allow selection of one offering.
 */
@SuppressWarnings("serial")
public class OfferingGridPanel extends TitledPanel {
	private static final int COLUM_SPRING = 1;
	private static final int COLUM_SUMMER = 2;
	private static final int COLUM_FALL = 3;

	private static final double SCALE_WEIGHT = 1;
	private static final int CELL_PADDING = 10;

	private static final int PREF_WIDTH = 600;
	private static final int PREF_HEIGHT = 500;
	
	private static final int DEFAULT_FIRST_YEAR = 2000;
	private static final int DEFAULT_LAST_YEAR = 2010;

	
	JPanel grid = new JPanel();	

	public OfferingGridPanel(Model model) {
		super("Ideas Shown", model);
		setMainContents(makeMainPanel());
	}
	private JComponent makeMainPanel() {
		grid.setBackground(Color.WHITE);
		grid.setPreferredSize(new Dimension(PREF_WIDTH, PREF_HEIGHT));
		
		setupInitialMessage();
		registerForCallbacks();
		return grid;
	}
	private void setupInitialMessage() {
		grid.setLayout(new BorderLayout());
		JLabel label = new JLabel("Use a filter to select ideas.");
		label.setHorizontalAlignment(JLabel.CENTER);
		grid.add(label, BorderLayout.CENTER);
	}
	private void registerForCallbacks() {
		getModel().addSelectedCourseListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				updateGrid();
			}
		});
	}


	// Grid update when data selected.
	private void updateGrid() {
		resetGrid();
		
		addColumnHeader();
		addRows();
		
		redrawGrid();
	}
	private void resetGrid() {
		grid.removeAll();
		grid.setLayout(new GridBagLayout());
	}
	private void redrawGrid() {
		grid.updateUI();
	}

	// Column Header
	private void addColumnHeader() {
		grid.add(new JLabel("Jan-Apr"), columnHeaderConstraints(COLUM_SPRING));
		grid.add(new JLabel("May-Aug"), columnHeaderConstraints(COLUM_SUMMER));
		grid.add(new JLabel("Sep-Dec"),   columnHeaderConstraints(COLUM_FALL));
	}
	private GridBagConstraints columnHeaderConstraints(int colNumber) {
		GridBagConstraints c = new GridBagConstraints();
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		c.ipadx = CELL_PADDING;
		c.ipady = CELL_PADDING;
		c.weightx = 1;
		
		c.gridx = colNumber;
		return c;
	}
	
	// Setup rows for each year
	private void addRows() {
		List<CourseOffering> offeringList = getModel().getOfferingsOfSelectedCourse();
		int firstYear = getFirstYear(offeringList);
		int lastYear = getLastYear(offeringList);
// ----------------------------------------------------------------
		for (int year = firstYear; year <= lastYear; year++) {
			int row = year - firstYear + 1;
			
			addRowHeading(year, row);
			addRowContents(offeringList, year, row);
		}
	}

	// Year Heading
	private void addRowHeading(int year, int row) {
		GridBagConstraints c = rowHeadingConstraints(row);
		grid.add(new JLabel(Integer.toString(year)), c);
	}
	private GridBagConstraints rowHeadingConstraints(int row) {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = row;
		
		c.ipadx = CELL_PADDING;
		c.ipady = CELL_PADDING;
		
		c.anchor = GridBagConstraints.NORTHEAST;
		return c;
	}
	
	// Year's contents
	private void addRowContents(List<CourseOffering> offeringList, int year, int row) {
		Semester semester = new Semester(Semester.SEMESTER_NUMBER_SPRING, year);
		int column = COLUM_SPRING;
		while(semester.getYear() == year) {
			addGridContentsForSemester(offeringList, semester, row, column);
			
			semester = semester.getNextSemester();
			column++;
		}
	}
	private void addGridContentsForSemester(List<CourseOffering> offerings, Semester semester, int row, int column) {
		JPanel semesterPanel = makeSemesterPanel(offerings, semester);
		GridBagConstraints constraints = semesterConstraints(row, column);
		grid.add(semesterPanel, constraints);
	}
	private JPanel makeSemesterPanel(List<CourseOffering> offeringList, Semester semester) {
		JPanel semesterPanel = new JPanel();
		semesterPanel.setPreferredSize(new Dimension(1, 1));
		semesterPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));				
		semesterPanel.setLayout(new BoxLayout(semesterPanel, BoxLayout.PAGE_AXIS));
		
		for (CourseOffering offering : offeringList) {
			if (offering.getSemester().equals(semester)) {
				makeOffeingButton(semesterPanel, offering);						
			}
		}
		return semesterPanel;
	}
	private void makeOffeingButton(JPanel semesterPanel, CourseOffering offering) {
//		String message = offering.getCourse().toString() + " - " + offering.getLocation();

		String message = offering.getTitle();
		String description = offering.getDescription();
		JButton button = new JButton(message);


		allowOnlyHorizontalStretching(button);
		button.addActionListener(offeringButtonListener(offering, description));
		semesterPanel.add(button);
	}
	private ActionListener offeringButtonListener(final CourseOffering offering, String description) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			    getModel().selectOffering(offering);
//			    DetailedRequest.popWindow(description);

//			    DetailedRequest dr = new DetailedRequest();
//			    dr.popWindow(description);
                JFrame d = new D(description);
                d.setVisible(true);

            }
		};
	}

	private GridBagConstraints semesterConstraints(int row, int column) {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = column;
		c.gridy = row;

		c.ipadx = CELL_PADDING;
		c.ipady = CELL_PADDING;
		
		c.anchor = GridBagConstraints.NORTHWEST;
		c.fill = GridBagConstraints.BOTH;
		
		c.weightx = SCALE_WEIGHT;
		c.weighty = SCALE_WEIGHT;
		return c;
	}

	
	// Utility functions
	private int getFirstYear(List<CourseOffering> offeringList) {
		int firstYear = DEFAULT_FIRST_YEAR;
		if (offeringList.size() > 0) {
			firstYear = offeringList.get(0).getSemester().getYear();
		}
		return firstYear;
	}
	private int getLastYear(List<CourseOffering> offeringList) {
		int lastYear = DEFAULT_LAST_YEAR;
		if (offeringList.size() > 0) {
			lastYear = offeringList.get(offeringList.size()-1).getSemester().getYear();
		}
		return lastYear;
	}
}
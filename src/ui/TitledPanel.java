package ui;

import model.Model;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;


/**
 * Abstract base class for the titled panels in the UI.
 * Supports drawing a header (title), and a main body with a border.
 */
@SuppressWarnings("serial")
public abstract class TitledPanel extends JPanel{
	private static final int EMPTY_BORDER_WIDTH = 3;
	
	private Model model;
	JPanel mainContentPanel;

	public TitledPanel(String title, Model model) {
		this.model = model;
		setLayout(new BorderLayout());
		add(makeTitleLabel(title), BorderLayout.NORTH);
		add(makeMainPanelHolder(), BorderLayout.CENTER);
		setEmptyBorder();
		
		// NOTE: Should not call any overridden functions from the base class because
		// the derived class may not have initialized yet.
	}

	// Make final so safe to call from constructor
	final private Component makeTitleLabel(String title) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		JLabel label = new JLabel(title);
		label.setForeground(Color.BLUE);
		panel.add(label);
		panel.add(Box.createHorizontalGlue());
		return panel;
	}

	// Make final so safe to call from constructor
	final private Component makeMainPanelHolder() {
		mainContentPanel = new JPanel(new BorderLayout());
		mainContentPanel.setBackground(Color.WHITE);
		mainContentPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.gray));
		return mainContentPanel;
	}
	
	// Make final so safe to call from constructor.
	final private void setEmptyBorder() {
		setBorder(BorderFactory.createEmptyBorder(
				EMPTY_BORDER_WIDTH, 
				EMPTY_BORDER_WIDTH, 
				EMPTY_BORDER_WIDTH, 
				EMPTY_BORDER_WIDTH));
	}


	/*
	 * Protected Interface for derived classes to access:
	 */
	final protected void setMainContents(JComponent mainComponent) {
		// NOTE: Swap the comments on the following lines to create UI skeleton for screenshot.
		mainContentPanel.add(mainComponent, BorderLayout.CENTER);
		//mainContentPanel.add(Box.createRigidArea(new Dimension(250, 150)));
	}
	
	protected Model getModel() {
		return model;
	}

	protected void allowOnlyHorizontalStretching(JComponent component) {
		Dimension prefSize = component.getPreferredSize();
		Dimension newSize = new Dimension(Integer.MAX_VALUE, (int)prefSize.getHeight());
		component.setMaximumSize(newSize);
	}
}

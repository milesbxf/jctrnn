package me.mb.jctrnn.ui;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.CardLayout;
import java.awt.GridBagLayout;

public class CTRNNEditorPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public CTRNNEditorPanel() {
		setLayout(new CardLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane, "name_32835278716710");
		
		Metadata metadata = new Metadata();
		tabbedPane.addTab("Metadata", null, metadata, null);
		
		JPanel pnlLayout = new JPanel();
		tabbedPane.addTab("Layout", null, pnlLayout, null);
		pnlLayout.setLayout(new CardLayout(0, 0));
		
		LayoutPanel layoutPanel = new LayoutPanel();
		pnlLayout.add(layoutPanel, "name_4508481943293");
		
		JPanel pnlRanges = new JPanel();
		tabbedPane.addTab("Parameter ranges", null, pnlRanges, null);
		pnlRanges.setLayout(new CardLayout(0, 0));
		
		ParameterRangesPanel parameterRangesPanel = new ParameterRangesPanel();
		pnlRanges.add(parameterRangesPanel, "name_1968254327185");

	}

}

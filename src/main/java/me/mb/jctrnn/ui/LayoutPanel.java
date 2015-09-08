package me.mb.jctrnn.ui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.CardLayout;

public class LayoutPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public LayoutPanel() {
		setLayout(new CardLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "name_6542409506646");
		
		NetworkLayoutPane networkLayoutPane = new NetworkLayoutPane();
		scrollPane.setViewportView(networkLayoutPane);
		
		JPanel panel = new JPanel();
		add(panel, "name_6542416340595");

	}

}

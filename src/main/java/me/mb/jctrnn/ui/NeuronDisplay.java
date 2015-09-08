package me.mb.jctrnn.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import me.mb.jctrnn.json.Network.Layer.Neuron;

public class NeuronDisplay extends JPanel {

	public static final Dimension SIZE = new Dimension(50,50);
	
	/**
	 * Create the panel.
	 */
	public NeuronDisplay(Neuron neuron) {
		setBorder(BorderFactory.createLineBorder(Color.BLUE));
		setPreferredSize(SIZE);
		setMinimumSize(SIZE);
		setMaximumSize(SIZE);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.RED);
		g2.fillOval(5, 5, SIZE.width-10, SIZE.height-10);
	}
	
}

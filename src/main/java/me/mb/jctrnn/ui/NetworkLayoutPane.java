package me.mb.jctrnn.ui;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import me.mb.jctrnn.json.JSONTestUtils;
import me.mb.jctrnn.json.Network;
import me.mb.jctrnn.json.Network.Layer;
import me.mb.jctrnn.json.Network.Layer.Neuron;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.util.ArrayList;
import java.util.List;

public class NetworkLayoutPane extends JPanel {

	/**
	 * Create the panel.
	 */
	public NetworkLayoutPane() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		loadNetwork(JSONTestUtils.createNetwork());
		
		
	}
	
	private final List<LayerDisplay> layers = new ArrayList<>();
	
	public void loadNetwork(Network network) {
		this.removeAll();
		layers.clear();
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0; gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		for(Layer netLayer : network.layers) {
			LayerDisplay layerDisplay = new LayerDisplay();
			layerDisplay.loadLayer(netLayer);
			layers.add(layerDisplay);
			add(layerDisplay,gbc);
			gbc.gridy++;
		}
	}

	public static class LayerDisplay extends JPanel {
		public LayerDisplay() {
			
			setBorder(BorderFactory.createLineBorder(Color.BLACK));
			
			GridBagLayout gridBagLayout = new GridBagLayout();
			gridBagLayout.columnWidths = new int[]{0, 0};
			gridBagLayout.rowHeights = new int[]{0, 0, 0};
			gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
			gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
			setLayout(gridBagLayout);
			
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.fill = GridBagConstraints.NONE;
			gbc.anchor = GridBagConstraints.CENTER;
			gbc.gridx = 0; gbc.gridy = 0;	gbc.weightx = 1;
		}
		
		private final List<NeuronDisplay> neurons = new ArrayList<>();
		
		public void loadLayer(Layer layer) {
			this.removeAll();
			neurons.clear();
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1; gbc.weighty = 0;
			gbc.anchor = GridBagConstraints.CENTER;
			gbc.fill = GridBagConstraints.NONE;
			
			for(Neuron neuron: layer.neurons) {
				NeuronDisplay neuronDisplay = new NeuronDisplay(neuron);
				neurons.add(neuronDisplay);
				add(neuronDisplay,gbc);
				gbc.gridx++;
			}
			
			
		}
		
		public List<NeuronDisplay> getNeurons() {
			return neurons;
		}
		
	}
	
	
	public static class Connection {
		private NeuronDisplay neuron1,neuron2;

		public Connection(NeuronDisplay neuron1, NeuronDisplay neuron2) {
			super();
			this.neuron1 = neuron1;
			this.neuron2 = neuron2;
		}
		
		public void paint(Graphics2D g2) {
			g2.setColor(Color.BLACK);
			g2.drawLine(neuron1.getLocation().x, neuron1.getLocation().y, neuron2.getLocation().x, neuron2.getLocation().y);
		}
		
	}
}

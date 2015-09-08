package me.mb.jctrnn.ui;

import javax.swing.AbstractSpinnerModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.JSpinner;

import java.awt.Insets;

import javax.swing.AbstractListModel;

import me.mb.jctrnn.json.Network;
import me.mb.jctrnn.json.Network.ParamRange;
import me.mb.jctrnn.json.Network.ParamRange.Range;

import javax.swing.border.BevelBorder;
import javax.swing.event.ListDataListener;
import javax.swing.JButton;
import javax.swing.BoxLayout;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class ParameterRangesPanel extends JPanel {

	private RangeListModel listModel = new RangeListModel();
	JButton btnDelete;JButton btnRename;AllRangePanels allRangePanels;
	
	public void addRange(ParamRange range) {
		listModel.addParamRange(range);
	}
	
	/**
	 * Create the panel.
	 */
	public ParameterRangesPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 450, 0};
		gridBagLayout.rowHeights = new int[] {120, 180};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0};
		setLayout(gridBagLayout);
		
		
		
		final JList<String> list = new JList<>();
		list.addListSelectionListener(new ListSelectionListener() {
			private int previousSelection = -1;
			
			public void valueChanged(ListSelectionEvent lse) {				
				boolean selected = list.getSelectedIndex() != -1;
				
				btnDelete.setEnabled(selected);
				btnRename.setEnabled(selected);
				
				if(list.getSelectedIndex() != -1) {
					allRangePanels.loadRange(listModel.getParamRangeAt(list.getSelectedIndex()));					
				}		
			}
		});
		list.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		list.setModel(listModel);
		
		
		ParamRange test1 = new ParamRange(),test2 = new ParamRange();
		
		test1.name = "Testing1";test2.name = "Testing2";
		listModel.addParamRange(test1);listModel.addParamRange(test2);
		
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 5, 0);
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weighty = 0.75;
		gbc.weightx = 0.95;
		add(list, gbc);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.NORTH;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.weightx = 0.05;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{43, 0};
		gbl_panel.rowHeights = new int[] {38, 38, 38};
		gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0};
		panel.setLayout(gbl_panel);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String newName = JOptionPane.showInputDialog("Enter the name of the new range:");
				if(newName != null) {
					ParamRange newRange = new ParamRange();
					newRange.name = newName;
					listModel.addParamRange(newRange);
					list.setSelectedIndex(listModel.getSize()-1);
				}
			}
		});
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.anchor = GridBagConstraints.NORTH;
		gbc_btnAdd.weightx = 1.0;
		gbc_btnAdd.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAdd.insets = new Insets(0, 0, 5, 0);
		gbc_btnAdd.gridx = 0;
		gbc_btnAdd.gridy = 0;
		panel.add(btnAdd, gbc_btnAdd);		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int result = JOptionPane.showConfirmDialog(btnDelete, "Delete " + list.getSelectedValue() + "?","Delete Range",JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.OK_OPTION) {
					listModel.removeParamRangeAt(list.getSelectedIndex());
				}
			}
		});
		btnDelete.setEnabled(false);
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.anchor = GridBagConstraints.NORTH;
		gbc_btnDelete.weightx = 1.0;
		gbc_btnDelete.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDelete.insets = new Insets(0, 0, 5, 0);
		gbc_btnDelete.gridx = 0;
		gbc_btnDelete.gridy = 1;
		panel.add(btnDelete, gbc_btnDelete);
		
		btnRename = new JButton("Rename");
		btnRename.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newName = JOptionPane.showInputDialog("Enter the new name of " + list.getSelectedValue() +":",list.getSelectedValue());
				if(newName != null) {
					listModel.renameAt(list.getSelectedIndex(),newName);
				}
			}
		});
		btnRename.setEnabled(false);
		GridBagConstraints gbc_btnRename = new GridBagConstraints();
		gbc_btnRename.anchor = GridBagConstraints.NORTH;
		gbc_btnRename.weightx = 1.0;
		gbc_btnRename.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRename.gridx = 0;
		gbc_btnRename.gridy = 2;
		panel.add(btnRename, gbc_btnRename);
		
		allRangePanels = new AllRangePanels();
		allRangePanels.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_allRangePanels = new GridBagConstraints();
		gbc_allRangePanels.gridwidth = 2;
		gbc_allRangePanels.insets = new Insets(0, 0, 5, 0);
		gbc_allRangePanels.anchor = GridBagConstraints.SOUTH;
		gbc_allRangePanels.weighty = 0.25;
		gbc_allRangePanels.weightx = 1.0;
		gbc_allRangePanels.fill = GridBagConstraints.BOTH;
		gbc_allRangePanels.gridx = 0;
		gbc_allRangePanels.gridy = 1;
		add(allRangePanels, gbc_allRangePanels);

	}

	public static class RangeListModel extends AbstractListModel<String> {

		private List<ParamRange> elements = new ArrayList<>();
		
		public void addParamRange(ParamRange range) {
			fireContentsChanged(this, elements.size(), elements.size());
			elements.add(range);
		}
		
		public void removeParamRangeAt(int index) {
			elements.remove(index);
			fireContentsChanged(this, index, index);
		}
		
		public ParamRange getParamRangeAt(int index) {
			return elements.get(index);
		}
		
		public void renameAt(int index, String newName) {
			elements.get(index).name = newName;
			fireContentsChanged(this, index, index);
		}
		
		public void setRangeAt(int index,ParamRange range) {
			elements.set(index, range);
			fireContentsChanged(this, index, index);
		}
		
		@Override
		public String getElementAt(int index) {
			// TODO Auto-generated method stub
			return elements.get(index).name;
		}

		@Override
		public int getSize() {
			// TODO Auto-generated method stub
			return elements.size();
		}
		
	}
	
	public static class AllRangePanels extends JPanel {

		
		
		private JLabel lbllow,lblHigh;
		private RangeComponents pnlTau,pnlBias,pnlGain,pnlWeight;
		private String rangeName = "";
		
		public Network.ParamRange getValue() {
			Network.ParamRange range = new Network.ParamRange();
			range.tauRange = pnlTau.getValue();
			range.biasRange = pnlBias.getValue();
			range.gainRange = pnlGain.getValue();
			range.weightRange = pnlWeight.getValue();
			range.name = rangeName;
			return range;
		}
		public void setRangeName(String rangeName) {
			this.rangeName = rangeName;
		}
		
		public void loadRange(ParamRange range) {
			if(range.tauRange == null)
				range.tauRange = new Range();
			pnlTau.load(range.tauRange);
			if(range.biasRange == null)
				range.biasRange = new Range();
			pnlBias.load(range.biasRange);
			if(range.gainRange == null)
				range.gainRange = new Range();
			pnlGain.load(range.gainRange);
			if(range.weightRange == null)
				range.weightRange = new Range();
			pnlWeight.load(range.weightRange);
			rangeName = range.name;
		}
		
		public AllRangePanels() {
			GridBagLayout gridBagLayout = new GridBagLayout();
			setLayout(gridBagLayout);
			
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.fill = GridBagConstraints.BOTH;
			gbc.insets = new Insets(0, 0, 5, 0);
			gbc.gridx = 1;
			gbc.gridy = 0;
			gbc.weightx = 0.33;
			gbc.weighty = 0.25;
						
			lbllow = new JLabel("low");
			add(lbllow,gbc);
			
			gbc.gridx = 2;
			lblHigh = new JLabel("high");
			add(lblHigh,gbc);

			gbc.fill = GridBagConstraints.NONE;
			gbc.anchor = GridBagConstraints.WEST;
			gbc.gridy = 1;
			gbc.gridx = 0;
			gbc.ipadx = 50;
			gbc.ipady = 10;
			pnlTau = new RangeComponents();
			pnlTau.setDescription("Tau");
			add(pnlTau.getLblDesc(),gbc);
			gbc.gridx = 1;
			add(pnlTau.getSpnLow(),gbc);
			gbc.gridx = 2;
			add(pnlTau.getSpnHigh(),gbc);
			
			gbc.gridy = 2;
			gbc.gridx = 0;
			pnlBias = new RangeComponents();
			pnlBias.setDescription("Bias");
			add(pnlBias.getLblDesc(),gbc);
			gbc.gridx = 1;
			add(pnlBias.getSpnLow(),gbc);
			gbc.gridx = 2;
			add(pnlBias.getSpnHigh(),gbc);

			gbc.gridy = 3;
			gbc.gridx = 0;
			pnlGain = new RangeComponents();
			pnlGain.setDescription("Gain");
			add(pnlGain.getLblDesc(),gbc);
			gbc.gridx = 1;
			add(pnlGain.getSpnLow(),gbc);
			gbc.gridx = 2;
			add(pnlGain.getSpnHigh(),gbc);
			
			gbc.gridy = 4;
			gbc.gridx = 0;
			pnlWeight = new RangeComponents();
			pnlWeight.setDescription("Weight");
			add(pnlWeight.getLblDesc(),gbc);
			gbc.gridx = 1;
			add(pnlWeight.getSpnLow(),gbc);
			gbc.gridx = 2;
			add(pnlWeight.getSpnHigh(),gbc);
			
		}
	}
	
	public static class RangeComponents {
		
		private JLabel lblDesc;
		private JSpinner spnLow,spnHigh;
		private Network.ParamRange.Range range;
		
		public RangeComponents() {
			SpinnerNumberModel model = new SpinnerNumberModel();
//			model.setStepSize(0.5f);
			this.range = new Network.ParamRange.Range();
			
			lblDesc = new JLabel();
			spnLow = new JSpinner(new SpinnerNumberModel(0f,-1e5f,1e5f,0.5f));
			spnHigh = new JSpinner(new SpinnerNumberModel(0f,-1e5f,1e5f,0.5f));
			spnLow.addChangeListener(new ChangeListener() {				
				@Override
				public void stateChanged(ChangeEvent e) {
					updateValues();					
				}
			});
			spnHigh.addChangeListener(new ChangeListener() {				
				@Override
				public void stateChanged(ChangeEvent e) {
					updateValues();					
				}
			});

		}
		
		public void load(Network.ParamRange.Range range) {
			this.range = range==null ? new Network.ParamRange.Range() : range;
			float low = range==null ? 0 : range.low;
			float high = range==null ? 0 : range.high;
			
			spnLow.setValue(low);
			spnHigh.setValue(high);
		}
		
		public JLabel getLblDesc() {
			return lblDesc;
		}
		
		public JSpinner getSpnHigh() {
			return spnHigh;
		}
		public JSpinner getSpnLow() {
			return spnLow;
		}
		
		public String getDescription() {
			return lblDesc.getText();
		}
		
		public void setDescription(String description) {
			lblDesc.setText(description);
		}
		
		private void updateValues() {			
			Number low = (Number) (spnLow.getValue()),
					high = (Number) (spnHigh.getValue());
			
			range.low = low.floatValue();
			range.high = high.floatValue();			
		}
		
		public Network.ParamRange.Range getValue() {
			return range;
		}
		
	}
}

package gui;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.SpringLayout;
import javax.swing.JProgressBar;

public class StatusPanel extends JPanel {
	public StatusPanel() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		setPreferredSize(new Dimension(731, 20));
		
		setup();
	}
	
	public void setup() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		JLabel layerLabel = new JLabel("Layer: ");
		springLayout.putConstraint(SpringLayout.NORTH, layerLabel, 0, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, layerLabel, 0, SpringLayout.WEST, this);
		layerLabel.setHorizontalAlignment(SwingConstants.LEFT);
		add(layerLabel);
		
		JProgressBar progressBar = new JProgressBar();
		springLayout.putConstraint(SpringLayout.NORTH, progressBar, 0, SpringLayout.NORTH, layerLabel);
		springLayout.putConstraint(SpringLayout.EAST, progressBar, 0, SpringLayout.EAST, this);
		add(progressBar);
		
		JLabel lblSize = new JLabel("Dimensions: ");
		springLayout.putConstraint(SpringLayout.NORTH, lblSize, 0, SpringLayout.NORTH, layerLabel);
		add(lblSize);
		
		JLabel lblView = new JLabel("View: ");
		springLayout.putConstraint(SpringLayout.WEST, lblSize, 100, SpringLayout.WEST, lblView);
		springLayout.putConstraint(SpringLayout.WEST, lblView, 80, SpringLayout.WEST, layerLabel);
		springLayout.putConstraint(SpringLayout.NORTH, lblView, 0, SpringLayout.NORTH, layerLabel);
		add(lblView);
	}
}
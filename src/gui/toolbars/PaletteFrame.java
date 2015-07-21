package gui.toolbars;

import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;

public class PaletteFrame extends JInternalFrame {
	public PaletteFrame() {
		setTitle("Palette");
		setSize(116, 401);
		setVisible(true);
		setIconifiable(true);

		JToolBar toolBar = new JToolBar();
		toolBar.setBackground(Color.DARK_GRAY);
		toolBar.setFloatable(false);
		toolBar.setBorder(null);
		toolBar.setOrientation(SwingConstants.VERTICAL);
		getContentPane().add(toolBar);
		
		toolBar.addSeparator(new Dimension(16, 5));

		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(PaletteFrame.class.getResource("/palette/Up.png")));
		btnNewButton.setOpaque(false);
		btnNewButton.setBorder(null);
		toolBar.add(btnNewButton);

		toolBar.addSeparator(new Dimension(16, 5));

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(new ImageIcon(PaletteFrame.class.getResource("/palette/Down.png")));
		btnNewButton_1.setOpaque(false);
		btnNewButton_1.setBorder(null);
		toolBar.add(btnNewButton_1);

		toolBar.addSeparator(new Dimension(16, 26));

		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setIcon(new ImageIcon(PaletteFrame.class.getResource("/palette/ZoomIn.png")));
		btnNewButton_2.setOpaque(false);
		btnNewButton_2.setBorder(null);
		toolBar.add(btnNewButton_2);

		toolBar.addSeparator(new Dimension(16, 5));

		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setIcon(new ImageIcon(PaletteFrame.class.getResource("/palette/ZoomOut.png")));
		btnNewButton_3.setOpaque(false);
		btnNewButton_3.setBorder(null);
		toolBar.add(btnNewButton_3);

		toolBar.addSeparator(new Dimension(16, 26));

		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.setIcon(new ImageIcon(PaletteFrame.class.getResource("/palette/Front.png")));
		btnNewButton_4.setOpaque(false);
		btnNewButton_4.setBorder(null);
		toolBar.add(btnNewButton_4);

		toolBar.addSeparator(new Dimension(16, 5));

		JButton btnNewButton_5 = new JButton("");
		btnNewButton_5.setIcon(new ImageIcon(PaletteFrame.class.getResource("/palette/Right.png")));
		btnNewButton_5.setOpaque(false);
		btnNewButton_5.setBorder(null);
		toolBar.add(btnNewButton_5);

		toolBar.addSeparator(new Dimension(16, 5));

		JButton btnNewButton_6 = new JButton("");
		btnNewButton_6.setIcon(new ImageIcon(PaletteFrame.class.getResource("/palette/Top.png")));
		btnNewButton_6.setOpaque(false);
		btnNewButton_6.setBorder(null);
		toolBar.add(btnNewButton_6);

		toolBar.addSeparator(new Dimension(16, 26));

		JButton btnNewButton_7 = new JButton("");
		btnNewButton_7.setIcon(new ImageIcon(PaletteFrame.class.getResource("/palette/Brush.png")));
		btnNewButton_7.setOpaque(false);
		btnNewButton_7.setBorder(null);
		toolBar.add(btnNewButton_7);

		toolBar.addSeparator(new Dimension(16, 5));

		JButton btnNewButton_8 = new JButton("");
		btnNewButton_8.setIcon(new ImageIcon(PaletteFrame.class.getResource("/palette/Eraser.png")));
		btnNewButton_8.setOpaque(false);
		btnNewButton_8.setBorder(null);
		toolBar.add(btnNewButton_8);

		toolBar.addSeparator(new Dimension(16, 5));

		JButton btnNewButton_9 = new JButton("");
		btnNewButton_9.setIcon(new ImageIcon(PaletteFrame.class.getResource("/palette/Fill.png")));
		btnNewButton_9.setOpaque(false);
		btnNewButton_9.setBorder(null);
		toolBar.add(btnNewButton_9);

		toolBar.addSeparator(new Dimension(16, 26));

		JButton btnNewButton_10 = new JButton("");
		btnNewButton_10.setIcon(new ImageIcon(PaletteFrame.class.getResource("/palette/Mirror_x.png")));
		btnNewButton_10.setOpaque(false);
		btnNewButton_10.setBorder(null);
		toolBar.add(btnNewButton_10);

		toolBar.addSeparator(new Dimension(16, 5));

		JButton btnNewButton_11 = new JButton("");
		btnNewButton_11.setIcon(new ImageIcon(PaletteFrame.class.getResource("/palette/Mirror_y.png")));
		btnNewButton_11.setOpaque(false);
		btnNewButton_11.setBorder(null);
		toolBar.add(btnNewButton_11);

		toolBar.addSeparator(new Dimension(16, 5));

		JButton btnNewButton_12 = new JButton("");
		btnNewButton_12.setIcon(new ImageIcon(PaletteFrame.class.getResource("/palette/Mirror_z.png")));
		btnNewButton_12.setOpaque(false);
		btnNewButton_12.setBorder(null);
		toolBar.add(btnNewButton_12);

		toolBar.addSeparator(new Dimension(16, 26));

		JButton btnNewButton_13 = new JButton("");
		btnNewButton_13.setIcon(new ImageIcon(PaletteFrame.class.getResource("/palette/Copy.png")));
		btnNewButton_13.setOpaque(false);
		btnNewButton_13.setBorder(null);
		toolBar.add(btnNewButton_13);

		toolBar.addSeparator(new Dimension(16, 5));

		JButton btnNewButton_14 = new JButton("");
		btnNewButton_14.setIcon(new ImageIcon(PaletteFrame.class.getResource("/palette/Paste.png")));
		btnNewButton_14.setOpaque(false);
		btnNewButton_14.setBorder(null);
		toolBar.add(btnNewButton_14);
		
		toolBar.addSeparator(new Dimension(16, 5));

		pack();
	}

}

package gui.toolbars;

import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.SwingConstants;

import gui.Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ToolsFrame extends JInternalFrame {
	JButton btn_up, btn_down, btn_in, btn_out, btn_front, btn_right, btn_top, btn_brush, btn_erase, btn_fill, btn_mx, btn_my, btn_mz, btn_copy, btn_paste;
	
	public ToolsFrame() {
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

		btn_up = new JButton();
		btn_up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Main.changeLayer(0);
			}
		});
		btn_up.setIcon(new ImageIcon(getClass().getResource("/tools/Up.png")));
		btn_up.setOpaque(false);
		btn_up.setBorder(null);
		toolBar.add(btn_up);

		toolBar.addSeparator(new Dimension(16, 5));

		btn_down = new JButton();
		btn_down.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Main.changeLayer(1);
			}
		});
		btn_down.setIcon(new ImageIcon(getClass().getResource("/tools/Down.png")));
		btn_down.setOpaque(false);
		btn_down.setBorder(null);
		toolBar.add(btn_down);

		toolBar.addSeparator(new Dimension(16, 26));

		btn_in = new JButton();
		btn_in.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Main.scaleGrid(0);
			}
		});
		btn_in.setIcon(new ImageIcon(getClass().getResource("/tools/ZoomIn.png")));
		btn_in.setOpaque(false);
		btn_in.setBorder(null);
		toolBar.add(btn_in);

		toolBar.addSeparator(new Dimension(16, 5));

		btn_out = new JButton();
		btn_out.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Main.scaleGrid(1);
			}
		});
		btn_out.setIcon(new ImageIcon(getClass().getResource("/tools/ZoomOut.png")));
		btn_out.setOpaque(false);
		btn_out.setBorder(null);
		toolBar.add(btn_out);

		toolBar.addSeparator(new Dimension(16, 26));

		btn_front = new JButton();
		btn_front.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setView(0);
			}
		});
		btn_front.setIcon(new ImageIcon(getClass().getResource("/tools/Front.png")));
		btn_front.setOpaque(false);
		btn_front.setBorder(null);
		toolBar.add(btn_front);

		toolBar.addSeparator(new Dimension(16, 5));

		btn_right = new JButton();
		btn_right.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setView(1);
			}
		});
		btn_right.setIcon(new ImageIcon(getClass().getResource("/tools/Right.png")));
		btn_right.setOpaque(false);
		btn_right.setBorder(null);
		toolBar.add(btn_right);

		toolBar.addSeparator(new Dimension(16, 5));

		btn_top = new JButton();
		btn_top.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setView(2);
			}
		});
		btn_top.setIcon(new ImageIcon(getClass().getResource("/tools/Top.png")));
		btn_top.setOpaque(false);
		btn_top.setBorder(null);
		toolBar.add(btn_top);

		toolBar.addSeparator(new Dimension(16, 26));

		btn_brush = new JButton();
		btn_brush.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setTool(0);
			}
		});
		btn_brush.setIcon(new ImageIcon(getClass().getResource("/tools/Brush2.png")));
		btn_brush.setOpaque(false);
		btn_brush.setBorder(null);
		toolBar.add(btn_brush);

		toolBar.addSeparator(new Dimension(16, 5));

		btn_erase = new JButton();
		btn_erase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setTool(1);
			}
		});
		btn_erase.setIcon(new ImageIcon(getClass().getResource("/tools/Eraser.png")));
		btn_erase.setOpaque(false);
		btn_erase.setBorder(null);
		toolBar.add(btn_erase);

		toolBar.addSeparator(new Dimension(16, 5));

		btn_fill = new JButton();
		btn_fill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setTool(2);
			}
		});
		btn_fill.setIcon(new ImageIcon(getClass().getResource("/tools/Fill.png")));
		btn_fill.setOpaque(false);
		btn_fill.setBorder(null);
		toolBar.add(btn_fill);

		toolBar.addSeparator(new Dimension(16, 26));

		btn_mx = new JButton();
		btn_mx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!Main.getModel().mirrx) {
					btn_mx.setIcon(new ImageIcon(getClass().getResource("/tools/Mirror_x2.png")));
					Main.getModel().mirrx = true;
				} else {
					btn_mx.setIcon(new ImageIcon(getClass().getResource("/tools/Mirror_x.png")));
					Main.getModel().mirrx = false;
				}
			}
		});
		btn_mx.setIcon(new ImageIcon(getClass().getResource("/tools/Mirror_x.png")));
		btn_mx.setOpaque(false);
		btn_mx.setBorder(null);
		toolBar.add(btn_mx);

		toolBar.addSeparator(new Dimension(16, 5));

		btn_my = new JButton();
		btn_my.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!Main.getModel().mirry) {
					btn_my.setIcon(new ImageIcon(getClass().getResource("/tools/Mirror_y2.png")));
					Main.getModel().mirry = true;
				} else {
					btn_my.setIcon(new ImageIcon(getClass().getResource("/tools/Mirror_y.png")));
					Main.getModel().mirry = false;
				}
			}
		});
		btn_my.setIcon(new ImageIcon(getClass().getResource("/tools/Mirror_y.png")));
		btn_my.setOpaque(false);
		btn_my.setBorder(null);
		toolBar.add(btn_my);

		toolBar.addSeparator(new Dimension(16, 5));

		btn_mz = new JButton();
		btn_mz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!Main.getModel().mirrz) {
					btn_mz.setIcon(new ImageIcon(getClass().getResource("/tools/Mirror_z2.png")));
					Main.getModel().mirrz = true;
				} else {
					btn_mz.setIcon(new ImageIcon(getClass().getResource("/tools/Mirror_z.png")));
					Main.getModel().mirrz = false;
				}
			}
		});
		btn_mz.setIcon(new ImageIcon(getClass().getResource("/tools/Mirror_z.png")));
		btn_mz.setOpaque(false);
		btn_mz.setBorder(null);
		toolBar.add(btn_mz);

		toolBar.addSeparator(new Dimension(16, 26));

		btn_copy = new JButton();
		btn_copy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btn_copy.setIcon(new ImageIcon(getClass().getResource("/tools/Copy.png")));
		btn_copy.setOpaque(false);
		btn_copy.setBorder(null);
		toolBar.add(btn_copy);

		toolBar.addSeparator(new Dimension(16, 5));

		btn_paste = new JButton();
		btn_paste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btn_paste.setIcon(new ImageIcon(getClass().getResource("/tools/Paste.png")));
		btn_paste.setOpaque(false);
		btn_paste.setBorder(null);
		toolBar.add(btn_paste);
		
		toolBar.addSeparator(new Dimension(16, 5));

		pack();
	}
	
	private void setTool(int num) {
		Main.currentTool = num;
		if (num == 0) {
			btn_brush.setIcon(new ImageIcon(getClass().getResource("/tools/Brush2.png")));
			btn_erase.setIcon(new ImageIcon(getClass().getResource("/tools/Eraser.png")));
			btn_fill.setIcon(new ImageIcon(getClass().getResource("/tools/Fill.png")));
		}
		if (num == 1) {
			btn_erase.setIcon(new ImageIcon(getClass().getResource("/tools/Eraser2.png")));
			btn_brush.setIcon(new ImageIcon(getClass().getResource("/tools/Brush.png")));
			btn_fill.setIcon(new ImageIcon(getClass().getResource("/tools/Fill.png")));
		}
		if (num == 2) {
			btn_fill.setIcon(new ImageIcon(getClass().getResource("/tools/Fill2.png")));
			btn_erase.setIcon(new ImageIcon(getClass().getResource("/tools/Eraser.png")));
			btn_brush.setIcon(new ImageIcon(getClass().getResource("/tools/Brush.png")));
		}
	}
	
	private void setView(int num) {
		if (num == 0) {
			Main.setView(0);
			btn_front.setIcon(new ImageIcon(getClass().getResource("/tools/Front2.png")));
			btn_front.setBorder(null);
			btn_top.setIcon(new ImageIcon(getClass().getResource("/tools/Top.png")));
			btn_top.setBorder(null);
			btn_right.setIcon(new ImageIcon(getClass().getResource("/tools/Right.png")));
			btn_right.setBorder(null);
		}
		if (num == 1) {
			Main.setView(1);
			btn_front.setIcon(new ImageIcon(getClass().getResource("/tools/Front.png")));
			btn_front.setBorder(null);
			btn_top.setIcon(new ImageIcon(getClass().getResource("/tools/Top2.png")));
			btn_top.setBorder(null);
			btn_right.setIcon(new ImageIcon(getClass().getResource("/tools/Right.png")));
			btn_right.setBorder(null);
		}
		if (num == 2) {
			Main.setView(2);
			btn_front.setIcon(new ImageIcon(getClass().getResource("/tools/Front.png")));
			btn_front.setBorder(null);
			btn_top.setIcon(new ImageIcon(getClass().getResource("/tools/Top.png")));
			btn_top.setBorder(null);
			btn_right.setIcon(new ImageIcon(getClass().getResource("/tools/Right2.png")));
			btn_right.setBorder(null);
		}
	}
}

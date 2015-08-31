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
	JButton btn_up, btn_down, btn_in, btn_out, btn_front, btn_right, btn_top, btn_pen, btn_erase, btn_fill, btn_mx, btn_my, btn_mz, btn_copy, btn_paste;
	
	public ToolsFrame() {
		setTitle("Palette");
		setVisible(true);
		setIconifiable(true);

		JToolBar toolBar = new JToolBar();
		toolBar.setBackground(Color.DARK_GRAY);
		toolBar.setFloatable(false);
		toolBar.setOrientation(SwingConstants.VERTICAL);
		getContentPane().add(toolBar);
		
		toolBar.addSeparator(new Dimension(16, 5));

		btn_up = new JButton();
		btn_up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Main.changeLayer(0);
			}
		});
		btn_up.setIcon(new ImageIcon(getClass().getResource("/tools/up.png")));
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
		btn_down.setIcon(new ImageIcon(getClass().getResource("/tools/down.png")));
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
		btn_in.setIcon(new ImageIcon(getClass().getResource("/tools/in.png")));
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
		btn_out.setIcon(new ImageIcon(getClass().getResource("/tools/out.png")));
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
		btn_front.setIcon(new ImageIcon(getClass().getResource("/tools/front1.png")));
		btn_front.setOpaque(false);
		btn_front.setBorder(null);
		toolBar.add(btn_front);

		toolBar.addSeparator(new Dimension(16, 5));

		btn_right = new JButton();
		btn_right.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setView(2);
			}
		});
		btn_right.setIcon(new ImageIcon(getClass().getResource("/tools/right.png")));
		btn_right.setOpaque(false);
		btn_right.setBorder(null);
		toolBar.add(btn_right);

		toolBar.addSeparator(new Dimension(16, 5));

		btn_top = new JButton();
		btn_top.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setView(1);
			}
		});
		btn_top.setIcon(new ImageIcon(getClass().getResource("/tools/top.png")));
		btn_top.setOpaque(false);
		btn_top.setBorder(null);
		toolBar.add(btn_top);

		toolBar.addSeparator(new Dimension(16, 26));

		btn_pen = new JButton();
		btn_pen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setTool(0);
			}
		});
		btn_pen.setIcon(new ImageIcon(getClass().getResource("/tools/pen1.png")));
		btn_pen.setOpaque(false);
		btn_pen.setBorder(null);
		toolBar.add(btn_pen);

		toolBar.addSeparator(new Dimension(16, 5));

		btn_erase = new JButton();
		btn_erase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setTool(1);
			}
		});
		btn_erase.setIcon(new ImageIcon(getClass().getResource("/tools/erase.png")));
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
		btn_fill.setIcon(new ImageIcon(getClass().getResource("/tools/fill.png")));
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
		btn_copy.setIcon(new ImageIcon(getClass().getResource("/tools/copy.png")));
		btn_copy.setOpaque(false);
		btn_copy.setBorder(null);
		toolBar.add(btn_copy);

		toolBar.addSeparator(new Dimension(16, 5));

		btn_paste = new JButton();
		btn_paste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btn_paste.setIcon(new ImageIcon(getClass().getResource("/tools/paste.png")));
		btn_paste.setOpaque(false);
		btn_paste.setBorder(null);
		toolBar.add(btn_paste);
		
		toolBar.addSeparator(new Dimension(16, 5));

		pack();
	}
	
	private void setTool(int num) {
		Main.currentTool = num;
		if (num == 0) {
			btn_pen.setIcon(new ImageIcon(getClass().getResource("/tools/pen1.png")));
			btn_erase.setIcon(new ImageIcon(getClass().getResource("/tools/erase.png")));
			btn_fill.setIcon(new ImageIcon(getClass().getResource("/tools/fill.png")));
		}
		if (num == 1) {
			btn_erase.setIcon(new ImageIcon(getClass().getResource("/tools/erase1.png")));
			btn_pen.setIcon(new ImageIcon(getClass().getResource("/tools/pen.png")));
			btn_fill.setIcon(new ImageIcon(getClass().getResource("/tools/fill.png")));
		}
		if (num == 2) {
			btn_fill.setIcon(new ImageIcon(getClass().getResource("/tools/fill1.png")));
			btn_erase.setIcon(new ImageIcon(getClass().getResource("/tools/erase.png")));
			btn_pen.setIcon(new ImageIcon(getClass().getResource("/tools/pen.png")));
		}
	}
	
	private void setView(int num) {
		if (num == 0) {
			Main.setView(0);
			btn_front.setIcon(new ImageIcon(getClass().getResource("/tools/front1.png")));
			btn_front.setBorder(null);
			btn_top.setIcon(new ImageIcon(getClass().getResource("/tools/top.png")));
			btn_top.setBorder(null);
			btn_right.setIcon(new ImageIcon(getClass().getResource("/tools/right.png")));
			btn_right.setBorder(null);
		}
		if (num == 1) {
			Main.setView(1);
			btn_front.setIcon(new ImageIcon(getClass().getResource("/tools/front.png")));
			btn_front.setBorder(null);
			btn_top.setIcon(new ImageIcon(getClass().getResource("/tools/top1.png")));
			btn_top.setBorder(null);
			btn_right.setIcon(new ImageIcon(getClass().getResource("/tools/right.png")));
			btn_right.setBorder(null);
		}
		if (num == 2) {
			Main.setView(2);
			btn_front.setIcon(new ImageIcon(getClass().getResource("/tools/front.png")));
			btn_front.setBorder(null);
			btn_top.setIcon(new ImageIcon(getClass().getResource("/tools/top.png")));
			btn_top.setBorder(null);
			btn_right.setIcon(new ImageIcon(getClass().getResource("/tools/right1.png")));
			btn_right.setBorder(null);
		}
	}
}

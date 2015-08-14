package gui.toolbars;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JToolBar;

public class ToolbarFrame extends JInternalFrame {
	public ToolbarFrame() {
		setTitle("ToolBar");
		setSize(600, 600);
		setVisible(true);
		setIconifiable(true);

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setBorder(null);
		getContentPane().add(toolBar);

		JButton btn_new = new JButton();
		btn_new.setIcon(new ImageIcon(getClass().getResource("/toolbar/file.png")));
		btn_new.setBorder(null);
		btn_new.setOpaque(false);
		toolBar.add(btn_new);

		JButton btn_open = new JButton();
		btn_open.setIcon(new ImageIcon(getClass().getResource("/toolbar/open.png")));
		btn_open.setOpaque(false);
		btn_open.setBorder(null);
		toolBar.add(btn_open);

		JButton btn_save = new JButton();
		btn_save.setIcon(new ImageIcon(getClass().getResource("/toolbar/save.png")));
		btn_save.setOpaque(false);
		btn_save.setBorder(null);
		toolBar.add(btn_save);

		JButton btn_export = new JButton();
		btn_export.setIcon(new ImageIcon(getClass().getResource("/toolbar/export.png")));
		btn_export.setBorder(null);
		btn_export.setOpaque(false);
		toolBar.add(btn_export);

		JButton btn_scene = new JButton();
		btn_scene.setIcon(new ImageIcon(getClass().getResource("/toolbar/scene.png")));
		btn_scene.setOpaque(false);
		btn_scene.setBorder(null);
		toolBar.add(btn_scene);

		JButton btn_animate = new JButton();
		btn_animate.setIcon(new ImageIcon(getClass().getResource("/toolbar/animate.png")));
		btn_animate.setOpaque(false);
		btn_animate.setBorder(null);
		toolBar.add(btn_animate);

		pack();
	}
}

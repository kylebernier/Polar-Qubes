package gui.grid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import gui.Main;
import javafx.application.Platform;

public class GridPanel extends JPanel{
	int size = 8;
	int height = 64;
	int width = 64;
	
	boolean toggleGrid = true;
	
	public GridPanel() {
		addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent m) {
				paintQ(m);
				repaint();
			}

			public void mouseClicked(MouseEvent m) {}
			public void mouseEntered(MouseEvent m) {}
			public void mouseExited(MouseEvent m) {}
			public void mouseReleased(MouseEvent m) {}
		});
		
		addMouseMotionListener(new MouseMotionListener() {
			public void mouseDragged(MouseEvent m) {
				paintQ(m);
				repaint();
			}

			public void mouseMoved(MouseEvent m) {
				int x = m.getX() / size;
				int y = m.getY() / size;
				int z = 0;

				if (x < width && y < height && x >= 0 && y >= 0) {
					Color c = Main.model.getColored(x, y, z);
					if (c != null) {
						String c1 = c.toString();
						setToolTipText(c1.substring(14, c1.length() - 1) + ",a=" + c.getAlpha() + "]");
					} else
						setToolTipText("Empty");
				}
			}
		});
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (int q : Main.model.Qubes) {
			int x = (q >> 24) & 0xFF;
			int y = (q >> 16) & 0xFF;
			int z = (q >> 8) & 0xFF;
			g.setColor((Main.model.getColored(q)));
			g.fillRect(x * size, y * size, size, size);
		}

		if (toggleGrid) {
			int x = width;
			int y = height;

			g.setColor(Color.BLACK);
			for (int i = 0; i <= x * size; i += size) {
				g.drawLine(i, 0, i, y * size);
			}
			for (int i = 0; i <= y * size; i += size) {
				g.drawLine(0, i, x * size, i);
			}
		}
	}
	
	private void paintQ(MouseEvent m) {
		int x = m.getX() / size;
		int y = m.getY() / size;
		int z = 2;
		
		int x1 = transformX(x);
		int y1 = transformY(y);
		int z1 = transformZ(z);
		
		if (Main.model.getColor(x, y, z) == null && x < width && y < height && x >= 0 && y >= 0) {
			Main.model.addQube(x, y, z, Main.currentColor);
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					Main.modelFrame.addBox(x1, y1, z1, Main.currentColor);
				}
			});
		}
	}
	
	private int transformX(int x) {
		return width - x - 1;
	}
	
	private int transformY(int y) {
		return height - y - 1;
	}

	private int transformZ(int z) {
		return z;
	}
}
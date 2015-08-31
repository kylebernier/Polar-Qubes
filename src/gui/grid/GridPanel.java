package gui.grid;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import gui.Main;
import javafx.application.Platform;

public class GridPanel extends JPanel{
	int size = 10;
	int height = 64;
	int width = 64;
	int depth = 64;
	
	int layer = 0;
	
	int view = 0;
	
	boolean toggleGrid = true;
	
	public void toggleGrid() {
		toggleGrid = !toggleGrid;
	}
	
	public void setView(int n) {
		view = n;
		revalidate();
		repaint();
	}
	
	public void changeLayer(int n) {	
		if(n == 0)
			if(view == 0 && layer+1 <= depth)
				layer++;
			else
				if(view == 1 && layer+1 <= width)
					layer++;
				else
					if(view == 2 && layer+1 <= height)
						layer++;
		if(n == 1)
			if(view == 0 && layer-1 >= 0)
				layer--;
			else
				if(view == 1 && layer-1 >= 0)
					layer--;
				else
					if(view == 2 && layer-1 >= 0)
						layer--;
		repaint();
	}
	
	public void setLayer(int n) {
		layer = n;
		repaint();
	}
	
	public void increaseSize() {
		size++;
		setPreferredSize(new Dimension(size*width+1, size*height+1));
		revalidate();
		repaint();
	}
	
	public void decreaseSize() {
		size--;
		setPreferredSize(new Dimension(size*width+1, size*height+1));
		revalidate();
		repaint();
	}
	
	public GridPanel() {
		setPreferredSize(new Dimension(size*width+1, size*height+1));
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
					Color c = Main.getModel().getColor(x, y, z);
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

		for (int q : Main.getModel().Qubes) {
			int x = (q >> 24) & 0xFF;
			int y = (q >> 16) & 0xFF;
			int z = (q >> 8) & 0xFF;
			if (view == 0) {
				if (z == layer) {
					g.setColor(Main.getModel().getColor(q));
					g.fillRect(x * size, y * size, size, size);
				}
			}
			
			if (view == 1) {
				if (y == layer) {
					g.setColor(Main.getModel().getColor(q));
					g.fillRect(x * size, y * size, size, size);
				}
			}
			
			if (view == 2) {
				if (x == layer) {
					g.setColor(Main.getModel().getColor(q));
					g.fillRect(x * size, y * size, size, size);
				}
			}
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
		int z = layer;
		
		int x1 = width - x - 1 - width;
		int y1 = height - y - 1;
		int z1 = z;
		
		if (view == 1) {
			x1 = x;
			y1 = height - y - 1 - height;
			z1 = depth - z - 1;
			
			int temp = x;
			x = z;
			z = width - temp;
		}
		if (view == 2) {
			x1 = width - x - 1;
			y1 = y;
			z1 = depth - z - 1 - depth;
			
			int temp = z;
			z = y;
			y = depth - temp;
		}
		
		
		
		if (x < width && y < height && x >= 0 && y >= 0) {
			if (Main.getModel().getColor(x, y, z) == null && Main.currentTool == 0) {
				Main.getModel().addQube(x, y, z, Main.getModel().currentColor);
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						Main.getModelFrame().addBox(x1, y1, z1, Main.getModel().currentColor);
					}
				});
			}
			if (Main.getModel().getColor(x, y, z) != null && Main.currentTool == 1) {				
				int n = Main.getModel().getIndex(x, y, z);
				Main.getModel().removeQube(x, y, z);
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						Main.getModelFrame().removeCube(n);
					}
				});
			}
		}
	}
}

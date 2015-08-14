package core;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import gui.Main;
import gui.model.Face;

public class Model {
	public List<Integer> Qubes = new ArrayList<Integer>();
	public List<Integer> ColorAssign = new ArrayList<Integer>();
	public List<Integer> Colors = new ArrayList<Integer>();
	
	public Color currentColor = Color.BLACK;
	
	public int currentLayer = 0;
	
	public boolean mirrx = false, mirry = false, mirrz = false;
	
	public int height = 64, width = 64, depth = 64;
	
	public Model() {
	}

	public void addQube(int x, int y, int z, Color color) {
		int c = color.getAlpha();
		c = (c << 8) + color.getRed();
		c = (c << 8) + color.getGreen();
		c = (c << 8) + color.getBlue();

		int c1 = 0;

		if (Colors.contains(c)) {
			c1 = Colors.indexOf(c);
		} else {
			Colors.add(c);
			c1 = Colors.indexOf(c);
		}
		
		ColorAssign.add(c1);

		int qu = x;
		qu = (qu << 8) + y;
		qu = (qu << 8) + z;
		qu = (qu << 8);

		Qubes.add(qu);
	}
	
	public void removeQube(int x, int y, int z) {
		int qu = x;
		qu = (qu << 8) + y;
		qu = (qu << 8) + z;
		qu = (qu << 8);
		
		if (Qubes.contains(qu)) {
			ColorAssign.remove(Qubes.indexOf(qu));
			Qubes.remove(Qubes.indexOf(qu));	
		}
	}
	
	public Integer getColorIndex(int x, int y, int z){
		int qu = x;
		qu = (qu << 8) + y;
		qu = (qu << 8) + z;
		qu = (qu << 8);
		
		if (Qubes.contains(qu))
			return ColorAssign.get(Qubes.indexOf(qu));
		
		return null;
	}

	public Integer getColor(int x, int y, int z) {
		int qu = x;
		qu = (qu << 8) + y;
		qu = (qu << 8) + z;
		qu = (qu << 8);
		
		if (Qubes.contains(qu))
			return Colors.get(ColorAssign.get(Qubes.indexOf(qu)));
		
		return null;
	}
	
	public Integer getColor(int qu) {
		if (Qubes.contains(qu))
			return Colors.get(ColorAssign.get(Qubes.indexOf(qu)));
		
		return null;
	}
	
	public Color getColored(int x, int y, int z) {
		int qu = x;
		qu = (qu << 8) + y;
		qu = (qu << 8) + z;
		qu = (qu << 8);
		
		if (Qubes.contains(qu)) {
			int c = Colors.get(ColorAssign.get(Qubes.indexOf(qu)));
			int a = (c >> 24) & 0xFF;
			int r = (c >> 16) & 0xFF;
			int g = (c >> 8) & 0xFF;
			int b = c & 0xFF;
			return new Color(r, g, b, a);
		}
		
		return null;
	}
	
	public Color getColored(int qu) {
		if (Qubes.contains(qu)) {
			int c = Colors.get(ColorAssign.get(Qubes.indexOf(qu)));
			int a = (c >> 24) & 0xFF;
			int r = (c >> 16) & 0xFF;
			int g = (c >> 8) & 0xFF;
			int b = c & 0xFF;
			return new Color(r, g, b, a);
		}
		
		return null;
	}
	
	public Color getColour(int i) {
		int c = Colors.get(i);
		int a = (c >> 24) & 0xFF;
		int r = (c >> 16) & 0xFF;
		int g = (c >> 8) & 0xFF;
		int b = c & 0xFF;
		return new Color(r, g, b, a);
	}
	
	public int getMaxDim() {
		int n = Integer.max(height, width);
		return Integer.max(n, depth);
	}
	
	public Face[][][] get3dArray() {
		int n = getMaxDim();
		Face[][][] faces = new Face[n][n][n];
		
		for (int i = 0; i < Qubes.size(); i++) {
			Face f = new Face();
			int qu = Qubes.get(i);
			int x = (qu >> 24) & 0xFF;
			int y = (qu >> 16) & 0xFF;
			int z = (qu >> 8) & 0xFF;
			f.texture = ColorAssign.get(i);
			faces[x][y][z] = f;
		}
		return faces;
	}
}
package core;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Model {
	public List<Integer> Qubes = new ArrayList<Integer>();
	List<Integer> Colors = new ArrayList<Integer>();
	
	public Model() {
	}

	public void addQube(int x, int y, int z, Color color) {
		int c = color.getAlpha();
		c = (c << 8) + color.getRed();
		c = (c << 8) + color.getGreen();
		c = (c << 8) + color.getBlue();

		int cl = 0;

		if (Colors.contains(c)) {
			cl = Colors.indexOf(c);
		} else {
			Colors.add(c);
			cl = Colors.indexOf(c);
		}

		int qu = x;
		qu = (qu << 8) + y;
		qu = (qu << 8) + z;
		qu = (qu << 8) + cl;

		if (!Qubes.contains(qu))
			Qubes.add(qu);
	}

	public Integer getColor(int x, int y, int z) {
		for (int i = 0; i < Qubes.size(); i++) {
			int q = Qubes.get(i);
			int x1 = (q >> 24) & 0xFF;
			int y1 = (q >> 16) & 0xFF;
			int z1 = (q >> 8) & 0xFF;
			if (x == x1 && y == y1 && z == z1) {
				return Colors.get((Qubes.get(i)) & 0xFF);
			}
		}
		return null;
	}
	
	public Integer getColor(int q) {
		return Colors.get(q & 0xFF);
	}
	
	public Color getColored(int x, int y, int z) {
		for (int i = 0; i < Qubes.size(); i++) {
			int q = Qubes.get(i);
			int x1 = (q >> 24) & 0xFF;
			int y1 = (q >> 16) & 0xFF;
			int z1 = (q >> 8) & 0xFF;
			if (x == x1 && y == y1 && z == z1) {
				int c = Colors.get((Qubes.get(i)) & 0xFF);
				int a = (c >> 24) & 0xFF;
				int r = (c >> 16) & 0xFF;
				int g = (c >> 8) & 0xFF;
				int b = c & 0xFF;
				return new Color(r, g, b, a);
			}
		}
		return null;
	}
	
	public Color getColored(int q) {
		int c = Colors.get(q & 0xFF);
		int a = (c >> 24) & 0xFF;
		int r = (c >> 16) & 0xFF;
		int g = (c >> 8) & 0xFF;
		int b = c & 0xFF;
		return new Color(r, g, b, a);
	}
}
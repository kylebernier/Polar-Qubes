package core;

import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

import gui.Main;

public class Model {
	public List<Integer> Qubes = new ArrayList<Integer>();
	public List<Integer> ColorAssign = new ArrayList<Integer>();
	public List<Color> Colors = new ArrayList<Color>();
	
	public Color currentColor = Color.PURPLE;
	
	public int currentLayer = 0;
	
	public boolean mirrx = false, mirry = false, mirrz = false;
	
	public int height = 64, width = 64, depth = 64;
	
	public Model() {
	}

	public void addQube(int x, int y, int z, Color color) {
		int c = 0;
		
		char asf = 3;

		if (Colors.contains(color)) {
			c = Colors.indexOf(color);
		} else {
			Colors.add(color);
			c = Colors.indexOf(color);
		}
		
		ColorAssign.add(c);

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
	
	public int getIndex(int x, int y, int z) {
		int qu = x;
		qu = (qu << 8) + y;
		qu = (qu << 8) + z;
		qu = (qu << 8);
		
		return Qubes.indexOf(qu);
	}
	
	public Color getColor(int x, int y, int z) {
		int qu = x;
		qu = (qu << 8) + y;
		qu = (qu << 8) + z;
		qu = (qu << 8);
		
		if (Qubes.contains(qu))
			return Colors.get(ColorAssign.get(Qubes.indexOf(qu)));
		
		return null;
	}
	
	public Color getColor(int qu) {
		if (Qubes.contains(qu))
			return Colors.get(ColorAssign.get(Qubes.indexOf(qu)));
		
		return null;
	}
	
	public Color getColour(int i) {
		return Colors.get(i);
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
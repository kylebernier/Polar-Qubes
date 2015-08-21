package core;

public class Face {
	public boolean transparent;
    public int texture;
    public int side;
    
    public boolean equals(final Face face) {
    	return face.transparent == this.transparent && face.texture == this.texture;
    }
}
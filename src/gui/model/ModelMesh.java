package gui.model;

import com.sun.javafx.geom.Vec3f;

import gui.Main;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;

public class ModelMesh extends Group {
	Material material;
	
	public void addCube(int x, int y, int z) {
		float points[] = {
                x, y, z,
                x + 1, y, z,
                x + 1, y + 1, z,
                x, y + 1, z,
                x, y, z + 1,
                x + 1, y, z + 1,
                x + 1, y + 1, z + 1,
                x, y + 1, z + 1
            };

            float texture[] = {0, 0, 1, 0, 1, 1, 0, 1};
            
            int faces[] = {
                0, 0, 2, 2, 1, 1,
                2, 2, 0, 0, 3, 3,
                1, 0, 6, 2, 5, 1,
                6, 2, 1, 0, 2, 3,            
                5, 0, 7, 2, 4, 1,
                7, 2, 5, 0, 6, 3,
                4, 0, 3, 2, 0, 1,
                3, 2, 4, 0, 7, 3,            
                3, 0, 6, 2, 2, 1,
                6, 2, 3, 0, 7, 3,
                4, 0, 1, 2, 5, 1,
                1, 2, 4, 0, 0, 3,
            };

            TriangleMesh cube = new TriangleMesh();
            cube.getPoints().setAll(points);
            cube.getTexCoords().setAll(texture);
            cube.getFaces().setAll(faces);
            
            MeshView mesh = new MeshView(cube);
            mesh.setCullFace(CullFace.BACK);
    		java.awt.Color c = Main.getModel().getColored(x, y, z);
    		material = new PhongMaterial(new Color((float)c.getRed()/255, (float)c.getGreen()/255, (float)c.getBlue()/255, (float)c.getAlpha()/255));
    	    mesh.setMaterial(material);
            
            getChildren().addAll(mesh);
	}
	
	private int CHUNK_SIZE;
    private Face[][][] voxels;
	
	void greedyMesh() {	
		CHUNK_SIZE = Main.getModel().getMaxDim();
    	voxels = Main.getModel().get3dArray();
    	
    	getChildren().clear();
    	
        int i, j, k, l, w, h, u, v, n, side = 0;
        
        final int[] x = new int []{0,0,0};
        final int[] q = new int []{0,0,0};
        final int[] du = new int[]{0,0,0}; 
        final int[] dv = new int[]{0,0,0};         
        
        /*
         * We create a mask - this will contain the groups of matching voxel faces 
         * as we proceed through the chunk in 6 directions - once for each face.
         */
        final Face[] mask = new Face [CHUNK_SIZE * CHUNK_SIZE];
        
        /*
         * These are just working variables to hold two faces during comparison.
         */
        Face voxelFace, voxelFace1;

        /**
         * We start with the lesser-spotted boolean for-loop (also known as the old flippy floppy). 
         * 
         * The variable backFace will be TRUE on the first iteration and FALSE on the second - this allows 
         * us to track which direction the indices should run during creation of the quad.
         * 
         * This loop runs twice, and the inner loop 3 times - totally 6 iterations - one for each 
         * voxel face.
         */
        for (boolean backFace = true, b = false; b != backFace; backFace = backFace && b, b = !b) { 

            /*
             * We sweep over the 3 dimensions - most of what follows is well described by Mikola Lysenko 
             * in his post - and is ported from his Javascript implementation.  Where this implementation 
             * diverges, I've added commentary.
             */
            for(int d = 0; d < 3; d++) {

                u = (d + 1) % 3; 
                v = (d + 2) % 3;

                x[0] = 0;
                x[1] = 0;
                x[2] = 0;

                q[0] = 0;
                q[1] = 0;
                q[2] = 0;
                q[d] = 1;

                /*
                 * Here we're keeping track of the side that we're meshing.
                 */
                if (d == 0)      { side = backFace ? 3   : 2;  }
                else if (d == 1) { side = backFace ? 5 : 4;   }
                else if (d == 2) { side = backFace ? 0  : 1; }                

                /*
                 * We move through the dimension from front to back
                 */            
                for(x[d] = -1; x[d] < CHUNK_SIZE;) {

                    /*
                     * -------------------------------------------------------------------
                     *   We compute the mask
                     * -------------------------------------------------------------------
                     */
                    n = 0;

                    for(x[v] = 0; x[v] < CHUNK_SIZE; x[v]++) {

                        for(x[u] = 0; x[u] < CHUNK_SIZE; x[u]++) {

                            /*
                             * Here we retrieve two voxel faces for comparison.
                             */
                            voxelFace  = (x[d] >= 0 )             ? getFace(x[0], x[1], x[2], side)                      : null;
                            voxelFace1 = (x[d] < CHUNK_SIZE - 1) ? getFace(x[0] + q[0], x[1] + q[1], x[2] + q[2], side) : null;

                            /*
                             * Note that we're using the equals function in the voxel face class here, which lets the faces 
                             * be compared based on any number of attributes.
                             * 
                             * Also, we choose the face to add to the mask depending on whether we're moving through on a backface or not.
                             */
                            mask[n++] = ((voxelFace != null && voxelFace1 != null && voxelFace.equals(voxelFace1))) 
                                        ? null 
                                        : backFace ? voxelFace1 : voxelFace;
                        }
                    }

                    x[d]++;

                    /*
                     * Now we generate the mesh for the mask
                     */
                    n = 0;

                    for(j = 0; j < CHUNK_SIZE; j++) {

                        for(i = 0; i < CHUNK_SIZE;) {

                            if(mask[n] != null) {

                                /*
                                 * We compute the width
                                 */
                                for(w = 1; i + w < CHUNK_SIZE && mask[n + w] != null && mask[n + w].equals(mask[n]); w++) {}

                                /*
                                 * Then we compute height
                                 */
                                boolean done = false;

                                for(h = 1; j + h < CHUNK_SIZE; h++) {

                                    for(k = 0; k < w; k++) {

                                        if(mask[n + k + h * CHUNK_SIZE] == null || !mask[n + k + h * CHUNK_SIZE].equals(mask[n])) { done = true; break; }
                                    }

                                    if(done) { break; }
                                }

                                /*
                                 * Here we check the "transparent" attribute in the Face class to ensure that we don't mesh 
                                 * any culled faces.
                                 */
                                if (!mask[n].transparent) {
                                    /*
                                     * Add quad
                                     */
                                    x[u] = i;  
                                    x[v] = j;

                                    du[0] = 0;
                                    du[1] = 0;
                                    du[2] = 0;
                                    du[u] = w;

                                    dv[0] = 0;
                                    dv[1] = 0;
                                    dv[2] = 0;
                                    dv[v] = h;

                                    /*
                                     * And here we call the quad function in order to render a merged quad in the scene.
                                     * 
                                     * We pass mask[n] to the function, which is an instance of the Face class containing 
                                     * all the attributes of the face - which allows for variables to be passed to shaders - for 
                                     * example lighting values used to create ambient occlusion.
                                     */
                                    quad(new Vec3f(x[0],                 x[1],                   x[2]), 
                                         new Vec3f(x[0] + du[0],         x[1] + du[1],           x[2] + du[2]), 
                                         new Vec3f(x[0] + du[0] + dv[0], x[1] + du[1] + dv[1],   x[2] + du[2] + dv[2]), 
                                         new Vec3f(x[0] + dv[0],         x[1] + dv[1],           x[2] + dv[2]), 
                                         w,
                                         h,
                                         mask[n],
                                         backFace);
                                }

                                /*
                                 * We zero out the mask
                                 */
                                for(l = 0; l < h; ++l) {

                                    for(k = 0; k < w; ++k) { mask[n + k + l * CHUNK_SIZE] = null; }
                                }

                                /*
                                 * And then finally increment the counters and continue
                                 */
                                i += w; 
                                n += w;

                            } else {

                              i++;
                              n++;
                            }
                        }
                    } 
                }
            }        
        }
    }

    /**
     * This function returns an instance of Face containing the attributes for 
     * one side of a voxel.  In this simple demo we just return a value from the 
     * sample data array.  However, in an actual voxel engine, this function would 
     * check if the voxel face should be culled, and set per-face and per-vertex 
     * values as well as voxel values in the returned instance.
     * 
     * @param x
     * @param y
     * @param z
     * @param face
     * @return 
     */
    Face getFace(final int x, final int y, final int z, final int side) {

        Face voxelFace = voxels[x][y][z];
        
        if (voxelFace != null)
        	voxelFace.side = side;

        return voxelFace;
    }
    
    /**
     * This function renders a single quad in the scene. This quad may represent many adjacent voxel 
     * faces - so in order to create the illusion of many faces, you might consider using a tiling 
     * function in your voxel shader. For this reason I've included the quad width and height as parameters.
     * 
     * For example, if your texture coordinates for a single voxel face were 0 - 1 on a given axis, they should now 
     * be 0 - width or 0 - height. Then you can calculate the correct texture coordinate in your fragement 
     * shader using coord.xy = fract(coord.xy). 
     * 
     * 
     * @param bottomLeft
     * @param topLeft
     * @param topRight
     * @param bottomRight
     * @param width
     * @param height
     * @param voxel
     * @param backFace 
     */
    void quad(final Vec3f bottomLeft, 
              final Vec3f topLeft, 
              final Vec3f topRight, 
              final Vec3f bottomRight,
              final int width,
              final int height,
              final Face voxel, 
              final boolean backFace) {
 
        final Vec3f [] vertices = new Vec3f[4];

        vertices[2] = topLeft;
        vertices[3] = topRight;
        vertices[0] = bottomLeft;
        vertices[1] = bottomRight;
        
        int [] faces = backFace ? new int[] { 2,3,0,2,1,0, 1,0,3,3,2,1 } : new int[]{ 2,3,3,1,1,0, 1,0,0,2,2,3 };
        
        float[] texture = {1, 1, 1, 0, 0, 1, 0, 0};
        
        TriangleMesh m = new TriangleMesh();

		// Points
		for (Vec3f v : vertices) {
			float x = v.x;
			float y = v.y;
			float z = v.z;
			m.getPoints().addAll(x, y, z);
		}

		// Textures
		for (float i : texture)
			m.getTexCoords().addAll(i);

		// Faces
		for (int i : faces)
			m.getFaces().addAll(i);
		
		MeshView face = new MeshView(m);
		face.setDrawMode(DrawMode.LINE);
		java.awt.Color c = Main.getModel().getColour(voxel.texture);
		material = new PhongMaterial(new Color((float)c.getRed()/255, (float)c.getGreen()/255, (float)c.getBlue()/255, (float)c.getAlpha()/255));
	    face.setMaterial(material);
		
		getChildren().add(face);
    }
}
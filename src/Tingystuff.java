public class Tingystuff {
	public static void main (String[] args) {	
		byte[][][] bla1 = new byte[1024][1024][1024];
		long startTime1 = System.currentTimeMillis();
		for (short i = 0; i < 1024; i++) {
			for (short j = 0; j < 1024; j++) {
				for (short k = 0; k < 1024; k++) {
					bla1[i][j][k] = Byte.MAX_VALUE;
				}
			}
		}
		long et1 = System.currentTimeMillis() - startTime1;
		
		long startTime2 = System.currentTimeMillis();
		for (short j = 0; j < 1024; j++) {
			for (short k = 0; k < 1024; k++) {
				byte b = bla1[0][j][k];
			}
		}
		long et2 = System.currentTimeMillis() - startTime2;
		
		System.out.println(et1 + "\n" + et2);
	}
}
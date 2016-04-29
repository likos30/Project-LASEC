import java.util.ArrayList;

public class Sn {
	public static void main(String[] args){
		int[][] keyMod2 =new int[2][2];
		keyMod2[0][0] = 1;
		keyMod2[0][1] = 1;
		keyMod2[1][0] = 1;
		keyMod2[1][1] = 0;
		
		int[][] key = new int[2][2];
		keyMod2[0][0] = 15;
		keyMod2[0][1] = 21;
		keyMod2[1][0] = 1;
		keyMod2[1][1] = 18;
		
	}

	private void biasSum(ArrayList<Integer> mu,ArrayList<ArrayList<Integer>> vector){
		
	}
	
	public static int[][] multiply(int[][] A, int[][] B) {
        int mA = A.length;
        int nA = A[0].length;
        int mB = B.length;
        int nB = B[0].length;
        if (nA != mB) throw new RuntimeException("Illegal matrix dimensions.");
        int[][] C = new int[mA][nB];
        for (int i = 0; i < mA; i++)
            for (int j = 0; j < nB; j++)
                for (int k = 0; k < nA; k++)
                    C[i][j] += A[i][k] * B[k][j];
        return C;
    }
}

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Probability {
	/*
	 * here are the proba from a website who did it from several sources
	 * p(A) = 0.0808
	 * P(B) = 0.0167
	 * P(C) = 0.0318
	 * P(D) = 0.0399
	 * P(E) = 0.1256
	 * P(F) = 0.0217
	 * P(G) = 0.0180
	 * P(H) = 0.0527
	 * P(I) = 0.0724
	 * P(J) = 0.0724
	 * P(K) = 0.0063
	 * P(L) = 0.0404
	 * P(M) = 0.0260
	 * P(N) = 0.0738
	 * P(O) = 0.0747
	 * P(P) = 0.0191
	 * P(Q) = 0.0009
	 * P(R) = 0.0642
	 * P(S) = 0.0659
	 * P(T) = 0.0915
	 * P(U) = 0.0279
	 * P(V) = 0.0100
	 * P(W) = 0.0189
	 * P(X) = 0.0021
	 * P(Y) = 0.0165
	 * P(Z) = 0.0007
	 * Sum = 0.9999
	 */
	public static void main(String[] args) throws IOException, IOException {
		double[] alphabet = new double[26];
		double sum=0;
		double sumSquare=0;
		double sum0Mod2=0;
		double sum1Mod2=0;
		PrintWriter writer = new PrintWriter("probabilités.txt","UTF-8");
		for(int i=0;i<alphabet.length;i++){
			Scanner sc = new Scanner(System.in);
			System.out.println("Probabilité de la "+(i+1)+ "ieme lettre de l'aphabet");
			double proba = sc.nextDouble();
			writer.println("Probabilite de la "+(i+1)+ "ieme lettre de l'aphabet "+proba);
			sum +=proba;
			sumSquare+=(Math.pow(proba, 2));
			if(i%2==0) {
				sum0Mod2+=proba;
			} else {
				sum1Mod2+=proba;
			}
			sc.nextLine();
	}

		writer.println("proba sum = "+ sum);
		writer.println("proba sum squared = "+ sumSquare);
		writer.println("proba sum squared 0 = "+ sum0Mod2 + " " + Math.pow(sum0Mod2,2));
		writer.println("proba sum squared 1 = "+ sum1Mod2 + " " + Math.pow(sum1Mod2,2));
		writer.println("Ration of good matching and all matchings="+(sumSquare/(Math.pow(sum0Mod2,2)+Math.pow(sum1Mod2,2))));
		writer.close();

}
}

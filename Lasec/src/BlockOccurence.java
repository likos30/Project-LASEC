import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BlockOccurence {
	
	public static void main(String[] args) throws IOException, IOException {
	int N = 1024*1024;
	String text = readDoc(new File("test")).toLowerCase();
	String textNoSpace = text.replaceAll("\\s+","");
	Scanner reader = new Scanner(System.in);
	int blockSize = 0;
	double sumSquare=0;
	double sum=0;
	//while(blockSize<2) {
		System.out.println("Choose a block size");
		blockSize = reader.nextInt();
	//}
	int numberOfBlock = textNoSpace.length()-blockSize+1;
	ArrayList<String> letter = new ArrayList<String>();
	int bufferCounter[] = new int[N];
	for(int i=0;i<(textNoSpace.length()-blockSize+1);i++){
		String select = textNoSpace.substring(i,i+blockSize);
		if(letter.contains(select)){
			bufferCounter[letter.indexOf(select)]++;
		}else {
			letter.add(select);
			bufferCounter[letter.indexOf(select)]=1;
		}
		System.out.println((double)i/(textNoSpace.length()-blockSize+1)+  "% " + i + " block");
	}
	System.out.println("finish to fill");
	
	PrintWriter writer = new PrintWriter("occurenceblocks.txt","UTF-8");
	for(int i=0;i<letter.size();i++){
		writer.println("le block " + letter.get(i) + " apparait "+ bufferCounter[i] + "fois");
		double proba = (double)bufferCounter[i]/numberOfBlock;
		sum+=proba;
		sumSquare+=(Math.pow(proba, 2));
	}
	writer.println("For Block Size "+ blockSize + "We have "+ numberOfBlock + " blocks and the sum gives "+ sum + "and proba gives "+ sumSquare);
	writer.close();
	System.out.println("FINISH");
	}
	
	public static String readDoc(File f) {
		String text = "";
		int read, N = 1024 * 1024;
		char[] buffer = new char[N];

		try {
		    FileReader fr = new FileReader(f);
		    BufferedReader br = new BufferedReader(fr);

		    while(true) {
		        read = br.read(buffer, 0, N);
		        text += new String(buffer, 0, read);

		        if(read < N) {
		            break;
		        }
		    }
		} catch(Exception ex) {
		    ex.printStackTrace();
		}

		return text;
		}
}
